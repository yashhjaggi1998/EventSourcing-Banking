package dev.codescreen.codescreen_jl7syjim.api;

import dev.codescreen.codescreen_jl7syjim.model.Error;
import dev.codescreen.codescreen_jl7syjim.model.LoadRequest;
import dev.codescreen.codescreen_jl7syjim.model.LoadResponse;
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

public interface LoadApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(
            operationId = "loadPut",
            summary = "Adds funds to a user's account.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "The result of an load", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LoadResponse.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Server Error response", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/load",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    default ResponseEntity<?> _loadPut(
            @Parameter(name = "LoadRequest", description = "An load request message that needs to be decisioned. This balance will be added to a user's balance.") @Valid @RequestBody(required = false) LoadRequest loadRequest
    ) {
        return loadPut(loadRequest);
    }

    // Override this method
    default  ResponseEntity<?> loadPut(LoadRequest loadRequest) {
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