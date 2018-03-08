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
                           RentService rentService, ReviewService reviewService, RatingService ratingService) {
        if(!isTestRunning) {
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

            Media vuk = Media.create("Vuk", fekete, book, adventure, "vuk.jpg", 1965, "A rókalyuk folyosója tágabb lett, és a pitvaron túl, mint egy teknő, öblösödött a kamra, ahol halkan szuszogott a rókakölyök. A szőrös gomolyag mozogni kezdett, orra kutatva emelkedett a levegőbe... Vuk lesz a neve, mint öregapjának,");
            Media tuskevar = Media.create("Tüskevár", fekete, book, realism, "tuskevar.jpg", 1957, "Vár volt állítólag valamikor, de akkor még sziget is. Akkor még odáig ért a Balaton, de a patakok telehordták iszappal a nagy öblöt; bizonyos, hogy Tüskevár körül most már sekély a víz és nádas az egész.");
            Media potter1 = Media.create("Harry Potter and the Philosopher's Stone", rowling, book, adventure, "harrypotter1.jpg", 1997, "This is the tale of Harry Potter, an ordinary 11-year-old boy serving as a sort of slave for his aunt and uncle who learns that he is actually a wizard and has been invited to attend the Hogwarts School for Witchcraft and Wizardry.");
            Media potter2 = Media.create("Harry Potter and the Chamber of Secrets", rowling, book, adventure, "nobook.jpg", 1998, "Harry Potter is in his second year of Hogwarts School of Witchcraft and Wizardry. He is visited by a house-elf named Dobby and warned not to go back to Hogwarts.");
            Media lordOfTheRings1 = Media.create("Lord of the Rings", tolkien, book, fantasy, "lordoftherings.jpg", 1954, "One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkeness bind them");
            Media gOfThrones1 = Media.create("Game of Thrones", martin, book, fantasy, "gameofthrones.jpg", 1996, "Here is the first volume in George R. R. Martin’s magnificent cycle of novels that includes A Clash of Kings and A Storm of Swords.");
            Media it = Media.create("It", king, book, horror, "it.jpg", 1986, "Welcome to Derry, Maine… It’s a small city, a place as hauntingly familiar as your own hometown. Only in Derry the haunting is real…");
            Media jurassicPark = Media.create("Jurassic Park", crichton, book, fantasy, "jurassicpark.jpg", 1990, "A billionaire has created a technique to clone dinosaurs. From the DNA that his crack team of scientists extract, he is able to grow the dinosaurs in his laboratories and lock them away on an island behind electric fences, creating a sort of theme park");
            Media twister = Media.create("Twister", crichton, book, drama, "twister.jpg", 1994, "As Oklahoma braces itself for the largest storm in more than fifty years, two rival groups of scientists fight to be the first to launch a high-tech, life-saving tracking device inside the ferocious funnel of one of the most destructive forces in the natural world.");

            Media natgeo167 = Media.create("Vol. 167/ No. 6", natgeo, magazine, science, "natgeo167-6.jpg", 1985, "National Geographic Magazine, is the official magazine of the National Geographic Society. It has been published continuously since its first issue in 1888, nine months after the Society itself was founded.");
            Media kepesSport = Media.create("2017 Nyári különszám", kepessport, magazine, sport, "kepessport.jpg", 2017, "A Képes Sport egy dinamikus, informatív, szórakoztató hetilap, amely nem csupán a sportot az átlagon felül kedvelő olvasókhoz szól.");

            Media nemzetiSport = Media.create("2015.05.13", nemzetisport, newspaper, sport, "nemzetisport.jpg", 2015, "Nemzeti Sport (the title means National Sport) is a Hungarian sports daily");
            Media times = Media.create("1969.07.21", nytimes, newspaper, science, "nytimes.jpg", 1969, "The New York Times is an American newspaper based in New York City with worldwide influence and readership. ");

            Member test = Member.create("test", Password.hashPassword("test"));
            Member admin = Member.create("admin", Password.hashPassword("admin"));
            admin.setMembership(Membership.ADMIN);

            Copy copy1 = Copy.create(it);
            Copy copy2 = Copy.create(lordOfTheRings1);
            Copy copy3 = Copy.create(vuk);
            Copy copy4 = Copy.create(potter2);
            copy1.setStatus(CopyStatuses.RENTED);
            copy2.setStatus(CopyStatuses.AVAILABLE);
            copy3.setStatus(CopyStatuses.AVAILABLE);
            copy4.setStatus(CopyStatuses.AVAILABLE);

            Rent rent1 = Rent.create(test, copy1);
            rent1.setRentPeriod();

            Rent rent2 = Rent.create(test, copy2);
            rent2.setRentPeriod();
            rent2.setDateReturned();

            Rent rent3 = Rent.create(test, copy3);
            rent3.setRentPeriod();
            rent3.setDateReturned();

            Rent rent4 = Rent.create(test, copy4);
            rent4.setRentPeriod();
            rent4.setDateReturned();

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
            copyService.saveCopy(copy3);
            copyService.saveCopy(copy4);
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
            rentService.saveRent(rent3);
            rentService.saveRent(rent4);

            Rating rating1 = new Rating(5, test, it);
            Review review1 = new Review("asdf", test, it);

            reviewService.saveReview(review1);
            ratingService.saveRating(rating1);
        }
    }

    public static void setTestRunning(boolean testRunning) {
        isTestRunning = testRunning;
    }
}