package com.paloit.client.petstore.webclient.api;

import com.paloit.client.petstore.webclient.apiclient.ApiClient;
import com.paloit.client.petstore.webclient.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-01-19T15:22:07.236567+07:00[Asia/Bangkok]")
public class UserApi {
    private ApiClient apiClient;

    public UserApi() {
        this(new ApiClient());
    }

    @Autowired(required=false)
    public UserApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create user
     * This can only be done by the logged in user.
     * <p><b>0</b> - successful operation
     * @param user Created user object
     * @return User
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createUserRequestCreation(User user) throws WebClientResponseException {
        Object postBody = user;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json", "application/xml"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json", "application/xml", "application/x-www-form-urlencoded"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/user", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create user
     * This can only be done by the logged in user.
     * <p><b>0</b> - successful operation
     * @param user Created user object
     * @return User
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<User> createUser(User user) throws WebClientResponseException {
        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return createUserRequestCreation(user).bodyToMono(localVarReturnType);
    }

    public Mono<ResponseEntity<User>> createUserWithHttpInfo(User user) throws WebClientResponseException {
        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return createUserRequestCreation(user).toEntity(localVarReturnType);
    }
    /**
     * Creates list of users with given input array
     * Creates list of users with given input array
     * <p><b>200</b> - Successful operation
     * <p><b>0</b> - successful operation
     * @param user The user parameter
     * @return User
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createUsersWithListInputRequestCreation(List<User> user) throws WebClientResponseException {
        Object postBody = user;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/xml", "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/user/createWithList", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Creates list of users with given input array
     * Creates list of users with given input array
     * <p><b>200</b> - Successful operation
     * <p><b>0</b> - successful operation
     * @param user The user parameter
     * @return User
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<User> createUsersWithListInput(List<User> user) throws WebClientResponseException {
        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return createUsersWithListInputRequestCreation(user).bodyToMono(localVarReturnType);
    }

    public Mono<ResponseEntity<User>> createUsersWithListInputWithHttpInfo(List<User> user) throws WebClientResponseException {
        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return createUsersWithListInputRequestCreation(user).toEntity(localVarReturnType);
    }
    /**
     * Delete user
     * This can only be done by the logged in user.
     * <p><b>400</b> - Invalid username supplied
     * <p><b>404</b> - User not found
     * @param username The name that needs to be deleted
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteUserRequestCreation(String username) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'username' is set
        if (username == null) {
            throw new WebClientResponseException("Missing the required parameter 'username' when calling deleteUser", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("username", username);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/user/{username}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete user
     * This can only be done by the logged in user.
     * <p><b>400</b> - Invalid username supplied
     * <p><b>404</b> - User not found
     * @param username The name that needs to be deleted
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteUser(String username) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteUserRequestCreation(username).bodyToMono(localVarReturnType);
    }

    public Mono<ResponseEntity<Void>> deleteUserWithHttpInfo(String username) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteUserRequestCreation(username).toEntity(localVarReturnType);
    }
    /**
     * Get user by user name
     * 
     * <p><b>200</b> - successful operation
     * <p><b>400</b> - Invalid username supplied
     * <p><b>404</b> - User not found
     * @param username The name that needs to be fetched. Use user1 for testing. 
     * @return User
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getUserByNameRequestCreation(String username) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'username' is set
        if (username == null) {
            throw new WebClientResponseException("Missing the required parameter 'username' when calling getUserByName", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("username", username);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/xml", "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI("/user/{username}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get user by user name
     * 
     * <p><b>200</b> - successful operation
     * <p><b>400</b> - Invalid username supplied
     * <p><b>404</b> - User not found
     * @param username The name that needs to be fetched. Use user1 for testing. 
     * @return User
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<User> getUserByName(String username) throws WebClientResponseException {
        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return getUserByNameRequestCreation(username).bodyToMono(localVarReturnType);
    }

    public Mono<ResponseEntity<User>> getUserByNameWithHttpInfo(String username) throws WebClientResponseException {
        ParameterizedTypeReference<User> localVarReturnType = new ParameterizedTypeReference<User>() {};
        return getUserByNameRequestCreation(username).toEntity(localVarReturnType);
    }
    /**
     * Logs user into the system
     * 
     * <p><b>200</b> - successful operation
     * <p><b>400</b> - Invalid username/password supplied
     * @param username The user name for login
     * @param password The password for login in clear text
     * @return String
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec loginUserRequestCreation(String username, String password) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "username", username));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "password", password));

        final String[] localVarAccepts = { 
            "application/xml", "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<String> localVarReturnType = new ParameterizedTypeReference<String>() {};
        return apiClient.invokeAPI("/user/login", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Logs user into the system
     * 
     * <p><b>200</b> - successful operation
     * <p><b>400</b> - Invalid username/password supplied
     * @param username The user name for login
     * @param password The password for login in clear text
     * @return String
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<String> loginUser(String username, String password) throws WebClientResponseException {
        ParameterizedTypeReference<String> localVarReturnType = new ParameterizedTypeReference<String>() {};
        return loginUserRequestCreation(username, password).bodyToMono(localVarReturnType);
    }

    public Mono<ResponseEntity<String>> loginUserWithHttpInfo(String username, String password) throws WebClientResponseException {
        ParameterizedTypeReference<String> localVarReturnType = new ParameterizedTypeReference<String>() {};
        return loginUserRequestCreation(username, password).toEntity(localVarReturnType);
    }
    /**
     * Logs out current logged in user session
     * 
     * <p><b>0</b> - successful operation
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec logoutUserRequestCreation() throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/user/logout", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Logs out current logged in user session
     * 
     * <p><b>0</b> - successful operation
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> logoutUser() throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return logoutUserRequestCreation().bodyToMono(localVarReturnType);
    }

    public Mono<ResponseEntity<Void>> logoutUserWithHttpInfo() throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return logoutUserRequestCreation().toEntity(localVarReturnType);
    }
    /**
     * Update user
     * This can only be done by the logged in user.
     * <p><b>0</b> - successful operation
     * @param username name that need to be deleted
     * @param user Update an existent user in the store
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateUserRequestCreation(String username, User user) throws WebClientResponseException {
        Object postBody = user;
        // verify the required parameter 'username' is set
        if (username == null) {
            throw new WebClientResponseException("Missing the required parameter 'username' when calling updateUser", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("username", username);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json", "application/xml", "application/x-www-form-urlencoded"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/user/{username}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update user
     * This can only be done by the logged in user.
     * <p><b>0</b> - successful operation
     * @param username name that need to be deleted
     * @param user Update an existent user in the store
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> updateUser(String username, User user) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return updateUserRequestCreation(username, user).bodyToMono(localVarReturnType);
    }

    public Mono<ResponseEntity<Void>> updateUserWithHttpInfo(String username, User user) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return updateUserRequestCreation(username, user).toEntity(localVarReturnType);
    }
}
