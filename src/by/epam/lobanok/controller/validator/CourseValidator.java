package by.epam.lobanok.controller.validator;

import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;

public class CourseValidator {
	private static final CourseValidator instance = new CourseValidator();

	/////////////////////////////////////////////////////////////////////////////////////////////
	private CourseValidator() {}
	
	public static CourseValidator getInstance() {
		return instance;
	}
	
	public boolean validateCourse(Course course) {
		String courseName;
		String description;
		courseName = course.getCourseName();
		description = course.getDescription();
		if ((courseName == null) ||	(description == null)) {
			return false;
		}
		return (courseName.trim().length() > 3);
	}
	
	public boolean validateRunningCourse(RunningCourse runningCourse) {
		return true;
	}
}
