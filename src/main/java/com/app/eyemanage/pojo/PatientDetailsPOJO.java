package com.app.eyemanage.pojo;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import lombok.AccessLevel;
import lombok.Setter;

//@Data
@Entity
@Table(name="PatientDetails")
@Component
public class PatientDetailsPOJO {
	
	@Column(unique = true, nullable = false)
	@Id
	@SequenceGenerator(name="Patient_SEQ", sequenceName="Patient_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Patient_SEQ")
	private Integer patientId;
	
	@Column(unique = false, nullable = false, length=20)
	private String firstName;
	
	@Column(unique = false, nullable = false, length=20)
	private String lastName;
	
	@Column(unique = false, nullable = false, length=2)
	private Integer age;
	
	@Column(unique = false, nullable = false, length=100)
	private String address;
	
	@Column(unique = false, nullable = true, length=50)
	@Email
	private String email;
	
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(unique = false, nullable = false)
	private Date birthDate;
	
	@Column(unique = false, nullable = false, length=1)
	private String gender;
	
	@Column(unique = false, nullable = true, length=10)
	private Long mobile;
	
	@Column(unique = false, nullable = true, length=15)
	private Long phoneNumber;
	
	@Column(unique = false, nullable = false)
	@CreatedDate
	private Date firstVisitDate;
	
	@OneToMany( mappedBy = "patient" , cascade = CascadeType.ALL, orphanRemoval = true )
	@Setter(AccessLevel.NONE)
	private final Set<VisitPOJO> visits = new HashSet<VisitPOJO>();
	
	@Transient
	private String searchFilter;
	
	@Transient
	private String searchText;
	
	private static final Logger logger = Logger.getLogger(PatientDetailsPOJO.class);
	
	public PatientDetailsPOJO() {
		logger.info("Default Constructor PatientDetails");
	}

	
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getFirstVisitDate() {
		return firstVisitDate;
	}

	public void setFirstVisitDate(Date firstVisitDate) {
		this.firstVisitDate = firstVisitDate;
	}

	public String getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Set<VisitPOJO> getVisits() {
		return  Collections.unmodifiableSet(this.visits);
	}
}
