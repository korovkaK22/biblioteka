package com.example.biblioteka.web.restcontrollers;

import com.example.biblioteka.entity.Writing;
import com.example.biblioteka.services.WritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/writings")
public class WritingController {

    @Autowired
    private WritingService writingService;

    @GetMapping
    public List<Writing> getAllWritings() {
        return writingService.findAllWritings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Writing> getWritingById(@PathVariable Integer id) {
        return writingService.findWritingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Writing createWriting(@RequestBody Writing writing) {
        return writingService.saveWriting(writing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Writing> updateWriting(@PathVariable Integer id, @RequestBody Writing writingDetails) {
        try {
            Writing updatedWriting = writingService.updateWriting(id, writingDetails);
            return ResponseEntity.ok(updatedWriting);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWriting(@PathVariable Integer id) {
        writingService.deleteWriting(id);
        return ResponseEntity.ok().build();
    }
}
