/**
 * 
 */
package main.java;

/**
 * @author Ninganna.c
 *
 */
public class ExecutiveCar extends Car{

	public ExecutiveCar(String model, int noOfSeats, int year, double basicRentalFare, double basicDiscount,
			double corporateDiscount, double serviceTax, double freeHours, double freeKms, double excesKmRate,
			double excesHrRate) {
		super(model, noOfSeats, year, basicRentalFare, basicDiscount, corporateDiscount, serviceTax, freeHours, freeKms,
				excesKmRate, excesHrRate);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param model
	 * @param noOfSeats
	 * @param year
	 * @param basicRentalFare
	 * @param basicDiscount
	 * @param corporateDiscount
	 * @param serviceTax
	 * @param freeHours
	 * @param freeKms
	 * @param excesKmRate
	 * @param excesHrRate
	 * @param carId
	 * @param carName
	 */
	public ExecutiveCar( String carId, String carName) {
		super();
		this.carId = carId;
		this.carName = carName;
	}

	private String carId;
	private String carName;
	/**
	 * @return the carId
	 */
	public String getCarId() {
		return carId;
	}
	/**
	 * @param carId the carId to set
	 */
	public void setCarId(String carId) {
		this.carId = carId;
	}
	/**
	 * @return the carName
	 */
	public String getCarName() {
		return carName;
	}
	/**
	 * @param carName the carName to set
	 */
	public void setCarName(String carName) {
		this.carName = carName;
	}


}
