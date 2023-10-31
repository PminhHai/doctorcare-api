package com.fx21044.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateInfoDTO {
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String name;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	private String address;
	
	private String avatar;

	public UpdateInfoDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
