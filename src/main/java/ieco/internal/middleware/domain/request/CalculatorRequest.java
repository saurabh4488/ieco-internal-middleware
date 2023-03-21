package ieco.internal.middleware.domain.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

public class CalculatorRequest {

	@NotBlank(message = "Type field is mandatory")
	private String type;

	private int sipFrequency;

	private double sipAmount;

	private int period;

	private int rateOfInterest;

	private double lumsumAmount;

	private double sipReturn;

	private double amount;

	private double tenure;

	// Retirement

	private int currentAge;

	private int retirementAge;

	private double expenses;

	private double expectedReturn;

	// expenses

	private int wants;

	private int savings;

	private int basicNeeds;

}
