package com.bpost.securitydemo.service;

import com.bpost.securitydemo.entity.EmpEntity;
import com.bpost.securitydemo.repository.EmpRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpService implements UserDetailsService {

    private final  EmpRepository empRepository;

    public EmpService(EmpRepository empRepository, PasswordEncoder passwordEncoder) {
        this.empRepository = empRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;




    public  EmpEntity saveEmp(EmpEntity empEntity) {
        String pwdEncoder = empEntity.getPwd();

        empEntity.setPwd(passwordEncoder.encode(pwdEncoder));
       return  empRepository.save(empEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = empRepository.findByEName(username);
        System.out.println("Username = " + userDetails.getUsername());
        System.out.println("Password = " + userDetails.getPassword());

        return userDetails;
    }
}
