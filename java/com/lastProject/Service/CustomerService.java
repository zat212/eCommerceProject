package com.lastProject.Service;

import com.lastProject.Entity.Customer;
import com.lastProject.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void saveCustomer(Customer customer){
        customer.setCusPassword(passwordEncoder.encode(customer.getCusPassword()));
        customer.setRegisteredDate(new Date());
        customerRepository.save(customer);
    }

    public Customer findByemail(String email){
        return customerRepository.findByCusEmail(email).get();
    }

    public Customer findById(int cusId){
        return customerRepository.findById(cusId).get();
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
}
