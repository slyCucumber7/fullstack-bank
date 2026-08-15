package com.example.userservice.entity.accountTransaction;


import com.example.userservice.common.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("account-transaction/")
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;
    @PostMapping
    public ResponseEntity<ResponseWrapper<AccountTransactionResponse>> incomingTransaction(@Valid @RequestBody AccountTransactionRequest request) {
        /*
            Logic implementing the transaction (Use AccountTransactionRequest)
        */

        AccountTransactionResponse response = accountTransactionService.transactionProcess(request);

        ResponseWrapper<AccountTransactionResponse> wrapper = ResponseWrapper.<AccountTransactionResponse>builder()
                .message("Okie Dokie")
                .status("200")
                .description("Transaction Complete")
                .content(response)
                .build();

        return ResponseEntity.ok(wrapper);
    }
}
