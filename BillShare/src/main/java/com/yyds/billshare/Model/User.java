package com.yyds.billshare.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer uid;

    private String firstname;
    private String lastname;

    private String nickname;
    private String email;
    private String avatar;
    private String password;
    private Long tel;

}