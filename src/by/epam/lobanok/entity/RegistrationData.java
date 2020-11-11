package by.epam.lobanok.entity;

import java.io.Serializable;


public class RegistrationData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	private String name;
	private String surname;
	private int age;
	private String sex;
	private String email;
	private String role;
	
	public RegistrationData() {}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		RegistrationData other = (RegistrationData) obj;
		if (age != other.age)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginationData [login=" + login + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", age=" + age + ", sex=" + sex + ", email=" + email + ", role=" + role + "]";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	public static class Builder {
		private RegistrationData regData;
		
		public Builder() {
			regData = new RegistrationData();
			
		}
		
		public Builder withLogin(String login) {
			regData.login = login;
			return this;			
		}
		
		public Builder withPassword(String password) {
			regData.password = password;
			return this;			
		}
		
		public Builder withName(String name) {
			regData.name = name;
			return this;			
		}
		
		public Builder withSurname(String surname) {
			regData.surname = surname;
			return this;			
		}
		
		public Builder withAge(int age) {
			regData.age = age;
			return this;			
		}
		
		public Builder withEmail(String email) {
			regData.email = email;
			return this;			
		}
		
		public Builder withSex(String sex) {
			regData.sex = sex;
			return this;			
		}
		
		public Builder withRole(String role) {
			regData.role = role;
			return this;			
		}
		
		public RegistrationData build() {
			return regData;
		}		
	}
}
