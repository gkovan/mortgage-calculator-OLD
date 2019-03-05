package com.gk.mortgage.calculator.logging;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gk.mortgage.calculator.annotations.Encrypt;
import com.gk.mortgage.calculator.annotations.PCI;
import com.gk.mortgage.calculator.annotations.PII;
import com.gk.mortgage.calculator.annotations.Sensitive;


public class MaskDataSerializer extends JsonSerializer<Object> {
	private static final long serialVersionUID = 1L;
	private JsonSerializer<Object> defaultSerializer;

	public MaskDataSerializer(JsonSerializer<Object> serializer) {
		defaultSerializer = serializer;
	}

	@Override
	public void serialize(Object obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		
		Class<?> objectClass = obj.getClass();

		jgen.writeStartObject();
		while (objectClass != null) {
		  			
			if (objectClass.getName().equalsIgnoreCase("java.lang.Object")) {
				break;
			}
			
		if (objectClass.isAnnotationPresent(Sensitive.class)) {

			for (Field field : objectClass.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					if (field.isAnnotationPresent(PII.class) || field.isAnnotationPresent(PCI.class)
							|| field.isAnnotationPresent(Encrypt.class)) {

						MaskingFactory mf = new MaskingFactory();
						SensitiveDataMask sensitiveData = mf.getInstance(field.getAnnotations());

						int keepLastDigits = 0;
						String maskedValue = null;
						if (field.isAnnotationPresent(PII.class)) {
							keepLastDigits = field.getAnnotation(PII.class).keepLastDigits();
							if (sensitiveData != null) {
								maskedValue = sensitiveData.mask((String) field.get(obj), keepLastDigits);
							}
						} else if (field.isAnnotationPresent(PCI.class)) {
							keepLastDigits = field.getAnnotation(PCI.class).keepLastDigits();
							if (sensitiveData != null) {
								maskedValue = sensitiveData.mask((String) field.get(obj), keepLastDigits);
							}
						} else if (field.isAnnotationPresent(Encrypt.class)) {
							if (sensitiveData != null) {
								maskedValue = sensitiveData.mask((String) field.get(obj));
							}
						}

						jgen.writeObjectField(field.getName(), maskedValue);
					} else {
						jgen.writeObjectField(field.getName(), field.get(obj));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} else {
			
			
			if (String.class.isAssignableFrom(obj.getClass())) {


					MaskingFactory mf = new MaskingFactory();
					SensitiveDataMask sensitiveData = mf.getInstance(obj.getClass().getAnnotations());

					if (sensitiveData != null) {
						String maskedValue = sensitiveData.mask((String) obj);

						jgen.writeString(maskedValue);
					} else {
						jgen.writeString((String) obj);
					}

			} else {
				
				
				try {
					for (Field field : objectClass.getDeclaredFields()) {
						field.setAccessible(true);
						jgen.writeObjectField(field.getName(), field.get(obj));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		  objectClass = objectClass.getSuperclass();
		}
		jgen.writeEndObject();
	}

}
