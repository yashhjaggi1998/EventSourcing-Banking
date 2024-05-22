package dev.codescreen.codescreen_jl7syjim.delegate;

import dev.codescreen.codescreen_jl7syjim.api.AuthorizationApi;

public class AuthorizationDelegate implements AuthorizationApi {

    private final AuthorizationApi delegate;

    public AuthorizationDelegate(AuthorizationApi delegate) {
        this.delegate = delegate;
    }

    /*@Override
    public ResponseEntity<?> authorizationPut(AuthorizationRequest authorizationRequest) {
        if (shouldReturnAuthorizationResponse(authorizationRequest)) {
            return delegate.authorizationPut(authorizationRequest);
        } else {
            Error error = buildErrorResponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }*/
}
