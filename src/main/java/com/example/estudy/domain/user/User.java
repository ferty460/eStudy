package com.example.estudy.domain.user;

import com.example.estudy.domain.answer.GapsTaskAnswer;
import com.example.estudy.domain.answer.SortTaskAnswer;
import com.example.estudy.domain.answer.TestAnswer;
import com.example.estudy.domain.answer.TextTaskAnswer;
import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.news.News;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String patronymic;

    @Column(unique = true, nullable = false)
    private String username;

    private Gender gender;

    private Integer age;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "author")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "author")
    private List<News> news = new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    private Set<Course> followedCourses = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Course> favoriteCourses = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<TextTaskAnswer> textAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TestAnswer> testAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SortTaskAnswer> sortAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<GapsTaskAnswer> gapsAnswers = new ArrayList<>();

    @Override
    public String toString() {
        return surname + " " + name.charAt(0) + ". " + (patronymic == null ? "" : patronymic.charAt(0) + '.');
    }

    public String toFullName() {
        return surname + " " + name + " " + (patronymic == null ? "" : patronymic);
    }

    /* --------- SECURITY --------- */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
