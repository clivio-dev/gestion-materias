package Javastral.com.gestorMateriasWeb.model.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 40)
    @Column(length = 40)
    @NaturalId
    private String username;

    @Email
    @NotBlank
    @Size(max = 40)
    @Column(length = 40)
    @NaturalId
    private String email;

    @NotBlank
    @Size(min = 4)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class,
            cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ElementCollection
    @CollectionTable(
        name = "user_passed_subjects",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @MapKeyJoinColumn(name = "subject_id")
    @Column(name = "grade")
    private Map<Subject, Integer> passedSubjects = new HashMap<>();
    
    public UserEntity(String username, String email, String password) {
    	this.username = username;
    	this.email = email;
    	this.password = password;
    }

    public void addPassedSubject(Subject subject, int grade) {
            passedSubjects.put(subject, grade);
    }

    public void removePassedSubject(Subject subject) {
            passedSubjects.remove(subject);
    }

    public boolean hasPassedSubject(Subject subject) {
            return passedSubjects.containsKey(subject);
    }
}
