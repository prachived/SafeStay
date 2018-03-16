package StaySafe.model;

public class Reviews {
	protected int ReviewId;
	protected String content;
	protected EndUsers endusers;
	protected Address address;

	public Reviews(int ReviewId, String content, EndUsers endusers, Address address) {
		this.ReviewId = ReviewId;
		this.content = content;
		this.endusers = endusers;
		this.address = address;
	}

	public Reviews(Date Created, String content, double Rating, Users users, Restaurants restaurants) {
		this.ReviewId = ReviewId;
		this.content = content;
		this.endusers = endusers;
		this.address = address;
	}
	public Reviews(int reviewId) {
		this.ReviewId = reviewId;
	}

	public int getReviewId() {
		return ReviewId;
	}

	public void setReviewId(int reviewId) {
		this.ReviewId = reviewId;
	}

	
}
