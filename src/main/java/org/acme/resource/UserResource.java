package org.acme.resource;

import org.acme.exceptions.AuthenticationFailException;
import org.acme.exceptions.CustomException;
import org.acme.model.User;
import org.acme.repository.UserRepository;
import org.acme.resource.request.ResponseRequest;
import org.acme.resource.request.SignInRequest;
import org.acme.resource.request.SignInResponse;
import org.acme.resource.request.SignupRequest;
import org.acme.service.AuthenticationService;
import org.acme.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
public class UserResource {

    @Inject
    private UserRepository userRepository;

    @Inject
    private  AuthenticationService authenticationService;

    @Inject
    private UserService userService;

    @GET
    @Path("/all")
    public List<User> findAllUser(@PathParam("token") String token) throws AuthenticationFailException{
        authenticationService.authenticate(token);
        return userRepository.findAll();

    }

    @POST
    @Path("/signup")
    public ResponseRequest signup(SignupRequest signupRequest) throws CustomException{
        return userService.signUp(signupRequest);
    }

    @POST
    @Path("/signup")
    public SignInResponse signin(SignInRequest signInRequest) throws CustomException{
        return userService.signIn(signInRequest);
    }

    //    @PostMapping("/updateUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserUpdateDto userUpdateDto) {
//        authenticationService.authenticate(token);
//        return userService.updateUser(token, userUpdateDto);
//    }


//    @PostMapping("/createUser")
//    public ResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
//            throws CustomException, AuthenticationFailException {
//        authenticationService.authenticate(token);
//        return userService.createUser(token, userCreateDto);
//    }
}
