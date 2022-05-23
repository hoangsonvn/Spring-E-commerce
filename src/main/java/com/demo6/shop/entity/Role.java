package com.demo6.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column
    private String title;
    @OneToMany(mappedBy = "role")
    private List<User> users;
    @Column
    private Boolean status;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "role_permission",joinColumns = @JoinColumn(name ="role_id" ),
            inverseJoinColumns =@JoinColumn(name = "permission_id"))
    private List<Permission> permissions = new ArrayList<>() ;

}
