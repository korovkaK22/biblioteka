package com.example.biblioteka.services;

import com.example.biblioteka.entity.Writing;
import com.example.biblioteka.repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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


    private BufferedReader getDescriptionReader(int id) throws IOException {
        Optional<Writing> writingOpt = findWritingById(id);
        if (writingOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Writing writing = writingOpt.get();
        String address = writing.getLocalAddressStoring();
        return new BufferedReader(new FileReader(address));

    }


    public String getPaginatedText(int id, int page, int size) throws IOException {
        try (BufferedReader reader = getDescriptionReader(id)) {
            String text = reader.lines().collect(Collectors.joining("\n"));

            int currPage = 0;
            int startIndex = -1;
            int endIndex = 0;

            while (text.length() + size > endIndex) {
                endIndex += size;
                while (endIndex < text.length() && text.charAt(endIndex) != '\n') {
                    endIndex++;
                }
                currPage++;


                if (currPage == page) {
                    if (endIndex > text.length()) {
                        endIndex = text.length();
                    }

                    return text.substring(startIndex + 1, endIndex);
                }

                startIndex = endIndex;
            }

            throw new IndexOutOfBoundsException("This page is to great. Only %s are available".formatted(currPage));
        }
    }

    public List<Writing> findWithOptions(String query, List<Integer> genreIds) {
        List<Writing> writings = writingRepository.findAll();
        List<Writing> result = new ArrayList<>();

        for (Writing writing : writings) {

            if (genreIds != null && !writing.getGenres().stream().map(Genre::getId).toList().containsAll(genreIds)) {
                continue;
            }

            if (query != null && !writing.getName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                continue;
            }
            result.add(writing);
        }

        return result;
    }

    public int getTotalPages(int id, int size) throws IOException {
        try (BufferedReader reader = getDescriptionReader(id)) {
            String text = reader.lines().collect(Collectors.joining("\n"));

            if (text.length() < size) {
                return 1;
            }

            int currPage = -1;
            int endIndex = 0;

            while (text.length() + size > endIndex) {
                endIndex += size;
                while (endIndex < text.length() && text.charAt(endIndex) != '\n') {
                    endIndex++;
                }
                currPage++;
            }

            statisticService.addViewsToWritingStatistic(id);

            return currPage;
        }
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
