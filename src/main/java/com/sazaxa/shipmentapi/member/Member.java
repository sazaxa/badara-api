package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
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

    @Column
    private Double point;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Builder
    public Member(Long id, String email, String password, String phoneNumber, String name, String status,
                  Double point, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.status = status;
        this.point = point;
        this.roles = roles;
    }

    public void updateMember(String password, String phoneNumber, String name){
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    public void updatePoint(Double point){
        this.point = point;
    }

}
