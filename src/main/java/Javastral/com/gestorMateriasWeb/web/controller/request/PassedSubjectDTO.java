package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.proyection.PassedSubjectProjection;

import java.util.List;
import java.util.stream.Collectors;

public record PassedSubjectDTO(long id, int grade) {
    public static List<PassedSubjectDTO> fromProjection(List<PassedSubjectProjection> projections) {
        return projections.stream()
            .map(projection -> new PassedSubjectDTO(projection.subjectId(), projection.grade()))
            .collect(Collectors.toList());
    }
}
