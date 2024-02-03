package Javastral.com.gestorMateriasWeb.model.entity;

import java.util.Set;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    
    public UserEntity(String username, String email, String password) {
    	this.username = username;
    	this.email = email;
    	this.password = password;
    }
}
