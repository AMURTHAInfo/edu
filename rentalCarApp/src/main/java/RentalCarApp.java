/**
 * 
 */
package main.java;

/**
 * @author Ninganna.c
 *
 */
public class RentalCarApp {

	CarFactory factory;
	
	
	public RentalCarApp(CarFactory factory) {
		this.factory = factory;
	}


	public void netRentalFare(String id,String name,String type,String model, int noOfSeats, int year,double kms,double hours){
		double carfare = 0;
		double grossRentalFare=0;
		double netRentalFare=0;
		Car car=factory.createCar( type, model, noOfSeats, year);
		carfare=getCarRent( car, kms, hours);
		if(carfare !=0){
			grossRentalFare=carfare - car.getBasicDiscount() -car.getCorporateDiscount();
			netRentalFare=grossRentalFare + (grossRentalFare*car.getServiceTax()/100);
		}
		System.out.println("***************************************************************");
		System.out.println("Rental Car Bill");
		System.out.println("***************************************************************");
		System.out.println("Customer Name : "+name);
		System.out.println("Customer Id   : "+id);
		System.out.println("Car Type      : "+type);
		System.out.println("Car Model     : "+car.getModel());
		System.out.println("year          : "+car.getYear());
		System.out.println("No Of Seats   : "+car.getNoOfSeats());
		System.out.println("Car Type      : "+type);
		System.out.println("***************************************************************");
		System.out.println("Total Kms                 : "+kms+"Km");
		int integer = (int)hours;
		int decimal = (int) Math.round((hours - integer)*60);
		System.out.println("Total Time               : "+integer+":"+decimal+"");
		System.out.println("***************************************************************");
		System.out.println("Total Fare of the Car     : "+String.format("%.2f", carfare)+"Rs");
		System.out.println("Total Fare After Discount : "+String.format("%.2f", grossRentalFare)+"Rs");
		System.out.println("Net Total Fare of the Car : "+String.format("%.2f", netRentalFare)+"Rs");
		System.out.println("***************************************************************");
	}
	
	public double getCarRent(Car car,double kms,double hours){
		
		double carfare = 0;
		if(car != null){
			if(kms > car.getFreeKms() && hours < car.getFreeHours()){
				carfare=car.getBasicRentalFare()+((kms-car.getFreeKms())*car.getExcesKmRate());
			}
			if(kms < car.getFreeKms() && hours > car.getFreeHours()){
				carfare=car.getBasicRentalFare()+((hours-car.getFreeHours())*car.getExcesHrRate());	
			}
			if(kms > car.getFreeKms() && hours > car.getFreeHours()){
				double fareWithExKm=car.getBasicRentalFare()+((kms-car.getFreeKms())*car.getExcesKmRate());
				double fareWithExHr=car.getBasicRentalFare()+((hours-car.getFreeHours())*car.getExcesHrRate());
				if(fareWithExKm > fareWithExHr){
					carfare=fareWithExKm;
				}
				else{
					carfare=fareWithExHr;
				}
			}
			
		}
		else{
			
		}
		return carfare;
	}
}
