package com.github.slycucumber7.web_server;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface Repository extends CrudRepository<BankCustomer,Long> {
    @Modifying
    @Query("update bank_customer c set c.balance = :balance where c.id = :id")
    void updateBalance(@Param(value = "id") long id, @Param(value = "balance") Double balance);

}
