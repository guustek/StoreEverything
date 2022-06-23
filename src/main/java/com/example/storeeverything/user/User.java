package com.example.storeeverything.user;

import com.example.storeeverything.information.Information;
import com.example.storeeverything.registration.token.ConfirmationToken;
import com.example.storeeverything.validation.email_not_taken.EmailNotTaken;
import com.example.storeeverything.validation.first_letter_uppercase.FirstLetterUpperCase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Email(message = "Must be a properly formatted e-mail address")
    @NotBlank(message = "Must not be empty")
    @EmailNotTaken
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Basic
    @NotBlank(message = "Must not be empty")
    @Size(min = 5, message = "Must be longer than 4 letters")
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @NotBlank(message = "Must not be empty")
    @FirstLetterUpperCase
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @NotBlank(message = "Must not be empty")
    @FirstLetterUpperCase
    @Column(name = "surname", nullable = false)
    private String surname;

    @Basic
    @Column(name = "age", nullable = false)
    @DecimalMin(value = "18", message = "Must be 18 or older")
    private int age;
    @Basic
    @Column(name = "role", nullable = false, columnDefinition = "varchar(255) default 'ROLE_LIMITED_USER'")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_LIMITED_USER;
    @Basic
    @Column(name = "locked", columnDefinition = "boolean default false")
    private Boolean locked = false;
    @Basic
    @Column(name = "enabled", columnDefinition = "boolean default false")
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<Information> informations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<ConfirmationToken> confirmationTokens;

    //security things
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
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
        return ! locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
