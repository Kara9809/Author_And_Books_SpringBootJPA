package authorsandbooks.authorsandbooks;

import authorsandbooks.authorsandbooks.entities.Author;
import authorsandbooks.authorsandbooks.entities.Book;
import authorsandbooks.authorsandbooks.exceptions.LibraryCustomException;
import authorsandbooks.authorsandbooks.services.LibraryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class AuthorsAndBooksApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AuthorsAndBooksApplication.class, args);
        System.out.println("Authors and Books system is running...");

        LibraryServiceImpl libraryService = ctx.getBean(LibraryServiceImpl.class);

        Author williamShakespeare = Author.builder().id(0).name("William Shakespeare").build();
        Book romeoAndJuliet = Book.builder().id(0).name("romeo and juliet").year(1597).author(williamShakespeare).build();
        Book hamlet = Book.builder().id(0).name("hamlet").year(1603).author(williamShakespeare).build();
        williamShakespeare.setBooks(List.of(romeoAndJuliet, hamlet));

        Author alexanderPushkin = Author.builder().id(0).name("Alexander Pushkin").build();
        Book theCaptainsDaughter = Book.builder().id(0).name("The captain's daughter").year(1836).author(alexanderPushkin).build();
        Book eugeneOnegin = Book.builder().id(0).name("Eugene Onegin").year(1833).author(alexanderPushkin).build();
        alexanderPushkin.setBooks(List.of(theCaptainsDaughter, eugeneOnegin));

        try {
            libraryService.addAuthorAndHisBook(williamShakespeare);
            libraryService.addAuthorAndHisBook(alexanderPushkin);
            //libraryService.deleteAnAuthor(alexanderPushkin.getId()); //try it, it works.
            System.out.println(libraryService.findBooksByYearBetween(1597, 1604));
//            System.out.println(catService.getCatsByWeightASC());

//            cat1.setWeight(8.13);
//            cat1.setId(2);
//            catService.updateCat(cat1);

        } catch (LibraryCustomException e) {
            throw new RuntimeException(e);
        }
    }
}
