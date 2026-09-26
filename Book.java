import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String genre;
    private boolean favorite;
    private String readingStatus;
    private List<Review> reviews;

    public Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.favorite = false;
        this.readingStatus = "To Be Read";
        this.reviews = new ArrayList<>();
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public String getReadingStatus() { return readingStatus; }
    public void setReadingStatus(String readingStatus) { this.readingStatus = readingStatus; }

    public void addReview(String username, String text, int rating) {
        if (text == null || text.trim().isEmpty()) return;
        if (rating < 1 || rating > 5) return;
        reviews.add(new Review(username, text.trim(), rating));
    }

    public List<Review> getReviews() { return reviews; }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        int total = 0;
        for (Review review : reviews) total += review.getRating();
        return (double) total / reviews.size();
    }

    public int getReviewCount() { return reviews.size(); }
}
