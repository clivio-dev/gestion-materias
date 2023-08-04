package Javastral.com.gestorMateriasWeb.web;

import Javastral.com.gestorMateriasWeb.model.proyection.PassedSubjectScoreProjection;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.model.User;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public UserController(UserRepository userRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<PassedSubjectScoreProjection> passedSubjects = subjectRepository.getPassedSubjectsScoreByUserId(userId);
            user.setPassedSubjects(passedSubjects);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }


}
