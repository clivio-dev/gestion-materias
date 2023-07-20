package Javastral.com.gestorMateriasWeb.web;

import Javastral.com.gestorMateriasWeb.model.UserRepository;
import Javastral.com.gestorMateriasWeb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setPassedSubjectIds(userRepository.getPassedSubjects(userId));
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        for (User user: users) {
            user.setPassedSubjectIds(userRepository.getPassedSubjects(user.getId()));
        }
        return (users.isEmpty())?ResponseEntity.notFound().build():ResponseEntity.ok(users);
    }
}
