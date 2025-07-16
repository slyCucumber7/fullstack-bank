package com.github.slycucumber7.web_server;
import org.springframework.data.annotation.Id;
record User(@Id long id, Double balance, String owner){

}
//class User{
//    @Id long id;
//    Double balance;
//    String owner;
//
//    User(long id, Double balance, String owner){
//        this.id = id;
//        this.balance = balance;
//        this.owner = owner;
//    }
//}

