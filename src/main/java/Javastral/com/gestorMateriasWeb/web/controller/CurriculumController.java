package Javastral.com.gestorMateriasWeb.web.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumIdNameProy;
import Javastral.com.gestorMateriasWeb.model.proyection.CurriculumWithSubjectsProy;
import Javastral.com.gestorMateriasWeb.web.controller.response.ApiResponse;
import Javastral.com.gestorMateriasWeb.web.controller.response.ErrorData;
import Javastral.com.gestorMateriasWeb.web.controller.response.MetaData;
import Javastral.com.gestorMateriasWeb.web.controller.response.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Javastral.com.gestorMateriasWeb.model.entity.Curriculum;
import Javastral.com.gestorMateriasWeb.model.entity.Subject;
import Javastral.com.gestorMateriasWeb.model.repository.CurriculumRepository;
import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import Javastral.com.gestorMateriasWeb.web.controller.request.CurriculumDTO;
import Javastral.com.gestorMateriasWeb.web.controller.request.SubjectDTO;


@RestController
@RequestMapping("/curriculum")
@CrossOrigin(origins = "*", maxAge = 3600) // TODO: para dev, aplicar configuracion de cors por db o env
public class CurriculumController {
    private final CurriculumRepository curriculumRepository;
    private final SubjectRepository subjectRepository;
    @Autowired
    public CurriculumController(CurriculumRepository curriculumRepository, SubjectRepository subjectRepository) {
        this.curriculumRepository = curriculumRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/{curriculumId}")
    ResponseEntity<ApiResponse<CurriculumDTO>> getCurriculumById(@PathVariable String curriculumId) {
        Optional<CurriculumWithSubjectsProy> curriculumOpt = curriculumRepository.findCurriculumWithSubjectsById(Long.parseLong(curriculumId));
        
        if (curriculumOpt.isPresent()) {
            CurriculumDTO curriculumDTO = new CurriculumDTO(
                curriculumOpt.get().getId(),
                curriculumOpt.get().getName(),
                curriculumOpt.get().getSubjects().stream()
                    .map(SubjectDTO::fromProjection)
                    .collect(Collectors.toSet())
            );
            
            ApiResponse<CurriculumDTO> response = ApiResponse.<CurriculumDTO>builder()
                .data(curriculumDTO)
                .meta(MetaData.builder()
                    .pagination(PaginationData.builder()
                        .page(1)
                        .pageSize(1)
                        .total(1)
                        .build())
                    .build())
                .errors(null)
                .build();
                
            return ResponseEntity.ok(response);
        }

        ApiResponse<CurriculumDTO> errorResponse = ApiResponse.<CurriculumDTO>builder()
            .data(null)
            .meta(null)
            .errors(ErrorData.builder()
                .message("Curriculum not found")
                .code("404")
                .build())
            .build();
            
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @GetMapping("/all")
    ResponseEntity<List<CurriculumIdNameProy>> getAllCurriculums(){
        return ResponseEntity.ok(curriculumRepository.getCurriculumProy());
    }

    @PostMapping
    ResponseEntity<String> saveCurriculum(@RequestBody CurriculumDTO curriculumDTO) {

        if(curriculumRepository.existsById(curriculumDTO.getId()))
            return ResponseEntity
                    .badRequest()
                    .body("The curriculum with ID: " + curriculumDTO.getId() + " already exists.");

        Map<Long, Subject> subjects = curriculumDTO.getSubjects().stream().collect(
                Collectors.toMap(
                        SubjectDTO::getId,
                        subjectDTO -> new Subject(subjectDTO.getId(), subjectDTO.getName())));

        for (SubjectDTO subjectDTO : curriculumDTO.getSubjects()) {

            Set<Long> prerequisites = new HashSet<>();

            for (Long idPrerequisiteDTO : subjectDTO.getPrerequisites()) {

                if (idPrerequisiteDTO != subjectDTO.getId() && subjects.containsKey(idPrerequisiteDTO)) {
                    prerequisites.add(idPrerequisiteDTO);
                } else {
                    return ResponseEntity
                            .badRequest()
                            .body("Invalid Prerequisite with ID: " + idPrerequisiteDTO);
                }
            }
            subjects.get(subjectDTO.getId()).setPrerequisiteSubjects(prerequisites);
        }

        Curriculum newCurriculum = new Curriculum(
                curriculumDTO.getId(),
                curriculumDTO.getName(),
                new HashSet<>(subjects.values()));

        for(Subject s : subjects.values()){
            if(!subjectRepository.existsById(s.getId()))
                subjectRepository.save(s);
        }
        curriculumRepository.save(newCurriculum);

        return ResponseEntity.ok("The new curriculum was saved successfully.");
    }

}




