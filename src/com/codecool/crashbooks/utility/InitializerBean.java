package com.codecool.crashbooks.utility;


import com.codecool.crashbooks.model.*;
import com.codecool.crashbooks.model.mediaProperty.*;
import com.codecool.crashbooks.model.memberProperty.Membership;
import com.codecool.crashbooks.service.*;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {
    private static boolean isTestRunning = false;

    public InitializerBean(AuthorService authorService, CategoryService categoryService, CopyService copyService,
                           GenreService genreService, MediaService mediaService, MemberService memberService,
                           RentService rentService) {
        if(isTestRunning) {
            Author fekete = Author.create("Fekete István");
            Author rowling = Author.create("J. K. Rowling");
            Author tolkien = Author.create("J. R. R. Tolkien");
            Author martin = Author.create("George R. R. Martin");
            Author king = Author.create("Stephen King");
            Author crichton = Author.create("Michael Crichton");
            Author natgeo = Author.create("National Geography");
            Author kepessport = Author.create("Képes Sport");
            Author nemzetisport = Author.create("Nemzeti Sport");
            Author nytimes = Author.create("The New York Times");

            Category book = Category.create(Categories.BOOK);
            Category magazine = Category.create(Categories.MAGAZINE);
            Category newspaper = Category.create(Categories.NEWSPAPER);

            Genre drama = Genre.create(Genres.DRAMA);
            Genre horror = Genre.create(Genres.HORROR);
            Genre fantasy = Genre.create(Genres.FANTASY);
            Genre adventure = Genre.create(Genres.ADVENTURE);
            Genre realism = Genre.create(Genres.REALISM);
            Genre science = Genre.create(Genres.SCIENCE);
            Genre sport = Genre.create(Genres.SPORT);

            Media vuk = Media.create("Vuk", fekete, book, adventure, "vuk.jpg", 1965, "A good book...");
            Media tuskevar = Media.create("Tüskevár", fekete, book, realism, "tuskevar.jpg", 1957, "A good book...");
            Media potter1 = Media.create("Harry Potter and the Philosopher's Stone", rowling, book, adventure, "harrypotter1.jpg", 1997, "A good book...");
            Media potter2 = Media.create("Harry Potter and the Chamber of Secrets", rowling, book, adventure, "nobook.jpg", 1998, "A good book...");
            Media lordOfTheRings1 = Media.create("Lord of the Rings", tolkien, book, fantasy, "lordoftherings.jpg", 1954, "A good book...");
            Media gOfThrones1 = Media.create("Game of Thrones", martin, book, fantasy, "gameofthrones.jpg", 1996, "A good book...");
            Media it = Media.create("It", king, book, horror, "it.jpg", 1986, "A good book...");
            Media jurassicPark = Media.create("Jurassic Park", crichton, book, fantasy, "jurassicpark.jpg", 1990, "A good book...");
            Media twister = Media.create("Twister", crichton, book, drama, "twister.jpg", 1994, "A good book...");

            Media natgeo167 = Media.create("Vol. 167/ No. 6", natgeo, magazine, science, "natgeo167-6.jpg", 1985, "A good magazine...");
            Media kepesSport = Media.create("2017 Nyári különszám", kepessport, magazine, sport, "kepessport.jpg", 2017, "A good magazine...");

            Media nemzetiSport = Media.create("2015.05.13", nemzetisport, newspaper, sport, "nemzetisport.jpg", 2015, "A good newspaper...");
            Media times = Media.create("1969.07.21", nytimes, newspaper, science, "nytimes.jpg", 1969, "A good newspaper...");

            Member test = Member.create("test", Password.hashPassword("test"));
            Member admin = Member.create("admin", Password.hashPassword("admin"));
            admin.setMembership(Membership.ADMIN);

            Copy copy1 = Copy.create(it);
            Copy copy2 = Copy.create(lordOfTheRings1);
            copy1.setStatus(CopyStatuses.RENTED);
            copy2.setStatus(CopyStatuses.AVAILABLE);

            Rent rent1 = Rent.create(test, copy1);
            rent1.setRentPeriod();

            Rent rent2 = Rent.create(test, copy2);
            rent2.setRentPeriod();
            rent2.setDateReturned();

            authorService.saveAuthor(fekete);
            authorService.saveAuthor(rowling);
            authorService.saveAuthor(tolkien);
            authorService.saveAuthor(martin);
            authorService.saveAuthor(king);
            authorService.saveAuthor(crichton);
            authorService.saveAuthor(natgeo);
            authorService.saveAuthor(kepessport);
            authorService.saveAuthor(nemzetisport);
            authorService.saveAuthor(nytimes);

            categoryService.saveCategory(book);
            categoryService.saveCategory(magazine);
            categoryService.saveCategory(newspaper);

            genreService.saveGenre(drama);
            genreService.saveGenre(horror);
            genreService.saveGenre(fantasy);
            genreService.saveGenre(adventure);
            genreService.saveGenre(realism);
            genreService.saveGenre(science);
            genreService.saveGenre(sport);
            genreService.saveGenre(Genre.create(Genres.COMEDY));
            genreService.saveGenre(Genre.create(Genres.ROMANCE));
            genreService.saveGenre(Genre.create(Genres.SATIRE));
            genreService.saveGenre(Genre.create(Genres.TRAGEDY));
            genreService.saveGenre(Genre.create(Genres.MYTHOLOGY));
            genreService.saveGenre(Genre.create(Genres.TRAGICOMEDY));

            mediaService.saveMedia(vuk);
            mediaService.saveMedia(tuskevar);
            mediaService.saveMedia(potter1);
            mediaService.saveMedia(potter2);
            mediaService.saveMedia(lordOfTheRings1);
            mediaService.saveMedia(gOfThrones1);
            mediaService.saveMedia(it);
            mediaService.saveMedia(jurassicPark);
            mediaService.saveMedia(twister);
            mediaService.saveMedia(natgeo167);
            mediaService.saveMedia(kepesSport);
            mediaService.saveMedia(nemzetiSport);
            mediaService.saveMedia(times);

            memberService.saveMember(test);
            memberService.saveMember(admin);

            copyService.saveCopy(copy1);
            copyService.saveCopy(copy2);
            copyService.saveCopy(Copy.create(vuk));
            copyService.saveCopy(Copy.create(vuk));
            copyService.saveCopy(Copy.create(tuskevar));
            copyService.saveCopy(Copy.create(tuskevar));
            copyService.saveCopy(Copy.create(tuskevar));
            copyService.saveCopy(Copy.create(potter1));
            copyService.saveCopy(Copy.create(potter1));
            copyService.saveCopy(Copy.create(potter1));
            copyService.saveCopy(Copy.create(potter1));
            copyService.saveCopy(Copy.create(potter1));
            copyService.saveCopy(Copy.create(potter2));
            copyService.saveCopy(Copy.create(lordOfTheRings1));
            copyService.saveCopy(Copy.create(lordOfTheRings1));
            copyService.saveCopy(Copy.create(lordOfTheRings1));
            copyService.saveCopy(Copy.create(lordOfTheRings1));
            copyService.saveCopy(Copy.create(lordOfTheRings1));
            copyService.saveCopy(Copy.create(gOfThrones1));
            copyService.saveCopy(Copy.create(jurassicPark));
            copyService.saveCopy(Copy.create(jurassicPark));
            copyService.saveCopy(Copy.create(twister));
            copyService.saveCopy(Copy.create(natgeo167));
            copyService.saveCopy(Copy.create(natgeo167));
            copyService.saveCopy(Copy.create(natgeo167));
            copyService.saveCopy(Copy.create(kepesSport));
            copyService.saveCopy(Copy.create(kepesSport));
            copyService.saveCopy(Copy.create(nemzetiSport));
            copyService.saveCopy(Copy.create(nemzetiSport));
            copyService.saveCopy(Copy.create(nemzetiSport));
            copyService.saveCopy(Copy.create(nemzetiSport));
            copyService.saveCopy(Copy.create(times));
            copyService.saveCopy(Copy.create(times));

            rentService.saveRent(rent1);
            rentService.saveRent(rent2);
        }
    }

    public static void setTestRunning(boolean testRunning) {
        isTestRunning = testRunning;
    }
}