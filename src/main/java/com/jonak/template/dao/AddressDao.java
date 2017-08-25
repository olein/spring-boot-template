package com.jonak.template.dao;

import com.jonak.template.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

@Repository
@EnableAsync
public interface AddressDao extends CrudRepository<Address, Integer> {
}
