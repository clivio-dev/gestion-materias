package Javastral.com.gestorMateriasWeb.web.controller;
import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumIdNameProy;
import Javastral.com.gestorMateriasWeb.model.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/curriculum")
public class CurriculumController {
    private final CurriculumRepository curriculumRepository;
    @Autowired
    public CurriculumController(CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    @GetMapping("/{curriculumId}")
    ResponseEntity<Curriculum> getCurriculumById(@PathVariable String curriculumId) {
        Optional<Curriculum> curriculum = curriculumRepository.findById(curriculumId);
        return curriculum.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    ResponseEntity<List<CurriculumIdNameProy>> getAllCurriculums(){
        return ResponseEntity.ok(curriculumRepository.getCurriculumProy());
    }
}




