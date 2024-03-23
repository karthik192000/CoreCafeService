package com.core.cafe.service.model;


import javax.persistence.*;

@Entity
@Table(name = "employee",schema = "cafe")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "cafe.customer_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
    private Integer customerid;

    private String customername;

    private Long mobile;


    private String credentials;

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }
}
