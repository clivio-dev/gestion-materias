package Javastral.com.gestorMateriasWeb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    /* estaria bueno implementar */
    @Autowired(required = false)
    private BuildProperties buildProperties;

    /* status general de la app */
    @GetMapping
    public ResponseEntity<Map<String, Object>> checkHealth() {
        Map<String, Object> healthInfo = new HashMap<>();
        healthInfo.put("status", "UP");

        if (buildProperties != null) {
            healthInfo.put("version", buildProperties.getVersion());
            healthInfo.put("buildTime", buildProperties.getTime());
        } else {
            healthInfo.put("version", "unknown");
        }

        healthInfo.put("database", checkDatabaseStatus());

        return ResponseEntity.ok(healthInfo);
    }

    /* mock */
    private String checkDatabaseStatus() {
        try {
            return "UP";
        } catch (Exception e) {
            return "DOWN";
        }
    }
}