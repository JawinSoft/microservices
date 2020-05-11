package com.spring.boot.msk.country.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.boot.msk.country.model.Country;

@Repository
public class CountryRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Country> getAllCountries(){
		String query = "select code, name, continent, population from country";
		
		RowMapper<Country> rm = new RowMapper<Country>() {

			@Override
			public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
				Country country = new Country();
				country.setCode(rs.getString("Code"));
				country.setName(rs.getString("name"));
				country.setContinent(rs.getString("continent"));
				country.setPopulate(rs.getLong("population"));
				return country;
			}
		};
		
		return jdbcTemplate.query(query, rm);
		
		
	}
	
}
