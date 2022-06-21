package org.acme.service;

import org.acme.config.MessageStrings;
import org.acme.exceptions.AuthenticationFailException;
import org.acme.model.AuthenticationToken;
import org.acme.model.User;
import org.acme.repository.TokenRepository;
import org.acme.utils.Helper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthenticationService {

    @Inject
    TokenRepository tokenRepository;

    public void saveConfirmationToken (AuthenticationToken authenticationToken){
        tokenRepository.persist(authenticationToken);
    }

    public AuthenticationToken getToken(User user){
        return tokenRepository.findTokenByUser(user);
    }

    public User getUser(String token){
        AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)){
            if (Helper.notNull(authenticationToken.getUser())){
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException{
        if (!Helper.notNull(token)){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }

}
