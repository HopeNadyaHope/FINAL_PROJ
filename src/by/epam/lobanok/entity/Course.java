package by.epam.lobanok.entity;

import java.io.Serializable;


public class Course implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String courseName;
	private String description;
	
	public Course(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
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
		Course other = (Course) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", description=" + description + "]";
	}	
	
	/////////////////////////////////////////////////////////////////////////////////
	public static class Builder {
		private Course course;
		
		public Builder() {
			course = new Course();
		}
		
		public Builder withID(int id) {
			course.id = id;
			return this;			
		}
		
		public Builder withCourseName(String courseName) {
			course.courseName = courseName;
			return this;			
		}
		
		public Builder withDescription(String description) {
			course.description = description;
			return this;			
		}
		
		public Course build() {
			return course;
		}		
	}
}