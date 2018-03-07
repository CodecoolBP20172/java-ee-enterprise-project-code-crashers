package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.mediaProperty.Author;
import com.codecool.crashbooks.model.mediaProperty.Category;
import com.codecool.crashbooks.model.mediaProperty.Genre;
import com.codecool.crashbooks.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    MediaRepository mediaRepository;

    public void saveMedia(Media media) {
        mediaRepository.save(media);
    }

    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    public List<Media> getMediasBy(Genre genre) {
        return mediaRepository.findByGenres_Id(genre.getId());
    }

    public List<Media> getMediasBy(Category category) {
        return mediaRepository.findByCategory_Id(category.getId());
    }

    public List<Media> getMediasBy(Author author) {
        return mediaRepository.findByAuthor_Id(author.getId());
    }

    public List<Media> getMediasBy(Genre genre, Category category) {
        return mediaRepository.findByGenres_IdAndCategory_Id(genre.getId(), category.getId());
    }
}
