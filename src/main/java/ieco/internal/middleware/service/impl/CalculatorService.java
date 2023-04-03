package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.request.CalculatorRequest;
import ieco.internal.middleware.domain.response.CalculatorResponse;
import ieco.internal.middleware.domain.response.ExpenseDistribution;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Component
public class CalculatorService {

	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

	public CalculatorResponse calculate(CalculatorRequest req) {
		if (req.getType().equalsIgnoreCase("expectedReturns")) {
			log.info("in Calc service");
			return expectedReturns(req);
		} else if (req.getType().equalsIgnoreCase("crorepati")) {
			log.info("in Calc Controller crorepati");
			return crorePati(req);
		} else if (req.getType().equalsIgnoreCase("monthlytake")) {
			log.info("in Calc Controller monthlytake");
			return monthlyTake(req);
		} else if (req.getType().equalsIgnoreCase("retirement")) {
			log.info("in Calc Controller monthlytake");
			return retirement(req);
		} else if (req.getType().equalsIgnoreCase("expense")) {
			log.info("in Calc Controller monthlytake");
			return expense(req);
		}
		CalculatorResponse calculatorRes = new CalculatorResponse();
		calculatorRes.setStatus("Failure");
		calculatorRes.setErrDescription("Please provide a valid type");
		return calculatorRes;

	}

