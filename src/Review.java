public class Review {
    private String username;
    private String text;
    private int rating;

    public Review(String username, String text, int rating) {
        this.username = username;
        this.text = text;
        this.rating = rating;
    }

    public String getUsername() { return username; }
    public String getText() { return text; }
    public int getRating() { return rating; }
}
