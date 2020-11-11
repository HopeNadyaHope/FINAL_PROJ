package by.epam.lobanok.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class RunningCourse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User teacher;
	private Course course;
	private LocalDate start;
	private LocalDate end;
	private String passing;
	
	public RunningCourse() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	public String getPassing() {
		return passing;
	}
	public void setPassing(String passing) {
		this.passing = passing;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + id;
		result = prime * result + ((passing == null) ? 0 : passing.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		RunningCourse other = (RunningCourse) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id != other.id)
			return false;
		if (passing == null) {
			if (other.passing != null)
				return false;
		} else if (!passing.equals(other.passing))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RunningCourse [id=" + id + ", teacher=" + teacher + ", course=" + course + ", start=" + start + ", end="
				+ end + ", passing=" + passing + "]";
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	public static class Builder {
		private RunningCourse runningCourse;
		
		public Builder() {
			runningCourse = new RunningCourse();			
		}
		
		public Builder withID(int id) {
			runningCourse.id = id;
			return this;			
		}
		
		public Builder withTeacher(User teacher) {
			runningCourse.teacher = teacher;
			return this;			
		}
		
		public Builder withCourse(Course course) {
			runningCourse.course = course;
			return this;			
		}
		
		public Builder withStart(LocalDate start) {
			runningCourse.start = start;
			return this;			
		}
		
		public Builder withEnd(LocalDate end) {
			runningCourse.end = end;
			return this;			
		}
		
		public Builder withPassing(String passing) {
			runningCourse.passing = passing;
			return this;			
		}

		public RunningCourse build() {
			return runningCourse;
		}
	}
}
