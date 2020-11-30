/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.23).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package hu.bme.mit.theta.cloud.cfa.endpoint.generated;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bme.mit.theta.cloud.cfa.endpoint.generated.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CfaApi {

    Logger log = LoggerFactory.getLogger(CfaApi.class);

    Optional<ObjectMapper> getObjectMapper();

    Optional<HttpServletRequest> getRequest();


    @Operation(summary = "", description = "Fetches the metadata for a CFA model", tags = {"cfa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched the model", content = @Content(schema = @Schema(implementation = GetModelFileResponse.class))),

            @ApiResponse(responseCode = "404", description = "Model with the given id not found"),

            @ApiResponse(responseCode = "500", description = "The error response if something goes wrong.")})
    @RequestMapping(value = "/cfa/{modelId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<?> getCfaModel(@Parameter(in = ParameterIn.PATH, description = "The model id", required = true, schema = @Schema()) @PathVariable("modelId") UUID modelId);


    @Operation(summary = "", description = "Fetches the metadata for a CFA model", tags = {"cfa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "102", description = "Analysis process not finished yet"),

            @ApiResponse(responseCode = "200", description = "Analysis finished", content = @Content(schema = @Schema(implementation = InlineResponse200.class))),

            @ApiResponse(responseCode = "404", description = "Process with the given id not found"),

            @ApiResponse(responseCode = "500", description = "The error response if something goes wrong.")})
    @RequestMapping(value = "/cfa/{modelId}/{processId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<InlineResponse200> getProcessResult(@Parameter(in = ParameterIn.PATH, description = "The model id", required = true, schema = @Schema()) @PathVariable("modelId") UUID modelId, @Parameter(in = ParameterIn.PATH, description = "The analysis process id", required = true, schema = @Schema()) @PathVariable("processId") UUID processId);


    @Operation(summary = "", description = "Starts analysis process on the model with the given parameters", tags = {"cfa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Analysis started successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StartProcessResponse.class)))),

            @ApiResponse(responseCode = "404", description = "Model with the given id not found"),

            @ApiResponse(responseCode = "500", description = "The error response if something goes wrong.")})
    @RequestMapping(value = "/cfa/{modelId}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<List<StartProcessResponse>> startCfaAnalysis(@Parameter(in = ParameterIn.PATH, description = "The model id", required = true, schema = @Schema()) @PathVariable("modelId") UUID modelId, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @RequestBody List<ModelConfig> body);


    @Operation(summary = "Upload CFA model file", description = "", tags = {"cfa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CFA model uploaded successfully", content = @Content(schema = @Schema(implementation = CreateModelResponse.class))),

            @ApiResponse(responseCode = "400", description = "Bad file format")})
    @RequestMapping(value = "/cfa",
            produces = {"application/json"},
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<CreateModelResponse> uploadCfa(@RequestParam(value = "model") MultipartFile model);

}

