import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }

    @Given("(a|another) book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addNewBook(final String title, final String author, final LocalDateTime published) {
        Date p = Date.from(published.toInstant(ZoneOffset.UTC));
        Book book = new Book(title, author, p);
        library.addBook(book);
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
        Date f = Date.from(from.toInstant(ZoneOffset.UTC));
        Date t = Date.from(to.toInstant(ZoneOffset.UTC));
        result = library.findBooks(f, t);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertThat(result.size(), equalTo(booksFound));
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }

    @When("the customer searches for books with title {string}")
    public void booksWithTitle(String title) {
        result = library.findBooksByTitle(title);
    }

    @When("the customer searches for books written by {string}")
    public void booksByAuthor(String author) {
        result = library.findBooksByAuthor(author);
    }

    @And("Book {int} should have the date {iso8601Date}")
    public void verifyBookDateAtPosition(int position, final LocalDateTime date) {
        Date d = Date.from(date.toInstant(ZoneOffset.UTC));
        assertThat(result.get(position - 1).getPublished(), equalTo(d));
    }
}
