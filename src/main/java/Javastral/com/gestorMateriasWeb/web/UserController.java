package Javastral.com.gestorMateriasWeb.web;

import Javastral.com.gestorMateriasWeb.model.*;
import Javastral.com.gestorMateriasWeb.model.proyection.PassedSubjectProyectionIdScore;
import Javastral.com.gestorMateriasWeb.model.from.UserSubjectUpdateForm;
import Javastral.com.gestorMateriasWeb.model.repository.PassedSubjectRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final PassedSubjectRepository passedSubjectRepo;


    @Autowired
    public UserController(UserRepository userRepository, SubjectRepository subjectRepository, PassedSubjectRepository passedSubjectRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.passedSubjectRepo = passedSubjectRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<PassedSubjectProyectionIdScore> passedSubjects = subjectRepository.getPassedSubjectsScoreByUserId(userId);
            user.setPassedSubjects(passedSubjects);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<String> updateSubjects(@RequestBody UserSubjectUpdateForm form ){
        if(userRepository.existsById(form.getUserId())){
            List<PassedSubject> newPassedSubjects = form.getSave().stream()
                    .map( PassesSubjectScoreProjection -> new PassedSubject(form.getUserId(),PassesSubjectScoreProjection.getSubjectId(),PassesSubjectScoreProjection.getScore())).toList();
            List<PassedSubjectKey> deletePassedSubjects = form.getDelete().stream()
                    .map( String -> new PassedSubjectKey(form.getUserId(),String)).toList();

            passedSubjectRepo.saveAll(newPassedSubjects);
            passedSubjectRepo.deleteAllById(deletePassedSubjects);

            return ResponseEntity.ok("Update successful");
        }
        return ResponseEntity.badRequest().body("Update couldn't be performed (user id dont exist)");
    }

}
