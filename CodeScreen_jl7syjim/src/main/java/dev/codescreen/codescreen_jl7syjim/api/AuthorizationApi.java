package dev.codescreen.codescreen_jl7syjim.api;

import dev.codescreen.codescreen_jl7syjim.model.AuthorizationRequest;
import dev.codescreen.codescreen_jl7syjim.model.AuthorizationResponse;
import dev.codescreen.codescreen_jl7syjim.model.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.Optional;

public interface AuthorizationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(
            operationId = "authorizationPut",
            summary = "Removes funds from a user's account if sufficient funds are available.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "The result of an authorization", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorizationResponse.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Server Error response", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/authorization",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    default ResponseEntity<?> _authorizationPut(
            @Parameter(name = "AuthorizationRequest", description = "An authorization request message that needs to be decisioned.") @Valid @RequestBody(required = false) AuthorizationRequest authorizationRequest
    ) {
        return authorizationPut(authorizationRequest);
    }

    // Override this method
    default  ResponseEntity<?> authorizationPut(AuthorizationRequest authorizationRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"balance\" : { \"amount\" : \"amount\", \"currency\" : \"currency\" }, \"messageId\" : \"messageId\", \"userId\" : \"userId\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : \"code\", \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
