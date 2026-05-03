package com.example.userservice.BankUser;

import com.example.userservice.common.ResponseWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("user-service/")
@RestController
public class BankUserController {

    private final BankUserService bankUserService;

    @GetMapping("users/{userId}")
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
    public ResponseEntity<ResponseWrapper<BankUserDto>> createNewUser(@RequestBody BankUserCreationRequest request){
        var results = bankUserService.createNewBankUser(request);
        if(results != null){
            var response = ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder()
                    .status("200")
                    .message("success")
                    .description("succ")
                    .content(results)
                    .build());
            return response;
        }
        else{
            System.out.println("Error: Failed to create new entity");
            return ResponseEntity.ok(ResponseWrapper.<BankUserDto>builder().status("400").message("bad request").description("failed to create new entity").content(null).build());
        }
    }



}
