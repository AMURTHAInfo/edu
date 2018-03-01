/**
 * 
 */
package main.java;

/**
 * @author Ninganna.c
 *
 */
public abstract class Car {
	private String model;
	private int noOfSeats;
	private int year;
	private double basicRentalFare;
	private double basicDiscount;
	private double  corporateDiscount;
	private double serviceTax;
	private double freeHours;
	private double freeKms;
	private double excesKmRate;
	private double excesHrRate;

	public Car(){
		
	};
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
	 */
	public Car(String model, int noOfSeats, int year, double basicRentalFare, double basicDiscount,
			double corporateDiscount, double serviceTax, double freeHours, double freeKms, double excesKmRate,
			double excesHrRate) {
		this.model = model;
		this.noOfSeats = noOfSeats;
		this.year = year;
		this.basicRentalFare = basicRentalFare;
		this.basicDiscount = basicDiscount;
		this.corporateDiscount = corporateDiscount;
		this.serviceTax = serviceTax;
		this.freeHours = freeHours;
		this.freeKms = freeKms;
		this.excesKmRate = excesKmRate;
		this.excesHrRate = excesHrRate;
	}
	
	public Car( String carId, String carName) {
		super();
		this.carId = carId;
		this.carName = carName;
	}
	private String carId;
	private String carName;
	
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the noOfSeats
	 */
	public int getNoOfSeats() {
		return noOfSeats;
	}
	/**
	 * @param noOfSeats the noOfSeats to set
	 */
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the basicRentalFare
	 */
	public double getBasicRentalFare() {
		return basicRentalFare;
	}
	/**
	 * @param basicRentalFare the basicRentalFare to set
	 */
	public void setBasicRentalFare(double basicRentalFare) {
		this.basicRentalFare = basicRentalFare;
	}
	/**
	 * @return the basicDiscount
	 */
	public double getBasicDiscount() {
		return basicDiscount;
	}
	/**
	 * @param basicDiscount the basicDiscount to set
	 */
	public void setBasicDiscount(double basicDiscount) {
		this.basicDiscount = basicDiscount;
	}
	/**
	 * @return the serviceTax
	 */
	public double getServiceTax() {
		return serviceTax;
	}
	/**
	 * @param serviceTax the serviceTax to set
	 */
	public void setServiceTax(double serviceTax) {
		this.serviceTax = serviceTax;
	}
	/**
	 * @return the freeHours
	 */
	public double getFreeHours() {
		return freeHours;
	}
	/**
	 * @param freeHours the freeHours to set
	 */
	public void setFreeHours(double freeHours) {
		this.freeHours = freeHours;
	}
	/**
	 * @return the freeKms
	 */
	public double getFreeKms() {
		return freeKms;
	}
	/**
	 * @param freeKms the freeKms to set
	 */
	public void setFreeKms(double freeKms) {
		this.freeKms = freeKms;
	}
	/**
	 * @return the corporateDiscount
	 */
	public double getCorporateDiscount() {
		return corporateDiscount;
	}
	/**
	 * @param corporateDiscount the corporateDiscount to set
	 */
	public void setCorporateDiscount(double corporateDiscount) {
		this.corporateDiscount = corporateDiscount;
	}
	/**
	 * @return the excesKmRate
	 */
	public double getExcesKmRate() {
		return excesKmRate;
	}
	/**
	 * @param excesKmRate the excesKmRate to set
	 */
	public void setExcesKmRate(double excesKmRate) {
		this.excesKmRate = excesKmRate;
	}
	/**
	 * @return the excesHrRate
	 */
	public double getExcesHrRate() {
		return excesHrRate;
	}
	/**
	 * @param excesHrRate the excesHrRate to set
	 */
	public void setExcesHrRate(double excesHrRate) {
		this.excesHrRate = excesHrRate;
	}

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
