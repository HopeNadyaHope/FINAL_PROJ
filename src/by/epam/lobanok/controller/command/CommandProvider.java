package by.epam.lobanok.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.lobanok.controller.command.impl.AddCourseParticipant;
import by.epam.lobanok.controller.command.impl.Entrance;
import by.epam.lobanok.controller.command.impl.Exit;
import by.epam.lobanok.controller.command.impl.go_to.GoToAboutPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToTeacherCourseParticipantsPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToCoursesPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToEditPrifile;
import by.epam.lobanok.controller.command.impl.go_to.GoToGuestUserPagePage;
import by.epam.lobanok.controller.command.impl.go_to.GoToMainPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToRegistrationPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToRunningCoursesPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToStudentCourseResultPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToStudentCoursesResultsPage;
import by.epam.lobanok.controller.command.impl.AddCourseResult;
import by.epam.lobanok.controller.command.impl.DeleteCourseParticipant;
import by.epam.lobanok.controller.command.impl.EditProfile;
import by.epam.lobanok.controller.command.impl.go_to.GoToUserCoursesPage;
import by.epam.lobanok.controller.command.impl.go_to.GoToUserPage;
import by.epam.lobanok.controller.command.impl.Localization;
import by.epam.lobanok.controller.command.impl.Registration;
import by.epam.lobanok.controller.command.impl.UploadPhoto;
import by.epam.lobanok.controller.command.impl.admin.AddCourse;
import by.epam.lobanok.controller.command.impl.admin.AddRunningCourse;
import by.epam.lobanok.controller.command.impl.admin.EditCourse;
import by.epam.lobanok.controller.command.impl.admin.EditRunningCourse;
import by.epam.lobanok.controller.command.impl.admin.go_to.GoToAddCoursePage;
import by.epam.lobanok.controller.command.impl.admin.go_to.GoToAddRunningCoursePage;
import by.epam.lobanok.controller.command.impl.admin.go_to.GoToAllStudentsPage;
import by.epam.lobanok.controller.command.impl.admin.go_to.GoToAllTeachersPage;
import by.epam.lobanok.controller.command.impl.admin.go_to.GoToEditCoursePage;
import by.epam.lobanok.controller.command.impl.admin.go_to.GoToEditRunningCoursePage;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	
	public CommandProvider(){
		commands.put(CommandName.LOCALIZATION, new Localization());		
				
		commands.put(CommandName.ENTRANCE, new Entrance());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.EDIT_PROFILE, new EditProfile());
		commands.put(CommandName.UPLOAD_PHOTO, new UploadPhoto());
		commands.put(CommandName.EXIT, new Exit());
				
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());		
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_ABOUT_PAGE, new GoToAboutPage());
		commands.put(CommandName.GO_TO_COURSES_PAGE, new GoToCoursesPage());
		commands.put(CommandName.GO_TO_RUNNING_COURSES_PAGE, new GoToRunningCoursesPage());
		commands.put(CommandName.GO_TO_GUEST_USER_PAGE, new GoToGuestUserPagePage());
		
		commands.put(CommandName.GO_TO_USER_PAGE, new GoToUserPage());
		commands.put(CommandName.GO_TO_EDIT_PROFILE, new GoToEditPrifile());
		commands.put(CommandName.GO_TO_USER_COURSES_PAGE, new GoToUserCoursesPage());
		
		commands.put(CommandName.GO_TO_STUDENT_COURSE_RESULT_PAGE, new GoToStudentCourseResultPage());		
		commands.put(CommandName.GO_TO_STUDENT_COURSES_RESULTS_PAGE, new GoToStudentCoursesResultsPage());
		commands.put(CommandName.ADD_COURSE_PARTICIPANT, new AddCourseParticipant());
		commands.put(CommandName.DELETE_COURSE_PARTICIPANT, new DeleteCourseParticipant());
		
		commands.put(CommandName.GO_TO_TEACHER_COURSE_PARTICIPANTS_PAGE, new GoToTeacherCourseParticipantsPage());
		commands.put(CommandName.ADD_COURSE_RESULT, new AddCourseResult());
		
		commands.put(CommandName.GO_TO_ALL_STUDENTS_PAGE, new GoToAllStudentsPage());
		commands.put(CommandName.GO_TO_ALL_TEACHERS_PAGE, new GoToAllTeachersPage());
		
		commands.put(CommandName.GO_TO_EDIT_COURSE_PAGE, new GoToEditCoursePage());
		commands.put(CommandName.EDIT_COURSE, new EditCourse());
		commands.put(CommandName.GO_TO_ADD_COURSE_PAGE, new GoToAddCoursePage());
		commands.put(CommandName.ADD_COURSE, new AddCourse());
		
		commands.put(CommandName.ADD_RUNNING_COURSE, new AddRunningCourse());
		commands.put(CommandName.GO_TO_ADD_RUNNING_COURSE_PAGE, new GoToAddRunningCoursePage());
		
		commands.put(CommandName.EDIT_RUNNING_COURSE, new EditRunningCourse());
		commands.put(CommandName.GO_TO_EDIT_RUNNING_COURSE_PAGE, new GoToEditRunningCoursePage());		
	}
	
	public Command getCommand(String commandValue) {
		Command command;
		CommandName commandName;		
		commandName = CommandName.valueOf(commandValue.toUpperCase());
		command = commands.get(commandName);
		
		return command;	
	}	
}
