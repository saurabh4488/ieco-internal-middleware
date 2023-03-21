package ieco.internal.middleware.domain.response;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ExpenseDistribution {

	private double savingsPercentage;
	
	private double basicPercentage;
	
	private double wantsPercentage;
}
