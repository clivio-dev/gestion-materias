package Javastral.com.gestorMateriasWeb.web;

import Javastral.com.gestorMateriasWeb.model.Subject;
import Javastral.com.gestorMateriasWeb.model.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/curriculum/{curriculumId}")
    ResponseEntity<Set<Subject>> getCurriculumById(@PathVariable String curriculumId){
        Set<Subject> subjects = subjectRepository.getCurriculumById(curriculumId);
        return ResponseEntity.ok(subjects);
    }

    

}
