/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.paloit.server.petstore.api;

import com.paloit.server.petstore.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-01-19T12:00:24.562146+07:00[Asia/Bangkok]")
@Validated
@Controller
@Tag(name = "user", description = "the user API")
public interface UserApi {

    default UserApiDelegate getDelegate() {
        return new UserApiDelegate() {};
    }

    /**
     * POST /user : Create user
     * This can only be done by the logged in user.
     *
     * @param user Created user object (optional)
     * @return successful operation (status code 200)
     */
    @Operation(summary = "Create user", tags={ "user", }, responses = {  @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  User.class))) })
        @RequestMapping(
        method = RequestMethod.POST,
        value = "/user",
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
    )
    default ResponseEntity<User> createUser(

@Parameter(name = "Created user object" )   @Valid @RequestBody(required = false) User user) {
        return getDelegate().createUser(user);
    }


    /**
     * POST /user/createWithList : Creates list of users with given input array
     * Creates list of users with given input array
     *
     * @param user  (optional)
     * @return Successful operation (status code 200)
     *         or successful operation (status code 200)
     */
    @Operation(summary = "Creates list of users with given input array", tags={ "user", }, responses = {  @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  User.class))), @ApiResponse(responseCode = "200", description = "successful operation") })
        @RequestMapping(
        method = RequestMethod.POST,
        value = "/user/createWithList",
        produces = { "application/xml", "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<User> createUsersWithListInput(

@Parameter(name = "" )   @Valid @RequestBody(required = false) List<User> user) {
        return getDelegate().createUsersWithListInput(user);
    }


    /**
     * DELETE /user/{username} : Delete user
     * This can only be done by the logged in user.
     *
     * @param username The name that needs to be deleted (required)
     * @return Invalid username supplied (status code 400)
     *         or User not found (status code 404)
     */
    @Operation(summary = "Delete user", tags={ "user", }, responses = {  @ApiResponse(responseCode = "400", description = "Invalid username supplied"), @ApiResponse(responseCode = "404", description = "User not found") })
        @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/user/{username}"
    )
    default ResponseEntity<Void> deleteUser(@Parameter(name = "username", description = "The name that needs to be deleted", required = true) @PathVariable("username") String username

) {
        return getDelegate().deleteUser(username);
    }


    /**
     * GET /user/{username} : Get user by user name
     *
     * @param username The name that needs to be fetched. Use user1 for testing.  (required)
     * @return successful operation (status code 200)
     *         or Invalid username supplied (status code 400)
     *         or User not found (status code 404)
     */
    @Operation(summary = "Get user by user name", tags={ "user", }, responses = {  @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  User.class))), @ApiResponse(responseCode = "400", description = "Invalid username supplied"), @ApiResponse(responseCode = "404", description = "User not found") })
        @RequestMapping(
        method = RequestMethod.GET,
        value = "/user/{username}",
        produces = { "application/xml", "application/json" }
    )
    default ResponseEntity<User> getUserByName(@Parameter(name = "username", description = "The name that needs to be fetched. Use user1 for testing. ", required = true) @PathVariable("username") String username

) {
        return getDelegate().getUserByName(username);
    }


    /**
     * GET /user/login : Logs user into the system
     *
     * @param username The user name for login (optional)
     * @param password The password for login in clear text (optional)
     * @return successful operation (status code 200)
     *         or Invalid username/password supplied (status code 400)
     */
    @Operation(summary = "Logs user into the system", tags={ "user", }, responses = {  @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  String.class))), @ApiResponse(responseCode = "400", description = "Invalid username/password supplied") })
        @RequestMapping(
        method = RequestMethod.GET,
        value = "/user/login",
        produces = { "application/xml", "application/json" }
    )
    default ResponseEntity<String> loginUser(@Parameter(name = "username", description = "The user name for login") @Valid @RequestParam(value = "username", required = false) String username

,@Parameter(name = "password", description = "The password for login in clear text") @Valid @RequestParam(value = "password", required = false) String password

) {
        return getDelegate().loginUser(username, password);
    }


    /**
     * GET /user/logout : Logs out current logged in user session
     *
     * @return successful operation (status code 200)
     */
    @Operation(summary = "Logs out current logged in user session", tags={ "user", }, responses = {  @ApiResponse(responseCode = "200", description = "successful operation") })
        @RequestMapping(
        method = RequestMethod.GET,
        value = "/user/logout"
    )
    default ResponseEntity<Void> logoutUser() {
        return getDelegate().logoutUser();
    }


    /**
     * PUT /user/{username} : Update user
     * This can only be done by the logged in user.
     *
     * @param username name that need to be deleted (required)
     * @param user Update an existent user in the store (optional)
     * @return successful operation (status code 200)
     */
    @Operation(summary = "Update user", tags={ "user", }, responses = {  @ApiResponse(responseCode = "200", description = "successful operation") })
        @RequestMapping(
        method = RequestMethod.PUT,
        value = "/user/{username}",
        consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
    )
    default ResponseEntity<Void> updateUser(@Parameter(name = "username", description = "name that need to be deleted", required = true) @PathVariable("username") String username

,

@Parameter(name = "Update an existent user in the store" )   @Valid @RequestBody(required = false) User user) {
        return getDelegate().updateUser(username, user);
    }

}