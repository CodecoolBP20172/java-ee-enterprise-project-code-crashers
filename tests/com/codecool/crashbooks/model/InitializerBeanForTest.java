package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaProperty.*;
import com.codecool.crashbooks.service.*;
import com.codecool.crashbooks.utility.Password;
import org.springframework.stereotype.Component;

@Component
public class InitializerBeanForTest {


    public InitializerBeanForTest(AuthorService authorService, CategoryService categoryService, CopyService copyService,
                                  GenreService genreService, MediaService mediaService, MemberService memberService,
                                  RentService rentService) {

        Author testAuthor1 = Author.create("testAuthor1");
        Author testAuthor2 = Author.create("testAuthor2");
        Author testAuthor3 = Author.create("testAuthor3");
        Author testAuthor4 = Author.create("testAuthor4");

        Category book = Category.create(Categories.BOOK);
        Category magazine = Category.create(Categories.MAGAZINE);
        Category newspaper = Category.create(Categories.NEWSPAPER);

        Genre adventure = Genre.create(Genres.ADVENTURE);
        Genre mythology = Genre.create(Genres.MYTHOLOGY);
        Genre realism = Genre.create(Genres.REALISM);

        Media media1 = Media.create("testMedia1", testAuthor1, magazine, adventure, "pic.jpg", 1999, "A good book...");
        Media media2 = Media.create("testMedia2", testAuthor1, book, mythology, "pic.jpg", 1919, "A good book...");
        Media media3 = Media.create("testMedia3", testAuthor2, book, adventure, "pic.jpg", 1981, "A good book...");
        Media media4 = Media.create("testMedia4", testAuthor3, newspaper, realism, "pic.jpg", 1965, "A good book...");


        Member testMember1 = Member.create("test", Password.hashPassword("test"));
        Member testMember2 = Member.create("test1", Password.hashPassword("test1"));
        //Member testAdmin = Member.create("admin", Password.hashPassword("admin"));
        //testAdmin.setMembership(Membership.ADMIN);

        Copy copy1 = Copy.create(media1);
        Copy copy2 = Copy.create(null);
        Copy copy3 = Copy.create(media1);
        Copy copy4 = Copy.create(media3);
        Copy copy5 = Copy.create(media3);

        copy1.setStatus(CopyStatuses.RENTED);
        copy2.setStatus(CopyStatuses.AVAILABLE);
        copy3.setStatus(CopyStatuses.PENDING);
        copy4.setStatus(CopyStatuses.PENDING);

        Rent rent1 = Rent.create(testMember1, copy1);
        rent1.setRentPeriod();

        Rent rent2 = Rent.create(testMember1, copy3);
        rent2.setRentPeriod();
        rent2.setDateReturned();

        Rent rent3 = Rent.create(testMember2, copy4);
        Rent rent4 = Rent.create(testMember2, copy5);


        Author[] authors = new Author[]{testAuthor1, testAuthor2, testAuthor3, testAuthor4};
        for (Author author : authors) {
            authorService.saveAuthor(author);
        }

        Category[] categories = new Category[]{book, magazine, newspaper};
        for (Category category : categories) {
            categoryService.saveCategory(category);
        }

        Genre[] genres = new Genre[]{adventure, mythology, realism};
        for (Genre genre : genres) {
            genreService.saveGenre(genre);
        }

        Media[] mediaList = new Media[]{media1, media2, media3, media4};
        for (Media media : mediaList) {
            mediaService.saveMedia(media);
        }

        Member[] members = new Member[]{testMember1, testMember2}; //testAdmin
        for (Member member : members) {
            memberService.saveMember(member);
        }

        Copy[] copies = new Copy[]{copy1, copy2, copy3, copy4, copy5};
        for (Copy copy : copies) {
            copyService.saveCopy(copy);
        }

        Rent[] rents = new Rent[]{rent1, rent2, rent3, rent4};
        for (Rent rent : rents) {
            rentService.saveRent(rent);
        }
    }
}