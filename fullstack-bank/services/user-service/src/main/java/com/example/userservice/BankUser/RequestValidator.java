package com.example.userservice.BankUser;

import com.example.userservice.common.exception.BadRequestException;

public class RequestValidator {

    public static boolean validateUserCreationRequest(BankUserRequest request){
        if(request == null){
            throw new BadRequestException("Bad Request. Reason: BankUserRequest null");
        }
        if (request.getNameF() == null || request.getNameF().isBlank() || request.getNameF().trim().length() > 30) {
            throw new BadRequestException("Bad Request. Reason: nameF field failed validation checks.");
        }
        if (request.getNameL() == null || request.getNameL().isBlank() || request.getNameL().trim().length() > 60) {
            throw new BadRequestException("Bad Request. Reason: nameL field failed validation checks.");
        }
        if (request.getAddrStreet() == null || request.getAddrStreet().isBlank() || request.getAddrSt().trim().length() > 32) {
            throw new BadRequestException("Bad Request. Reason: addrStreet field failed validation checks.");
        }
        if (request.getAddrCity() == null || request.getAddrCity().isBlank() || request.getAddrCity().trim().length() > 32) {
            throw new BadRequestException("Bad Request. Reason: addrCity field failed validation checks.");
        }
        if (request.getAddrSt() == null || request.getAddrSt().isBlank() || request.getAddrSt().trim().length() != 2) {
            throw new BadRequestException("Bad Request. Reason: addrSt field failed validation checks.");
        }
        if (request.getAddrZip() == null || request.getAddrZip().isBlank() || request.getAddrZip().trim().length() > 9) {
            throw new BadRequestException("Bad Request. Reason: addrZip field failed validation checks.");
        }
        if (request.getPhone() == null || request.getPhone().isBlank() || request.getPhone().trim().length() > 15) {
            throw new BadRequestException("Bad Request. Reason: phone field failed validation checks.");
        }
        if (request.getEmail() == null || request.getEmail().isBlank() || request.getEmail().trim().length() > 100) {
            throw new BadRequestException("Bad Request. Reason: email field failed validation checks.");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()){
            /*
                TODO: impose size limit on password
             */
            throw new BadRequestException("Bad Request. Reason: password field failed validation checks.");
        }
        return true;
    }
}
