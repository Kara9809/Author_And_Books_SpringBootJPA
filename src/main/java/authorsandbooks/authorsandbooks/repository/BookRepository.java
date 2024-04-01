package authorsandbooks.authorsandbooks.repository;

import authorsandbooks.authorsandbooks.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByYearBetween(Integer startYear, Integer endYear);

    @Query(value = "SELECT AVG(year) FROM books;", nativeQuery = true)
//    List<Book> books = bookRepository.findAll();
//        if (books.isEmpty()) {
//        return 0.00; // Return 0 if there are no books
//    }
//
//    int sum = 0;
//        for (Book book : books) {
//        sum += book.getAveragePublicationYear();
//    }
//
//        return (double) sum / books.size();
    Double getAveragePublicationYear();

    @Query(value = "SELECT AVG(year) FROM books WHERE author_id = ?;", nativeQuery = true)
    Double getAveragePublicationYearByAuthorId(int authorId);

}
