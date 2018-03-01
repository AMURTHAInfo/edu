/**
 * 
 */
package main.java;

/**
 * @author Ninganna.c
 *
 */
public class SuvCar extends Car{

	public SuvCar(String model, int noOfSeats, int year, double basicRentalFare, double basicDiscount,
			double corporateDiscount, double serviceTax, double freeHours, double freeKms, double excesKmRate,
			double excesHrRate) {
		super(model, noOfSeats, year, basicRentalFare, basicDiscount, corporateDiscount, serviceTax, freeHours, freeKms,
				excesKmRate, excesHrRate);
		// TODO Auto-generated constructor stub
	}
	public SuvCar( String carId, String carName) {
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
