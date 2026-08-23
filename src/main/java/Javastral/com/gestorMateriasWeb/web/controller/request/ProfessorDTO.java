package Javastral.com.gestorMateriasWeb.web.controller.request;

import Javastral.com.gestorMateriasWeb.model.proyection.ProfessorBasicProjection;

import java.util.List;

public record ProfessorDTO(
        long id,
        String fullName,
        Long departmentId,
        String departmentName
) {
    public static ProfessorDTO fromProjection(ProfessorBasicProjection projection) {
        return new ProfessorDTO(
                projection.getId(),
                projection.getFullName(),
                projection.getDepartmentId(),
                projection.getDepartmentName()
        );
    }

    public static List<ProfessorDTO> fromProjection(List<ProfessorBasicProjection> projections) {
        return projections.stream()
                .map(ProfessorDTO::fromProjection)
                .toList();
    }
}
