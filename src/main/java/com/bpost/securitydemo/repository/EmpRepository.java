package com.bpost.securitydemo.repository;

import com.bpost.securitydemo.entity.EmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<EmpEntity,Integer> {
    public EmpEntity findByEName(String eName);
}
