package com.example.biblioteka.services;

import com.example.biblioteka.entity.Writing;
import com.example.biblioteka.repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WritingService {

    @Autowired
    private WritingRepository writingRepository;

    public List<Writing> findAllWritings() {
        return writingRepository.findAll();
    }

    public Optional<Writing> findWritingById(Integer id) {
        return writingRepository.findById(id);
    }

    public Writing saveWriting(Writing writing) {
        return writingRepository.save(writing);
    }

    public void deleteWriting(Integer id) {
        writingRepository.deleteById(id);
    }

    public Writing updateWriting(Integer id, Writing writing) {
        return writingRepository.findById(id)
                .map(existingWriting -> {
                    existingWriting.setName(writing.getName());
                    existingWriting.setDescription(writing.getDescription());
                    existingWriting.setDateOfPublication(writing.getDateOfPublication());
                    existingWriting.setLocalAddressStoring(writing.getLocalAddressStoring());
                    return writingRepository.save(existingWriting);
                })
                .orElseThrow(() -> new RuntimeException("Writing not found with id " + id));
    }
}
