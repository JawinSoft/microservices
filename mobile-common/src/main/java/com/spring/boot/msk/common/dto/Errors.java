package com.spring.boot.msk.common.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Errors {

	private List<Error> errors;
}
