package com.spring.boot.msk.common.client.dto;

import java.util.List;

import com.spring.boot.msk.common.model.Country;
import com.spring.boot.msk.common.model.MobileAccessory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileInfo {

	private int id;

	private String name;

	private int price;

	private String status;

	private String lob;

	private String publictionDate;

	private Country country;

	private List<MobileAccessory> mobileAccessories;

}
