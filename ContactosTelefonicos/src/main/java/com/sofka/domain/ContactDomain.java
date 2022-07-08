package com.sofka.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Entity
@Table(name = "contact")
public class ContactDomain implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Long id;

    @Column(name = "con_full_name")
    private String fullName;

    @Column(name = "con_phone")
    private String phone;

    @Column(name = "con_email")
    private String email;

    @Column(name = "con_birthday")
    private String birthday;
}
