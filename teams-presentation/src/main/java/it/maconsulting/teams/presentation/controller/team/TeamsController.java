package it.maconsulting.teams.presentation.controller.team;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = {"Teams"})
@RequestMapping("teams")
public class TeamsController {

    /**
     * Get a list of the existing teams
     * @return
     */
    @GetMapping
    public List<Object> list() {
        return null;
    }

    /**
     * Create an empty team
     * @return
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody String name) {

        return ResponseEntity.ok("OK");
    }

}
