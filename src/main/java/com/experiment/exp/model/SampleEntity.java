package com.experiment.exp.model;

import org.springframework.data.annotation.AccessType;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
/*
@Entity(name = "SampleEntity") is for the JPA entity name (used in JPQL queries).
@Table(name = "SampleEntity") is for the database table name (used in the actual database schema).
*/

@Data
public class SampleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	
	//Both runtime validation and database enforcement, 
	//use @NotNull and @Column(nullable = false) together.
	@NotNull
	@Column(nullable = false)
	private String name;
	
	private String email;
	

}
