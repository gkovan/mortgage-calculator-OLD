package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CurbsideRestAssuredConfig {

    public static final String PROXY_URL = "http://inetgw.aa.com:9091";


    public static RequestSpecification getProxyForURL(String url){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy(PROXY_URL)
                .setBaseUri(url)
                .build();
    }

    public static RequestSpecification getCurbsideSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

}
