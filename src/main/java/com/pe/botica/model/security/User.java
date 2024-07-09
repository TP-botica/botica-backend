package com.pe.botica.model.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pe.botica.model.DrugstoreProduct;
import com.pe.botica.model.DrugstoreService;
import com.pe.botica.model.Purchase;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "account_number")
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference(value = "role-user")
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "drugstore-product")
    private List<DrugstoreProduct> drugstoreProducts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "drugstore-service")
    private List<DrugstoreService> drugstoreServices;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customer-purchase")
    private List<Purchase> customerPurchases;
    @OneToMany(mappedBy = "drugstore", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "drugstore-purchase")
    private List<Purchase> drugstorePurchases;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == null) return null;
        if(role.getPermissions() == null) return null;

        return role.getPermissions().stream()
                .map(each -> each.getOperation().getName())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
