package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "list", name = "t_users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false,name = "c_name")
    String name;

    @Column(name = "c_email")
    String email;

    @Column(name = "c_age")
    Integer age;
}
