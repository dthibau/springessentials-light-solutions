package org.formation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties("hello")
@Validated
@Data
public class HelloProperties {
	/**
	 * Greeting message returned by the Hello Rest service.
	 */
	@NotEmpty
	private String greeting = "";

	@Min(0) @Max(1) 
	private int position;
	
	@Pattern(regexp = "uppercase|lowercase|camelcase", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String style = "Upper";
	
	@NotNull
	private CaseEnum caseType;
}