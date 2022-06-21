package org.acme.service;

import org.acme.config.MessageStrings;
import org.acme.enums.ResponseStatus;
import org.acme.exceptions.AuthenticationFailException;
import org.acme.exceptions.CustomException;
import org.acme.model.AuthenticationToken;
import org.acme.model.User;
import org.acme.repository.UserRepository;
import org.acme.resource.request.*;
import org.acme.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.acme.config.MessageStrings.USER_CREATED;

@Singleton
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseRequest signUp(SignupRequest signupRequest) throws CustomException {
        //check to see if the current email address has already been registered
        if (Helper.notNull(userRepository.findByEmail(signupRequest.getEmail()))){
            //if the email address has been registered then throw an exception
            throw new CustomException("User already exists");
        }
        //first encrypt the password
        String encryptedPassword = signupRequest.getPassword();
        try {
            encryptedPassword = hashPassword(signupRequest.getPassword());
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(signupRequest.getFirstName(), signupRequest.getLastName(), signupRequest.getEmail(), encryptedPassword);
        //User createdUser;

        try {
            userRepository.persist(user);

            //generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            //save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            //success in creating
            return new ResponseRequest(ResponseStatus.SUCCESS.toString(), USER_CREATED);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    public SignInResponse signIn (SignInRequest signInRequest) throws CustomException {
        //first find user by email
        User user = userRepository.findByEmail(signInRequest.getEmail());
        if (!Helper.notNull(user)){
            throw new AuthenticationFailException("User not present");
        }
        try {
            //check if password is right
            if (!user.getPassword().equals(hashPassword(signInRequest.getPassword()))){
                //password does not exist
                throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (!Helper.notNull(token)){
            //token not present
            throw new CustomException("token not present");
        }
        return new SignInResponse("success", token.getToken());
    }

    String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public ResponseRequest createUser(String token, CreateUserRequest createUserRequest) throws CustomException, AuthenticationFailException{
        User creatingUser = authenticationService.getUser(token);
//        if (!canCrudUser(creatingUser.getRole())){
//            //user can't create new user
//            throw new AuthenticationFailException(MessageStrings.USER_NOT_PERMITTED);
//        }
        String encryptedPassword = createUserRequest.getPassword();
        try {
            encryptedPassword = hashPassword(createUserRequest.getPassword());
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(creatingUser.getFirstName(), createUserRequest.getLastName(), createUserRequest.getEmail(),
                encryptedPassword);
        //User createdUser;
        try {
            userRepository.persist(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ResponseRequest(ResponseStatus.SUCCESS.toString(), USER_CREATED);
        }
        catch (Exception e){
            //handle user creation fail error
            throw new CustomException(e.getMessage());
        }
    }

//    boolean canCrudUser(Role role) {
//        if (role == Role.admin || role == Role.manager) {
//            return true;
//        }
//        return false;
//    }

//    boolean canCrudUser(User userUpdating, Integer userIdBeingUpdated) {
//        Role role = userUpdating.getRole();
//        // admin and manager can crud any user
//        if (role == Role.admin || role == Role.manager) {
//            return true;
//        }
//        // user can update his own record, but not his role
//        if (role == Role.user && userUpdating.getId() == userIdBeingUpdated) {
//            return true;
//        }
//        return false;
//    }
}
