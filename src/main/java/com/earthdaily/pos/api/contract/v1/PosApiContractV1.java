package com.earthdaily.pos.api.contract.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pos V1", description = "Point of interest API Contract V1")
@RequestMapping("/v1/pos")
public interface PosApiContractV1 {

    @Operation(summary = "Update a point of interest",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @PutMapping("/{id}")
    ResponseEntity update(@PathVariable String id, @RequestBody Pos newPos);

    @Operation(summary = "Create a point of interest",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @PostMapping
    ResponseEntity create(@RequestBody Pos request);


    @Operation(summary = "Get point of interest by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @GetMapping("/{id}")
    ResponseEntity<Pos> get(@PathVariable String id);
}