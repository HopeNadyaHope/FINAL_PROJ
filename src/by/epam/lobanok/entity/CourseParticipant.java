package by.epam.lobanok.entity;

import java.io.Serializable;


public class CourseParticipant implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User student;
	private RunningCourse runningCourse;
	private Result result;
	
	public CourseParticipant() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public RunningCourse getRunningCourse() {
		return runningCourse;
	}

	public void setRunningCourse(RunningCourse runningCourse) {
		this.runningCourse = runningCourse;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((runningCourse == null) ? 0 : runningCourse.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		CourseParticipant other = (CourseParticipant) obj;
		if (id != other.id)
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (runningCourse == null) {
			if (other.runningCourse != null)
				return false;
		} else if (!runningCourse.equals(other.runningCourse))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseParticipant [id=" + id + ", student=" + student + ", runningCourse=" + runningCourse + ", result="
				+ result + "]";
	}	
	
	/////////////////////////////////////////////////////////////////////////////////
	public static class Builder {
		private CourseParticipant courseParticipant;
		
		public Builder() {
			courseParticipant = new CourseParticipant();
		}
		
		public Builder withID(int id) {
			courseParticipant.id = id;
			return this;			
		}
		
		public Builder withStudent(User student) {
			courseParticipant.student = student;
			return this;			
		}
		
		public Builder withRunningCourse(RunningCourse runningCourse) {
			courseParticipant.runningCourse = runningCourse;
			return this;			
		}
		
		public Builder withResult(Result result) {
			courseParticipant.result = result;
			return this;			
		}
		
		public CourseParticipant build() {
			return courseParticipant;
		}	
	}
}