package Javastral.com.gestorMateriasWeb.init;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import Javastral.com.gestorMateriasWeb.model.entity.ERole;
import Javastral.com.gestorMateriasWeb.model.entity.Role;
import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;

@Component
public class UserInit implements CommandLineRunner{
	
	@Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		UserEntity userEntity = UserEntity.builder()
                .email("alexis@gmail.com")
                .username("alexis")
                .password(passwordEncoder.encode("1234"))
                .roles(Set.of(Role.builder()
                        .name(ERole.valueOf(ERole.ADMIN.name()))
                        .build()))
                .build();
        UserEntity userEntity2 = UserEntity.builder()
                .email("lucas@gmail.com")
                .username("lucas")
                .password(passwordEncoder.encode("4321"))
                .roles(Set.of(Role.builder()
                        .name(ERole.valueOf(ERole.USER.name()))
                        .build()))
                .build();
        UserEntity userEntity3 = UserEntity.builder()
                .email("gonza@gmail.com")
                .username("gonza")
                .password(passwordEncoder.encode("1122"))
                .roles(Set.of(Role.builder()
                        .name(ERole.valueOf(ERole.GUEST.name()))
                        .build()))
                .build();

        userRepository.save(userEntity);
        userRepository.save(userEntity2);
        userRepository.save(userEntity3);
	}
}
