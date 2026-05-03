package com.example.userservice.BankUser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankUserService {
    private final BankUserRepository repository;

    public BankUserDto getUserFromId(Long id){
        return repository.getUserById(id);
    }

    public BankUserDto createNewBankUser(BankUserCreationRequest request){
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


}
