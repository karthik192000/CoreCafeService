package com.core.cafe.service.model;


import javax.persistence.*;

@Entity
@Table(name = "employee",schema = "cafe")
public class Employee {


    @Id
    @SequenceGenerator(name = "employee_id_sequence", sequenceName = "cafe.employee_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_sequence")
    private Integer employeeid;

    private String employeename;

    private String role;

    private String emailid;

    private String passwrd;


    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }
}
