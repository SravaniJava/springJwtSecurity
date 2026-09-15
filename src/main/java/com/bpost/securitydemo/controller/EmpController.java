package com.bpost.securitydemo.controller;

import com.bpost.securitydemo.entity.EmpEntity;
import com.bpost.securitydemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
    @Autowired
    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @PostMapping("/regEmp")
    public ResponseEntity<EmpEntity> registerEmp(@RequestBody EmpEntity empEntity){
        EmpEntity  empEntity1= empService.saveEmp(empEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(empEntity1);


    }
}
