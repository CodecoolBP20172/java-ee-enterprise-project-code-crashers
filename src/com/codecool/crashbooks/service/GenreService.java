package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Genre;
import com.codecool.crashbooks.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(int id) {
        return genreRepository.findOne(id);
    }

    public List<Genre> getGenreByType(String type) {
        return genreRepository.findByType(type);
    }
}
