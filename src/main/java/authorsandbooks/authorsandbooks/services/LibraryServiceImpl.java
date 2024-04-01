package authorsandbooks.authorsandbooks.services;

import authorsandbooks.authorsandbooks.entities.Author;
import authorsandbooks.authorsandbooks.entities.Book;
import authorsandbooks.authorsandbooks.exceptions.LibraryCustomException;
import authorsandbooks.authorsandbooks.repository.AuthorRepository;
import authorsandbooks.authorsandbooks.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void addAuthorAndHisBook(Author author) throws LibraryCustomException {
        if (author.getName().length() > 50) {
            throw new LibraryCustomException("Author name should be lower then 50 characters");
        }
//        author.setBooks(List.of(book));

// Save both the book and the author within the same transaction
//        bookRepository.save(book);
        authorRepository.save(author);
    }


    @Transactional
    public void deleteAnAuthor(int authorId) throws LibraryCustomException {
        if (!authorRepository.existsById(authorId)) {
            throw new LibraryCustomException("Author with this ID is not exist. Please try again.");
        }
        authorRepository.deleteById(authorId);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getSingleAuthor(int authorId) throws LibraryCustomException {
        return authorRepository.findById(authorId).orElseThrow(() -> new LibraryCustomException("Author with this ID is not exist. Please try again."));
    }

    public List<Book> findBooksByYearBetween(int startYear, int endYear) throws LibraryCustomException {
        if (startYear > endYear) {
            throw new LibraryCustomException("Invalid input: start year cannot be greater than end year.");
        }
        return bookRepository.findBooksByYearBetween(startYear, endYear);
    }

    public Double getAveragePublicationYear() {
        return bookRepository.getAveragePublicationYear();
    }

    public Double getAveragePublicationYearByAuthorId(int authorId) {
        return bookRepository.getAveragePublicationYearByAuthorId(authorId);
    }


}







