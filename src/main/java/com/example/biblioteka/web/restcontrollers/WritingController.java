package com.example.biblioteka.web.restcontrollers;

import com.example.biblioteka.dto.WritingsDto;
import com.example.biblioteka.entity.Writing;
import com.example.biblioteka.services.WritingService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/writings")
public class WritingController {

    @Autowired
    private WritingService writingService;

    @GetMapping
    public List<WritingsDto> getAllWritings() {
        return writingService.findAllWritings().stream().map(WritingsDto::new).toList() ;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Writing> getWritingById(@PathVariable Integer id) {
//        return writingService.findWritingById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPageWritingById(@PathVariable Integer id,
                                                     @RequestParam(defaultValue = "1") @Min(1) int page,
                                                     @RequestParam(defaultValue = "2000") @Min(1) int size) {
        if (page <= 0) {
            return ResponseEntity.status(400).build();
        }

        if (writingService.findWritingById(id).isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        try {
            return ResponseEntity.ok().body(writingService.getPaginatedText(id, page, size));
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(400).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<WritingsDto>> getWritingsByOptions(
           @RequestParam() String query,   @RequestParam(required=false) List<Integer> genres)  {

      return ResponseEntity.ok(writingService.findWithOptions(query, genres).stream().map(WritingsDto::new).toList());

    }


    @GetMapping("/total-pages/{id}")
    public ResponseEntity<Integer> getPageSizeById(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "2000") @Min(1) int size)  {

        if (writingService.findWritingById(id).isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        try {
            return ResponseEntity.ok().body(writingService.getTotalPages(id, size));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

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
