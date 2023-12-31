package com.fx21044.model;

public class JwtResponse {
	private int id;
	private String token;
	private String type = "Bearer";
    private String name;
    private String email;
    private String role;
    
	public JwtResponse() {
		
	}

	public JwtResponse(String accessToken, int id, String name, String email,
			String role) {
		this.id = id;
		this.token = accessToken;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	
    
    
}
