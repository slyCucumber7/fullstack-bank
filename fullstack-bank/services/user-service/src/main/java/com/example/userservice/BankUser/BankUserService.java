package com.example.userservice.BankUser;

import com.example.userservice.common.exception.BadRequestException;
import com.example.userservice.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankUserService {
    private final BankUserRepository repository;

    public BankUserDto getUserFromId(Long id){
        var user = repository.getUserById(id);
        if(user == null){
            throw new NotFoundException("User with id: " + id + " not found.");
        }
        return user;
    }

    public BankUserDto createNewBankUser(BankUserRequest request){
        BankUser newUser = BankUser.builder()
                .id(null)
                .nameF(request.getNameF())
                .nameM(request.getNameM())
                .nameL(request.getNameL())
                .addrStreet(request.getAddrStreet())
                .addrCity(request.getAddrCity())
                .addrSt(request.getAddrSt())
                .addrZip(request.getAddrZip())
                .phone(request.getPhone())
                .email(request.getEmail())
                .creationTs(null)
                .build();
        var result = repository.save(newUser);
        if(result == null){
            System.out.println("Failed to create new entity.");
        }
        return new BankUserDto(result);
    }

    public BankUserDto updateBankUser(BankUserRequest request, Long id){
        BankUser user = repository.findBankUserById(id);
        if(user == null){
            throw new NotFoundException("User with id: " + id + " not found.");
        }
        if (request.getNameF() != null && !request.getNameF().isBlank()) {
            user.setNameF(request.getNameF());
        }
        if (request.getNameM() != null ) {
            user.setNameM(request.getNameM());
        }
        if (request.getNameL() != null && !request.getNameL().isBlank()) {
            user.setNameL(request.getNameL());
        }
        if (request.getAddrStreet() != null && !request.getAddrStreet().isBlank()) {
            user.setAddrStreet(request.getAddrStreet());
        }
        if (request.getAddrCity() != null && !request.getAddrCity().isBlank()) {
            user.setAddrCity(request.getAddrCity());
        }
        if (request.getAddrSt() != null && !request.getAddrSt().isBlank()) {
            user.setAddrSt(request.getAddrSt());
        }
        if (request.getAddrZip() != null && !request.getAddrZip().isBlank()) {
            user.setAddrZip(request.getAddrZip());
        }
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            user.setPhone(request.getPhone());
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }
//        if(request.getPassword() != null && Validator.isValidPassword(request.getPassword)){
//            !--UPDATE PASSWORD HASH--!
//        }
        /*
            TODO: Implement password update logic once Spring Security is implemented
         */
            BankUserDto results = new BankUserDto(repository.save(user));
            if(results == null){
                throw new BadRequestException("Failed to update user with id: " + id);
            }
            return results;
    }


}
