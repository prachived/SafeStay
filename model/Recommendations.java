package StaySafe.model;

public class Recommendations {
		protected int recommendationId;
		protected EndUsers endusers;
		protected Address address;
		protected double rating;
		protected double petfriendly;
		protected double childfriendly;
		
		

		public Recommendations(EndUsers endusers, Address address, double rating, double petfriendly, double childfriendly ) {
			this.endusers = endusers;
			this.address = address;
			this.childfriendly=childfriendly;
			this.petfriendly=petfriendly;
		    this.rating=rating;
		}

		public Recommendations(int recommendationId) {
			this.recommendationId = recommendationId;
		}

		public Recommendations(int recommendationId, EndUsers endusers, Address address, double rating, double petfriendly, double childfriendly ) {
			this.recommendationId = recommendationId;
			this.Endusers = endusers;
			this.address = address;
			this.childfriendly=childfriendly;
			this.petfriendly=petfriendly;
		    this.rating=rating;
		}
		
		public EndUsers getEndusers() {
			return endusers;
		}

		public void setEndusers(EndUsers endusers) {
			this.endusers = endusers;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public double getRating() {
			return rating;
		}

		public void setRating(float rating) {
			this.rating = rating;
		}

		public double getPetfriendly() {
			return petfriendly;
		}

		public void setPetfriendly(float petfriendly) {
			this.petfriendly = petfriendly;
		}

		public double getChildfriendly() {
			return childfriendly;
		}

		public void setChildfriendly(float childfriendly) {
			this.childfriendly = childfriendly;
		}

		public int getRecommendationId() {
			return recommendationId;
		}

		public void setRecommendationId(int recommendationId) {
			this.recommendationId = recommendationId;
		}

	

	}

