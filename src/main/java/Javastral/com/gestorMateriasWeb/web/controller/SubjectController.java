package Javastral.com.gestorMateriasWeb.web.controller;

import Javastral.com.gestorMateriasWeb.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;


}
