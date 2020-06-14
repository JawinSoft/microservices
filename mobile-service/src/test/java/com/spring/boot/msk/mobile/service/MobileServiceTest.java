package com.spring.boot.msk.mobile.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.boot.msk.common.dto.MobileDTO;
import com.spring.boot.msk.mobile.entity.Lob;
import com.spring.boot.msk.mobile.entity.Mobile;
import com.spring.boot.msk.mobile.entity.Status;
import com.spring.boot.msk.mobile.exception.MobileNotFoundException;
import com.spring.boot.msk.mobile.repository.MobileRepository;

@SpringBootTest(classes  = MobileService.class)
public class MobileServiceTest {

	@MockBean
	private MobileRepository mockMobileRepository;
	
	@Autowired
	private MobileService mobileService;
	
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println();
		System.out.println("This statement is just before All Junit Test cases execution..!");
		System.out.println();
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println();
		System.out.println("This statement is just After All Junit Test cases execution .. !");
		System.out.println();
	}
	
	static {
		System.err.println(" This is Static Block ");
	}
	
	@BeforeEach
	public void init() {
		System.out.println();
		System.out.println("For Every Test case execution before");
		System.out.println();
	}
	
	@AfterEach
	public void clean() {
		System.out.println();
		System.out.println("For Every Test case execution After ..!");
		System.out.println();
	}
	
	
	@Test
	public void testGetAllMobilesWhenRepositoryReturnsData() {
		Mobile mobile = Mobile.builder().id(1).name("MI").status(Status.AVAILABLE).lob(Lob.INDIRECT).price(10000).publicationDate(LocalDate.now()).build();
		when(mockMobileRepository.findAll()).thenReturn(Arrays.asList(mobile));
		List<MobileDTO> mobileDtos =  mobileService.getMoblies();
		assertNotNull(mobileDtos);
		assertEquals(1, mobileDtos.size());
		assertEquals("MI", mobileDtos.get(0).getName());		
	}
	
	

	@Test
	public void testGetAllMobilesWhenRepostioryNotReturningAnyData() {
		List<MobileDTO> mobileDtos =  mobileService.getMoblies();
		assertNotNull(mobileDtos);
		assertEquals(0, mobileDtos.size());
	}
	
	

	@Test
	public void testGetAllMobilesWhenRepositoryReturnsNull() {
		when(mockMobileRepository.findAll()).thenReturn(null);
		List<MobileDTO> mobileDtos =  mobileService.getMoblies();
		assertNotNull(mobileDtos);
		assertEquals(0, mobileDtos.size());
	
	}
	
	
	@Test
	public void testgGetMobileByIdMobilesWhenRepositoryReturnsNull() {
		when(mockMobileRepository.findById(1)).thenReturn(Optional.empty());		
		assertThrows(MobileNotFoundException.class , () -> mobileService.getMobileById(1));	
	}
	
	
	


}
