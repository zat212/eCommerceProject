package com.lastProject.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int staffId;
    @Column(nullable = false, unique = true)
    private String staffName;
    @Column(nullable = false, unique = true)
    private String staffEmail;
    @Column(nullable = false, unique = true)
    private String staffPassword;
    @Column(nullable = false)
    private String staffRole;
    private Date joinedDate;



    public Staff() {
    }

    public Staff(String staffName, String staffEmail, String staffPassword, String staffRole, Date joinedDate) {
        this.staffName = staffName;
        this.staffEmail = staffEmail;
        this.staffPassword = staffPassword;
        this.staffRole = staffRole;
        this.joinedDate = joinedDate;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }



    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", staffEmail='" + staffEmail + '\'' +
                ", staffPassword='" + staffPassword + '\'' +
                ", staffRole='" + staffRole + '\'' +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
