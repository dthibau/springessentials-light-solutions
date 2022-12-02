package org.formation.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

	private String message;
	private String level;
	private String apiVersion;
	
}
