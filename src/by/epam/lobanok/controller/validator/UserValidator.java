package by.epam.lobanok.controller.validator;

import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.RegistrationData;

public class UserValidator {
	private static final UserValidator instance = new UserValidator();

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String EMAIL_REGEX = "^[.a-z0-9_-]+@[a-z0-9-]+\\.[a-zA-Z]{2,6}$";
	

	/////////////////////////////////////////////////////////////////////////////////////////////
	private UserValidator() {}
	
	public static UserValidator getInstance() {
		return instance;
	}
	
	public boolean validateRegistrationData(RegistrationData regData) {
		int age = regData.getAge();
		String email = regData.getEmail();
		
		if((regData.getLogin().trim().length() < 3) || (regData.getPassword().length() < 5)
				||(regData.getName() == null) || (regData.getSurname() == null)
				||(regData.getSex() == null) || (regData.getRole() == null)
				||(age < 14) || (age >  100) || (email == null)) {
			return false;
		}
		return regData.getEmail().matches(EMAIL_REGEX);
	}
	
	public boolean validateEntranceData(EntranceData entrData) {
		if((entrData.getLogin().length() < 3) || (entrData.getPassword().length() < 5)) {
			return false;
		}
		return true;
	}
}
