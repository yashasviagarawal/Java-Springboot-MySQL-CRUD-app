package com.yash.SimpleCRUD.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Students")
@AllArgsConstructor
@NoArgsConstructor
public class Students {

    @Id
    private Integer id;
    @Column(name="NAME")
    private String name;
    @Column(name="AGE")
    private Integer age;
    @Column(name="PHONE_NUMBER")
    private Long phoneNumber;
    @Column(name="EMAIL")
    private String email;
}
