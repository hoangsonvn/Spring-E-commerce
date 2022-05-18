package com.demo6.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Getter
@Setter
public class Permission  {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permissionName;

    private String permissionKey;

}