package com.github.slycucumber7.web_server;
import org.springframework.data.annotation.Id;
//record BankCustomer(@Id long id, Double balance){

//}
class BankCustomer{
    @Id long id;

    Double balance;

    BankCustomer(long id, Double balance){
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }
}

