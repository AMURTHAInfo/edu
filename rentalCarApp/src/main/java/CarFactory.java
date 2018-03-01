/**
 * 
 */
package main.java;

import java.util.ArrayList;

/**
 * @author Ninganna.c
 *
 */
public class CarFactory {

	public Car createCar(String type,String model, int noOfSeats, int year){
		double economyCarBasicFare=750;
		double economyCarBasicDisc=100;
		double economyCarPerExtraKm=12;
		double economyCarPerExtraHr=125;
		double economyCarCorporateDisc=0;
		
		double executiveCarBasicFare=economyCarBasicFare+(1.5*economyCarBasicFare);
		double executiveCarBasicDisc=200;
		double executiveCarPerExtraKm=economyCarPerExtraKm+(economyCarPerExtraKm/2);
		double executiveCarPerExtraHr=economyCarPerExtraHr+(economyCarPerExtraHr*20/100);
		double executiveCarCorporateDisc=executiveCarBasicFare/10;
		
		double suvCarBasicFare=750;
		double suvCarBasicDisc=economyCarBasicDisc*2;
		double suvCarPerExtraKm=economyCarPerExtraKm+(economyCarPerExtraKm*75/100);
		double suvCarPerExtraHr=economyCarPerExtraHr+(economyCarPerExtraHr/2);
		double suvCarCorporateDisc=suvCarBasicFare*15/100;
		
		if(type.equals("1")){
			return new EconomyCar( model, noOfSeats, year,economyCarBasicFare,economyCarBasicDisc,economyCarCorporateDisc,6,8,50,economyCarPerExtraKm,economyCarPerExtraHr);
		}
		else if(type.equals("2")){
			return new ExecutiveCar(model, noOfSeats, year,executiveCarBasicFare,executiveCarBasicDisc,executiveCarCorporateDisc,6,8,100,executiveCarPerExtraKm,executiveCarPerExtraHr);
		}
		else if(type.equals("3")){
			return new SuvCar(model, noOfSeats, year,suvCarBasicFare,suvCarBasicDisc,suvCarCorporateDisc,6,8,150,suvCarPerExtraKm,suvCarPerExtraHr);
		}
		else{
			return null;
		}
	}
	public ArrayList<Car> getAllCars(){
		ArrayList<Car> carList = new ArrayList<Car>();
		carList.add(new EconomyCar("1","Economy Car"));
		carList.add(new EconomyCar("2","Executive Car"));
		carList.add(new EconomyCar("3","Suv Car"));
		return carList;
	}
}
