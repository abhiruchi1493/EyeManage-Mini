package com.app.eyemanage.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name="VisitDetails")
@Component
public class VisitPOJO {
	
	@Id
	@SequenceGenerator(name="Visit_SEQ", sequenceName="Visit_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Visit_SEQ")
	@Column(unique = true, nullable = false)
	private Integer visitId;
	
	@Column(unique = false, nullable = true)
	private String description;
	
	@Column(unique = false, nullable = false)
	private Date visitDate;
	
	@Column(unique = false, nullable = false)
	private Float fees;
	
	@ManyToOne
	@JoinColumn( name = "patientId", nullable = false )
	private PatientDetailsPOJO patient;
	
	@Transient
	private String searchFilter;
	
	@Transient
	private String searchText;
	
}
