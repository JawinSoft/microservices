package com.spring.boot.msk.mobile.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mobile { 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private Integer price;

	@Column
	private Status status;

	@Column
	private Lob lob;

	@Column(name="publication_date")
	private LocalDate publicationDate= LocalDate.now();
	
	@Column(name="country_code")
	private String countryCode;
	
	@Column
	private Boolean isActive;

}
