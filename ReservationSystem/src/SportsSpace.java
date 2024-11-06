public class SportsSpace {

    private int id;

    private String Name;

    private String type;

    private int capacity;

    private List<Schedule> availableSchedules;

    private List<Rating> ratings;

    private List<Comment> comments;

    private Qualification qualification;

    public Time isAvailable(Date date, Time startTime, Time endTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Rating addRating(Rating rating) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Comment addComment(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void getAverageRating() {
    }
}
