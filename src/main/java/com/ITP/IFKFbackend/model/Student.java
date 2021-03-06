package com.ITP.IFKFbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")


public class Student {
	
	@Id
	@Column(name = "studentId")
	 private String  studentId;
	
	@Column(name = "name")
	 private String name;
	
	@Column(name = "gender")
	 private String gender;
	
	@Column(name = "address")
	 private String address;
	
	@Column(name = "email")
	 private String email;
	
	@Column(name = "nic")
	 private String nic;
	
	@Column(name = "dob")
	 private Date dob;
	
	@Column(name = "sessionId")
	 private String sessionId;
	
	@Column(name = "phoneNo")
	 private String phoneNo;

	 
	 
	 public Student() {
		 
	 }
	   
	 public Student(String studentId, String name, String gender, String address, String email, String nic, Date dob,
			String sessionId, String phoneNo) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.nic = nic;
		this.dob = dob;
		this.sessionId = sessionId;
		this.phoneNo = phoneNo;
	}
	 
	 
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}