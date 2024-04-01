package authorsandbooks.authorsandbooks.controllers;


import authorsandbooks.authorsandbooks.entities.Author;
import authorsandbooks.authorsandbooks.entities.Book;
import authorsandbooks.authorsandbooks.exceptions.LibraryCustomException;
import authorsandbooks.authorsandbooks.services.LibraryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/library")
@CrossOrigin
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryServiceImpl libraryService;

    // RequestBody
    // RequestBody => Object
    // RequestParam => name, weight... id/uuid https//localhost:8080/api/cats/?name="mitizi"&weight="";
    // PathVariable => id/uuid https//localhost:8080/api/cats/1

    // 200  - 200 Get | Void 204
    // 300
    // 400
    // 500

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAuthorAndHisBook(@RequestBody Author author) throws LibraryCustomException {
        libraryService.addAuthorAndHisBook(author);
    }

    @DeleteMapping(value = "/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnAuthor(@PathVariable int authorId) throws LibraryCustomException {
        libraryService.deleteAnAuthor(authorId);
    }

    @GetMapping
    public List<Author> getAllAuthors() throws LibraryCustomException {
        return libraryService.getAllAuthors();
    }

    @GetMapping(value = "/{authorId}")
    public Author getSingleAuthor(@PathVariable int authorId) throws LibraryCustomException {
        return libraryService.getSingleAuthor(authorId);
    }

    @GetMapping(value = "/books-by-year")
    public List<Book> findBooksByYearBetween(@RequestParam int startYear, @RequestParam int endYear) throws LibraryCustomException {
        return libraryService.findBooksByYearBetween(startYear, endYear);
    }

    @GetMapping(value = "/average-by-year")
    public Double getAveragePublicationYear() {
        return libraryService.getAveragePublicationYear();
    }

    @GetMapping(value = "/average-by-year-and-author")
    public Double getAveragePublicationYearByAuthorId(@PathVariable int authorId) {
        return libraryService.getAveragePublicationYearByAuthorId(authorId);
    }
}
