package com.spring.boot.msk.mobile.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mobile {
	
	public Mobile(int id, String name, int price) {
		 this.id = id;
		 this.name= name;
		 this.price = price;
		}

	   @XmlElement
		private int id;
		
	   @XmlElement
		private String name;
		
	   @XmlElement
		private int price;
		
		private Status status;
		
		private Lob lob; 
		
		private LocalDate publicationDate;
		
		
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

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}


}
