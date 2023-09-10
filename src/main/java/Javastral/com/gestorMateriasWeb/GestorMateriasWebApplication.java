package Javastral.com.gestorMateriasWeb;

import Javastral.com.gestorMateriasWeb.model.entity.ERole;
import Javastral.com.gestorMateriasWeb.model.entity.RoleEntity;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@SpringBootApplication(scanBasePackages = "Javastral.com.gestorMateriasWeb")
public class GestorMateriasWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestorMateriasWebApplication.class, args);
    }

    /*
    Se guardan usuarios en la ddbb para testing
    */
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Bean
    CommandLineRunner init(){
        return args -> {
            UserEntity userEntity = UserEntity.builder()
                    .email("alexis@gmail.com")
                    .username("alexis")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(RoleEntity.builder()
                            .name(ERole.valueOf(ERole.ADMIN.name()))
                            .build()))
                    .build();
            UserEntity userEntity2 = UserEntity.builder()
                    .email("lucas@gmail.com")
                    .username("lucas")
                    .password(passwordEncoder.encode("4321"))
                    .roles(Set.of(RoleEntity.builder()
                            .name(ERole.valueOf(ERole.USER.name()))
                            .build()))
                    .build();
            UserEntity userEntity3 = UserEntity.builder()
                    .email("gonza@gmail.com")
                    .username("gonza")
                    .password(passwordEncoder.encode("1122"))
                    .roles(Set.of(RoleEntity.builder()
                            .name(ERole.valueOf(ERole.INVITER.name()))
                            .build()))
                    .build();

            userRepository.save(userEntity);
            userRepository.save(userEntity2);
            userRepository.save(userEntity3);
        };
    }


}

