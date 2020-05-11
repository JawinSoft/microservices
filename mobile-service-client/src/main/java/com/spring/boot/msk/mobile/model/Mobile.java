package com.spring.boot.msk.mobile.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Mobile {

	private int id;

	private String name;

	private int price;

	private Status status;

	private Lob lob;

	private LocalDate publicationDate;

}
