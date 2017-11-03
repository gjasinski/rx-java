package simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AnotherRestClient {
    public List<String> getFavoriteBooks() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
        }
        return createBooks();
    }

    public List<String> getFavoriteBooksWithException() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {

        }
        throw new RuntimeException("Failed to load");
    }

    private List<String> createBooks() {
        List<String> books = new ArrayList<>();
        books.add("100Lord of the Rings");
        books.add("100The dark elf");
        books.add("100Eclipse Introduction");
        books.add("100History book");
        books.add("100Der kleine Prinz");
        books.add("1007 habits of highly effective people");
        books.add("100Other book 1");
        books.add("100Other book 2");
        books.add("100Other book 3");
        books.add("100Other book 4");
        books.add("100Other book 5");
        books.add("100Other book 6");
        return books;
    }
}
