package application;

public class EnergyCalculator {
	// Constants for unit rates
	private static final double SVT_ELECTRICITY_RATE = 0.20; // pence per kWh
	private static final double SVT_GAS_RATE = 0.04; // pence per kWh

	private static final double FIXED_ELECTRICITY_RATE = 0.18; // pence per kWh
	private static final double FIXED_GAS_RATE = 0.035; // pence per kWh

	private static final double ECONOMY7_DAY_RATE = 0.15; // pence per kWh (day rate)
	private static final double ECONOMY7_NIGHT_RATE = 0.08; // pence per kWh (night rate)
	// Constants for standing charges
	private static final double SVT_STANDING_CHARGE = 25.0; // pence per day
	private static final double FIXED_STANDING_CHARGE = 20.0; // pence per day
	private static final double ECONOMY7_STANDING_CHARGE = 22.0; // pence per day

	// Calculate cost for Standard Variable Tariff
	public static double calculateSVTCost(double electricityUsage, double gasUsage, int days) {
		double electricityCost = electricityUsage * SVT_ELECTRICITY_RATE;
		double gasCost = gasUsage * SVT_GAS_RATE;
		double standingCharge = SVT_STANDING_CHARGE * days; // per days standing charge

		return electricityCost + gasCost + standingCharge;
	}
	// Calculate cost for Fixed Tariff
	public static double calculateFixedCost(double electricityUsage, double gasUsage, int days) {
		double electricityCost = electricityUsage * FIXED_ELECTRICITY_RATE;
		double gasCost = gasUsage * FIXED_GAS_RATE;
		double standingCharge = FIXED_STANDING_CHARGE * days; // per days standing charge

		return electricityCost + gasCost + standingCharge;
	}

	// Calculate cost for Economy 7 Tariff
	public static double calculateEconomy7Cost(double electricityDayUsage, double electricityNightUsage,
			double gasUsage, int days) {
		double electricityDayCost = electricityDayUsage * ECONOMY7_DAY_RATE;
		double electricityNightCost = electricityNightUsage * ECONOMY7_NIGHT_RATE;
		double gasCost = gasUsage * ECONOMY7_NIGHT_RATE;
		double standingCharge = ECONOMY7_STANDING_CHARGE * days; // per days standing charge

		return electricityDayCost + electricityNightCost + gasCost + standingCharge;
	}
}
