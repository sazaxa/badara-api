package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Table(name ="zx_member")
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column
    private String name;

    @Column
    private String status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Builder
    public Member(String email, String password, String phoneNumber, String name, String status, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.status = status;
        this.roles = roles;
    }

    public void updateMember(String email, String password, String phoneNumber, String name){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public void updateStatus(String status){
        this.status = status;
    }

}
