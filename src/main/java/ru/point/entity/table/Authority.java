package ru.point.entity.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", unique = true, nullable = false)
    String name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    @Column(name = "users")
    Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
