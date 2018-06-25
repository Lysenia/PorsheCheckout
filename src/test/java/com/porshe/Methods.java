package com.porshe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Methods {
	
	public static double fromStringToNumberFormat(String word) {
		String str = "";
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (Character.isDigit(word.charAt(i))) {
				str += ch;
			}
		}
		double inNumberFormat = Double.valueOf(str);
		return inNumberFormat;
	}
	
	public static void checkEquipmentPrice(WebDriver driver, String... prices) {
		int optionsPrice = 0;
		for (String each : prices) {
			optionsPrice += fromStringToNumberFormat(each);
		}

		if (findEquipmentPrice(driver) == optionsPrice) {
			System.out.println("PASS. Equipments Price is equal to the options price.");
		} else {
			System.out.println("FAIL. NOT A MATCH EQUIPMENTS PRICE");
			System.out.println("EQUIPMENTS PRICE : " + findEquipmentPrice(driver));
			System.out.println();
		}
	}
	
		public static double findEquipmentPrice(WebDriver driver) {
			String equipmentPrice = driver.findElement(By.xpath("/html/body/div/div/div/section/section/div/div[2]/div[2]"))
					.getText();

			double equipmentPrice1 = fromStringToNumberFormat(equipmentPrice);
			return equipmentPrice1;
		
	}
		public static double findTotalPrice(WebDriver driver) {
			String totalPrice = driver.findElement(By.xpath("/html/body/div/div/div/section/section/div/div[4]/div[2]"))
					.getText();

			double totalPrice1 = fromStringToNumberFormat(totalPrice);
			return totalPrice1;
		}
		
		public static void checkTotalPrice(WebDriver driver) {
			if (findTotalPrice(driver) == findBasePrice(driver) + findEquipmentPrice(driver) + findFees(driver)) {
				System.out.println("PASS. Total price is equal to the sum of given values.");
			} else {
				System.out.println("FAIL. NOT A MATCH TOTAL PRICE");
				System.out.println("TOTAL PRICE : " + findTotalPrice(driver));
				System.out.println(
						"ALL VALUE ARE : " + (findBasePrice(driver) + findEquipmentPrice(driver) + findFees(driver)));
			}
		}
			
			public static double findBasePrice(WebDriver driver) {
				String basePrice = driver.findElement(By.xpath("/html/body/div/div/div/section/section/div/div[1]/div[2]"))
						.getText();
				double basePrice1 = fromStringToNumberFormat(basePrice);

				return basePrice1;
			}

		
public static double findFees(WebDriver driver) {
	String feesPrice = driver.findElement(By.xpath("/html/body/div/div/div/section/section/div/div[3]/div[2]"))
			.getText();

	double feesPrice1 = fromStringToNumberFormat(feesPrice);
	return feesPrice1;
}
}
