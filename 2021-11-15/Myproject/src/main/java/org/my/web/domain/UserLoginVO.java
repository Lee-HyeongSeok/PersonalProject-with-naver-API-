package org.my.web.domain;

import org.json.simple.JSONObject;

public class UserLoginVO {
	String id;
	String nickName;
	String age;
	String gender;
	String email;
	String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setInstances(JSONObject responseObj) {
		id = (String)responseObj.get("id");
		nickName = (String)responseObj.get("nickname");
		age = (String)responseObj.get("age");
		gender = (String)responseObj.get("gender");
		email = (String)responseObj.get("name");
	}
	public String toString() {
		return "{id : "+id+"}"+"{nick name : "+nickName+"}"+"{age : "+age+"}"+"{gender : "+gender+"}"+"{email : "+email+"}"+"{name : "+name+"}";
	}
}
