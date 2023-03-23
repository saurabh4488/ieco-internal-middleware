package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CalculatorResponse {

	private String type;

	private int sipFrequency;

	private int period;

	private double sipAmount;

	private double roi;

	private double returnPercent;

	private String sipReturn;
	private ArrayList<Object> sipReturnList;
	private ArrayList<Object> sipReturnListForSIXPercent;
	private double lumpsumAmount;

	private double lumpsumReturn;

	private String totalReturn;

	private BigDecimal inflationAdjustedAmount;

	private double returnPercentPerMonth;

	private double monthlyTake;

	private String status;

	private String errDescription;

	private BigDecimal principleAmount;

	private double principleAmountPercentage;

	private BigDecimal ReturnAmount;

	private double ReturnAmountPercentage;

	// Retirement

	private double expensesAtRetirement;

	private double expensesAtRetirementPerMonth;

	private BigDecimal corpus;

	private BigDecimal sip;

	// expenses
	private int totalExpenses;

	private BigInteger savingRatio;

	private ExpenseDistribution expenseDistribution;

	private String amount;

	private double expensesAtLifeExpectency;

	
}
