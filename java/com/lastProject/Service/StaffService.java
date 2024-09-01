package com.lastProject.Service;

import com.lastProject.Entity.Staff;
import com.lastProject.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void saveStaff(Staff staff){
        staff.setStaffPassword(passwordEncoder.encode(staff.getStaffPassword()));
        staff.setJoinedDate(new Date());
        staffRepository.save(staff);
    }

    public Staff findByEmail(String email){
        return staffRepository.findByStaffEmail(email).get();
    }

    public List<Staff> getAllStaff(){
        return staffRepository.findAll();
    }
}
