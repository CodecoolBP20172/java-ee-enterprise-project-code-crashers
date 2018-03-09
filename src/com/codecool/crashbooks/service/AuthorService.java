package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Author;
import com.codecool.crashbooks.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findOne(id);
    }

    public List<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
}
