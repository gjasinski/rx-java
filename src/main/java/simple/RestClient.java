package simple;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is a fake REST client.
 * <p>
 * It simulates making blocking calls to an REST endpoint.
 */
public class RestClient {


    public List<String> getFavoriteBooks() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
        }
        return createBooks();
    }

    public List<String> getFavoriteBooksWithException() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {

        }
        throw new RuntimeException("Failed to load");
    }

    private List<String> createBooks() {
        List<String> books = new ArrayList<>();
        books.add("Lord of the Rings");
        books.add("The dark elf");
        books.add("Eclipse Introduction");
        books.add("History book");
        books.add("Der kleine Prinz");
        books.add("7 habits of highly effective people");
        books.add("Other book 1");
        books.add("Other book 2");
        books.add("Other book 3");
        books.add("Other book 4");
        books.add("Other book 5");
        books.add("Other book 6");
        return books;
    }
}