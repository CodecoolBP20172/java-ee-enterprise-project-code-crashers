package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    List<Media> findByGenres_Id(int genreId);

    @Transactional
    List<Media> findByCategory_Id(int categoryId);

    List<Media> findByAuthor_Id(int authorId);

    List<Media> findByGenres_IdAndCategory_Id(int genreId, int categoryId);
}
