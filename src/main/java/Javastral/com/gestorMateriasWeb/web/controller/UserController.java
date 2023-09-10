package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.entity.UserEntity;
import Javastral.com.gestorMateriasWeb.web.controller.request.SubjectUpdateDTO;
import Javastral.com.gestorMateriasWeb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    //TODO: sacar el id del token
    @PostMapping(value = "/update")
    public ResponseEntity<String> updateSubjects(@RequestBody SubjectUpdateDTO data ){
        Optional<UserEntity> userOp = userRepository.findById(data.getUserId());
        if(userOp.isPresent()){
            UserEntity user = userOp.get();

            Map<Long,Integer> newPassedSubjects = data.getSave();
            List<Long> deletePassedSubjects = data.getDelete();

            deletePassedSubjects.forEach(user.getPassedSubjects()::remove);
            newPassedSubjects.forEach(user.getPassedSubjects()::put);
            userRepository.save(user);

            return ResponseEntity.ok("Update successful");
        }
        return ResponseEntity.badRequest().body("Update couldn't be performed (user id dont exist)");
    }

}
