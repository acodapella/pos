package com.earthdaily.pos.api.contract.v1;

import com.earthdaily.pos.api.contract.v1.Pos;
import com.earthdaily.pos.api.contract.v1.PosApiContractV1;
import com.earthdaily.pos.data.PosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URI;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/v1/pos")
@EnableWebMvc
@RequiredArgsConstructor
public class PosController implements PosApiContractV1 {

    private final PosRepository repository;

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Pos newPos) {
        Optional<Pos> pos = repository.findById(id);
        if (pos.isPresent()) {
            Pos oldPos = pos.get();
            oldPos.setLng(newPos.getLng());
            oldPos.setLat(newPos.getLat());
            oldPos.setName(newPos.getName());
            try {
                repository.save(oldPos);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                log.error("Cannot update - id:" + id + ", body:" + newPos);
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Pos pos) {
        try {
            repository.save(pos);
            return ResponseEntity.created(URI.create("/" + pos.getId())).build();
        } catch (Exception e) {
            log.error("Cannot create - body:" + pos);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pos> get(@PathVariable String id) {
        Optional<Pos> pos = repository.findById(id);
        if (pos.isPresent()) {
            return ResponseEntity.ok(pos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
