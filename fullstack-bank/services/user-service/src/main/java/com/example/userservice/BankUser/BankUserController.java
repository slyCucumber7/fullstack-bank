package com.example.userservice.BankUser;

import com.example.userservice.common.ResponseWrapper;
import com.example.userservice.common.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("user-service/")
@RestController
public class BankUserController {

    private final BankUserService bankUserService;

    @GetMapping("users/{userId}")
    @Operation(
            summary = "Get a record of user information",
            description = "Retrieves a record containing user information for a given user, provided a valid user id."
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType ="application/json", schema = @Schema(implementation = ResponseWrapper.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType ="application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<ResponseWrapper<BankUserDto>> getUserDetails(@PathVariable Long userId){

        BankUserDto dto = bankUserService.getUserFromId(userId);
        if(dto != null){
            var response = ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder()
                            .status("200")
                            .message("success")
                            .description("succ")
                            .content(dto)
                            .build());
            return response;
        }
        /*
            Throw not found exception here; Handle with global exception handler
         */
        return ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder().status("404").message("not found").description("not found").content(null).build());
    }

    @PostMapping("users")
    @Operation(
            summary = "Create bank user",
            description = "Creates a bank user, given required user data"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType ="application/json", schema = @Schema(implementation = ResponseWrapper.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType ="application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<ResponseWrapper<BankUserDto>> createNewUser(@RequestBody BankUserRequest request){
        var results = bankUserService.createNewBankUser(request);
        if(results != null){
            var response = ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder()
                    .status("200")
                    .message("OK")
                    .description("Successfully created new user with id: " + results.getId())
                    .content(results)
                    .build());
            return response;
        }
        else{
            System.out.println("Error: Failed to create new entity");
            return ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder().status("400").message("bad request").description("failed to create new entity").content(null).build());
        }
    }
    @PatchMapping("users/{userId}")
    @Operation(
            summary = "Updates bank user info",
            description = "Updates bank user info, given replacement data and a valid user id."
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType ="application/json", schema = @Schema(implementation = ResponseWrapper.class))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType ="application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<ResponseWrapper<BankUserDto>> updateUser(@RequestBody BankUserRequest request, @PathVariable Long userId){
    var results = bankUserService.updateBankUser(request, userId);
        if(results != null){
            var response = ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder()
                    .status("200")
                    .message("OK")
                    .description("Successfully updated user with id: " + results.getId())
                    .content(results)
                    .build());
            return response;
        }
        else{
            System.out.println("Error: Failed to create new entity");
            return ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder().status("400").message("bad request").description("failed to create new entity").content(null).build());
        }
  }
//




   }
