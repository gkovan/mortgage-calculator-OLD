package com.gk.mortgage.calculator.domain;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Configuration
public class Messages {
	
	@Autowired
	public Messages()
	{
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("messages.yml"));
		Messages.messages = yaml.getObject();
	}
	
    private static Properties messages;

    public String getProperty(String name) {
        return messages.getProperty(name);
    }

}