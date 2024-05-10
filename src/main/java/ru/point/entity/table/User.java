package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "telephone_id", nullable = false, unique = true)
    String username;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "second_name", nullable = false)
    String secondName;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "isLocked", nullable = false)
    Boolean isAccountLocked = false;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "secret", nullable = false)
    String secret;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name = "authorities")
    Set<Authority> authorities;

    @OneToOne(cascade = CascadeType.ALL)
    Cart cart;

    @ManyToMany(fetch = FetchType.LAZY)
    Set<Product> favorites;

    @OneToMany(targetEntity = Review.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    Set<Review> reviews;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    Set<Order> orders;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isAccountNonLocked() && isCredentialsNonExpired() && isAccountNonExpired();
    }
}
