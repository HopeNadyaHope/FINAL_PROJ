package by.epam.lobanok.entity;

import java.io.Serializable;

public class Result implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private int rating;
	private String review;
	
	public Result() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + rating;
		result = prime * result + ((review == null) ? 0 : review.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (id != other.id)
			return false;
		if (rating != other.rating)
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", rating=" + rating + ", review=" + review + "]";
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	public static class Builder {
		private Result result;
		
		public Builder() {
			result = new Result();
		}
		
		public Builder withID(int id) {
			result.id = id;
			return this;			
		}
		
		public Builder withRating(int rating) {
			result.rating = rating;
			return this;			
		}
		
		public Builder withReview(String review) {
			result.review = review;
			return this;			
		}
		
		public Result build() {
			return result;
		}		
	}
	
}
