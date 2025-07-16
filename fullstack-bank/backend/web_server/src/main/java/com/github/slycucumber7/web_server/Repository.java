package com.github.slycucumber7.web_server;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface Repository extends CrudRepository<User,Long> {
    User findByIdAndOwner(Long id,String owner);

    User findUserById(Long id);

}
