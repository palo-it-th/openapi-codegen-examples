package com.paloit.client.petstore.webclient.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-01-19T15:22:07.236567+07:00[Asia/Bangkok]")
public class HttpBearerAuth implements Authentication {
    private final String scheme;
    private String bearerToken;

    public HttpBearerAuth(String scheme) {
        this.scheme = scheme;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    @Override
    public void applyToParams(MultiValueMap<String, String> queryParams, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams) {
        if (bearerToken == null) {
            return;
        }
        headerParams.add(HttpHeaders.AUTHORIZATION, (scheme != null ? upperCaseBearer(scheme) + " " : "") + bearerToken);
    }

    private static String upperCaseBearer(String scheme) {
        return ("bearer".equalsIgnoreCase(scheme)) ? "Bearer" : scheme;
    }

}