	public CalculatorResponse expectedReturns(CalculatorRequest calculatorReq) {

		CalculatorResponse calculatorRes = new CalculatorResponse();
		double lumpsum_return = 0;
		double total_return = 0;

		if (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(calculatorReq.getSipAmount())) == 0) {
			double lumSumRoi = (double) calculatorReq.getRateOfInterest() / 100;
			double lumSumRoiRoiForSixPercent = (double) 6 / 100;
			ArrayList<Double> lumSumReturnListForInputROI = getLumSumReturn(lumSumRoi, calculatorReq);

			ArrayList<Double> lumSumReturnListForSixROI = getLumSumReturn(lumSumRoiRoiForSixPercent, calculatorReq);
			lumpsum_return = (double) lumSumReturnListForInputROI.get(lumSumReturnListForInputROI.size() - 1);

			total_return = lumpsum_return;
			calculatorRes.setSipReturn("0");
			calculatorRes.setSipReturnList(lumSumReturnListForInputROI);
			calculatorRes.setSipReturnListForSIXPercent(lumSumReturnListForSixROI);
			calculatorRes.setTotalReturn(BigDecimal.valueOf(total_return).toPlainString());
			calculatorRes.setLumpsumReturn(lumpsum_return);

		} else if (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(calculatorReq.getLumsumAmount())) == 0) {
			double sipRoi = (double) calculatorReq.getRateOfInterest() / (calculatorReq.getSipFrequency() * 100);
			double sipRoiForSixPercent = (double) 6 / (calculatorReq.getSipFrequency() * 100);
			ArrayList<Double> sipReturnListForInputROI = getSipReturn(sipRoi, calculatorReq);

			ArrayList<Double> sipReturnListForSixROI = getSipReturn(sipRoiForSixPercent, calculatorReq);
			total_return = (double) sipReturnListForInputROI.get(sipReturnListForInputROI.size() - 1) + lumpsum_return;
			calculatorRes.setSipReturn(
					BigDecimal.valueOf((double) sipReturnListForInputROI.get(sipReturnListForInputROI.size() - 1))
							.toPlainString());
			calculatorRes.setTotalReturn(BigDecimal.valueOf(total_return).toPlainString());
			calculatorRes.setSipReturnList(sipReturnListForInputROI);
			calculatorRes.setSipReturnListForSIXPercent(sipReturnListForSixROI);
		} else if (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(calculatorReq.getLumsumAmount())) != 0
				&& BigDecimal.ZERO.compareTo(BigDecimal.valueOf(calculatorReq.getSipAmount())) != 0) {


			double sipRoi = (double) calculatorReq.getRateOfInterest() / (calculatorReq.getSipFrequency() * 100);
			double sipRoiForSixPercent = (double) 6 / (calculatorReq.getSipFrequency() * 100);

			double lumSumRoi = (double) calculatorReq.getRateOfInterest() / 100;
			double lumSumRoiRoiForSixPercent = (double) 6 / 100;

			ArrayList<Double> sipReturnListForInputROI = getSipReturn(sipRoi, calculatorReq);
			ArrayList<Double> lumSumReturnListForInputROI = getLumSumReturn(lumSumRoi, calculatorReq);
			ArrayList<Double> result = getTotalReturnList(sipReturnListForInputROI, lumSumReturnListForInputROI);

			ArrayList<Double> sipReturnListForSixROI = getSipReturn(sipRoiForSixPercent,calculatorReq);
			ArrayList<Double> lumSumReturnListForSixROI = getLumSumReturn(lumSumRoiRoiForSixPercent, calculatorReq);

			total_return = (double) result.get(result.size() - 1);
			calculatorRes.setSipReturn(
					BigDecimal.valueOf((double) sipReturnListForInputROI.get(sipReturnListForInputROI.size() - 1))
							.toPlainString());
			calculatorRes.setSipReturnList(result);
			calculatorRes.setTotalReturn(BigDecimal.valueOf(total_return).toPlainString());
			 calculatorRes.setSipReturnListForSIXPercent(getTotalReturnList(sipReturnListForSixROI, lumSumReturnListForSixROI));
		}

		calculatorRes.setLumpsumAmount(calculatorReq.getLumsumAmount());

		calculatorRes.setPeriod(calculatorReq.getPeriod());
		calculatorRes.setRoi(calculatorReq.getRateOfInterest());
		calculatorRes.setSipAmount(calculatorReq.getSipAmount());
		calculatorRes.setSipFrequency(calculatorReq.getSipFrequency());

		calculatorRes.setType(calculatorReq.getType());

		calculatorRes.setStatus("Success");
		return calculatorRes;
	}

	public CalculatorResponse crorePati(CalculatorRequest calculatorReq) {

		// sip_freq is fixed
		int sip_freq = 12;

		// infaltionRate is fixed as of now
		double infaltionRate = (double) 0 / 100;

		int period = calculatorReq.getPeriod();
		double inputAmt = calculatorReq.getSipReturn();

		double returnPercent = calculatorReq.getRateOfInterest() / 100.0;
		double returnPercentPerMonth = returnPercent / sip_freq;

		int noOfPayments = sip_freq * period;

		double inflationAdjustedAmount = inputAmt * Math.pow((1 + infaltionRate), period);

		Double sip_amt = (inflationAdjustedAmount / (returnPercentPerMonth + 1))
				* (returnPercentPerMonth / (Math.pow(returnPercentPerMonth + 1, noOfPayments) - 1));
		double principleAmount = sip_amt * period * 12;
		double principlePercentage = (principleAmount * 100) / inputAmt;
		double returnAmount = inputAmt - principleAmount;
		CalculatorResponse calculatorRes = new CalculatorResponse();

		calculatorRes.setSipFrequency(sip_freq);
		calculatorRes.setPeriod(period);
		calculatorRes.setSipReturn(BigDecimal.valueOf(inputAmt).toPlainString());


		calculatorRes
				.setInflationAdjustedAmount(BigDecimal.valueOf(inflationAdjustedAmount).setScale(0, RoundingMode.HALF_UP));
		calculatorRes.setReturnPercent(round(calculatorReq.getRateOfInterest(), 0));
		calculatorRes.setReturnPercentPerMonth(round(returnPercentPerMonth, 3));
		calculatorRes.setSipAmount(round(sip_amt, 0));
		calculatorRes.setPrincipleAmount(BigDecimal.valueOf(principleAmount).setScale(0, RoundingMode.HALF_UP));
		calculatorRes.setPrincipleAmountPercentage(round(principlePercentage, 2));
		calculatorRes.setReturnAmount(BigDecimal.valueOf(returnAmount).setScale(0, RoundingMode.HALF_UP));
		calculatorRes.setReturnAmountPercentage(round(100 - principlePercentage, 2));
		calculatorRes.setStatus("Success");
		return calculatorRes;
	}

	public CalculatorResponse monthlyTake(CalculatorRequest calculatorReq) {
		double amount = calculatorReq.getAmount();
		double years = calculatorReq.getTenure();
		double months = 0;
		if ((years * 12) % 1 == 0) {
			months = (years * 12);
		}

		double roiPercentage = getROI(months);
		double roi = (double) roiPercentage / 100;
		double monthlytake = amount * roi / 12;
		CalculatorResponse calculatorRes = new CalculatorResponse();
		calculatorRes.setSipAmount(amount);
		calculatorRes.setReturnPercent(roiPercentage);
		calculatorRes.setMonthlyTake(round(monthlytake, 2));
		calculatorRes.setStatus("Success");

		return calculatorRes;
	}

	CalculatorResponse retirement(CalculatorRequest calculatorReq) {

		final int lifeExpectencyAge = 75;// default value is 75

		double rateOfInterest = 1.89 / 100;// default roi is 1.89
		double inflationRate = (double) 6 / 100;

		int N = lifeExpectencyAge - calculatorReq.getRetirementAge();

		int diffAge = calculatorReq.getRetirementAge() - calculatorReq.getCurrentAge();

		double yearlyExpensesAtRetirement = (calculatorReq.getExpenses() * (Math.pow(1 + inflationRate, diffAge))) * 12;

		BigDecimal corpusAtRetirement = BigDecimal.valueOf(
				(yearlyExpensesAtRetirement * (1 - (Math.pow(1 / (1 + rateOfInterest), (N))))) / rateOfInterest).setScale(0, RoundingMode.HALF_UP);


		double sipReturn = calculatorReq.getSipReturn() / (12 * 100);

		double firstValue = (Math.pow((sipReturn + 1), diffAge * 12)) - 1;
		double secondValue = firstValue * (sipReturn + 1);
		BigDecimal thirdValue = corpusAtRetirement.multiply(BigDecimal.valueOf(sipReturn)).divide(BigDecimal
				.valueOf(secondValue), RoundingMode.HALF_EVEN).setScale(0, RoundingMode.HALF_UP);
		double expensesAtLifeExpectency = calculatorReq.getExpenses()
				* Math.pow((1 + inflationRate), lifeExpectencyAge - calculatorReq.getCurrentAge());

		CalculatorResponse calculatorRes = new CalculatorResponse();

		calculatorRes.setExpensesAtRetirement(round(yearlyExpensesAtRetirement, 2));
		calculatorRes.setExpensesAtRetirementPerMonth(round(yearlyExpensesAtRetirement / 12, 2));
		calculatorRes.setCorpus(corpusAtRetirement);
		calculatorRes.setExpensesAtLifeExpectency(round(expensesAtLifeExpectency, 2));
		calculatorRes.setSip(thirdValue);
		calculatorRes.setStatus("Success");

		return calculatorRes;
	}

	private CalculatorResponse expense(CalculatorRequest req) {

		final int minimumSavingsPercentage = 20;

		int numberOfyears = 10;

		int totalExpenses = req.getBasicNeeds() + req.getSavings() + req.getWants();

		double savingsPercentage = calculatePercentage(req.getSavings(), totalExpenses);

		double basicPercentage = calculatePercentage(req.getBasicNeeds(), totalExpenses);

		double wantsPercentage = calculatePercentage(req.getWants(), totalExpenses);

		double sipRoi = (double) 10 / (100 * 12); // roi 10 is fixed


		double sipAmount = totalExpenses * Math.abs(minimumSavingsPercentage - savingsPercentage) / 100;

		double sip_return = sipAmount * (Math.pow((sipRoi + 1), (numberOfyears * 12)) - 1) / sipRoi * (sipRoi + 1);

		ExpenseDistribution expenseDistribution = new ExpenseDistribution();

		expenseDistribution.setBasicPercentage(round(basicPercentage, 2));
		expenseDistribution.setSavingsPercentage(round(savingsPercentage, 2));
		expenseDistribution.setWantsPercentage(round(wantsPercentage, 2));
		CalculatorResponse calculatorRes = new CalculatorResponse();
		calculatorRes.setTotalExpenses(totalExpenses);
		calculatorRes.setExpenseDistribution(expenseDistribution);
		calculatorRes.setSipAmount(round(sipAmount, 2));
		calculatorRes.setAmount(BigDecimal.valueOf(sip_return).setScale(0, RoundingMode.HALF_UP).toPlainString());
		calculatorRes.setStatus("Success");
		return calculatorRes;
	}

	public static double getROI(double months) {

		if (isBetween(months, 6, 9)) {

			return 5.80;
		} else if (isBetween(months, 9, 12)) {
			return 6.05;
		} else if (isBetween(months, 12, 23)) {
			return 6.20;
		} else if (isBetween(months, 23, 60)) {
			return 6.00;
		} else if (isBetween(months, 60, 120)) {
			return 5.50;
		}

		return 0;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public double calculatePercentage(int obtained, int total) {

		return (double) (obtained * 100) / total;
	}

	ArrayList<Double> getSipReturn(double sipRoi, CalculatorRequest calculatorReq) {

		double inputAmont = calculatorReq.getSipAmount();

		ArrayList<Double> list = new ArrayList<>();
		for (int i = 1; i <= calculatorReq.getPeriod(); i++) {
			int sip_tenure = calculatorReq.getSipFrequency() * i;
			double sip_return = inputAmont * (Math.pow((sipRoi + 1), sip_tenure) - 1) / sipRoi * (sipRoi + 1);

			list.add(round(sip_return, 2));
		}
		return list;

	}

	ArrayList<Double> getLumSumReturn(double sipRoi, CalculatorRequest calculatorReq) {
		ArrayList<Double> list = new ArrayList<>();
		for (int i = 1; i <= calculatorReq.getPeriod(); i++) {

			double lumpsum_return = calculatorReq.getLumsumAmount() * (Math.pow(sipRoi + 1, i));

			list.add(round(lumpsum_return, 2));
		}
		return list;

	}

	ArrayList<Double> getTotalReturnList(ArrayList<Double> l1,ArrayList<Double> l2){
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < l1.size(); i++) {
			result.add(round((double) l1.get(i) + (double) l2.get(i),2));
		}

		return result;
	}

	public static boolean isBetween(double x, double lower, double upper) {
		return lower <= x && x <= upper;
	}
}
