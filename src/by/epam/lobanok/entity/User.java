package by.epam.lobanok.entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private String sex;
	private int age;
	private String email;
	private String role;
	private String photoURL;
	
	public User() {}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photoURL == null) ? 0 : photoURL.hashCode());
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
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photoURL == null) {
			if (other.photoURL != null)
				return false;
		} else if (!photoURL.equals(other.photoURL))
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
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", sex=" + sex + ", age=" + age
				+ ", email=" + email + ", role=" + role + ", photo_url=" + photoURL + "]";
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	public static class Builder {
		private User user;
		
		public Builder() {
			user = new User();			
		}
		
		public Builder withID(int id) {
			user.id = id;
			return this;			
		}
		
		public Builder withName(String name) {
			user.name = name;
			return this;			
		}
		
		public Builder withSurname(String surname) {
			user.surname = surname;
			return this;			
		}
		
		public Builder withAge(int age) {
			user.age = age;
			return this;			
		}
		
		public Builder withEmail(String email) {
			user.email = email;
			return this;			
		}
		
		public Builder withSex(String sex) {
			user.sex = sex;
			return this;			
		}
		
		public Builder withRole(String role) {
			user.role = role;
			return this;			
		}
		
		public Builder withPhotoURL(String photoURL) {
			user.photoURL = photoURL;
			return this;			
		}
		
		public User build() {
			return user;
		}		
	}
}
