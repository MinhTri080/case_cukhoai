package com.cg.model;


import com.cg.model.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "customers")
@Accessors (chain = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "full_name")
    private  String fullName;

    private  String phone;

    private String email;

    private String address;

    private boolean deleted;
    public CustomerDTO toCustomerDTO(){
        return new CustomerDTO()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                ;
    }
}
