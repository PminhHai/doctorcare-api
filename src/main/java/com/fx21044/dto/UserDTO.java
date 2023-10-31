package com.fx21044.dto;

public class UserDTO {
	private int id;
	
	private String name;
	
	private String email;
	
	private String role;
	
	private String phone;
	
	private String gender;
	
	private String address;
	
	private int isNotBlocked;
	
	public UserDTO() {
		
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIsNotBlocked() {
		return isNotBlocked;
	}

	public void setIsNotBlocked(int isNotBlocked) {
		this.isNotBlocked = isNotBlocked;
	}
	
	 
}
