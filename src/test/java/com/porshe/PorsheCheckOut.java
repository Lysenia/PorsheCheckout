package com.porshe;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PorsheCheckOut {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","/Users/lesia/Documents/selenium dependencies/drivers/chromedriver");
		
//WebDriverManager.chromedriver().setup();
				
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				
// Step 1
			
				driver.get("https://www.porsche.com/usa/modelstart/");

// Step 2
				
				driver.navigate().to("https://www.porsche.com/usa/modelstart/all/?modelrange=718");

// Step 3
				
				String basePrice1 = driver.findElement(By.className("m-14-model-price")).getText();
				double price1 = Methods.fromStringToNumberFormat(basePrice1) / 100;
				System.out.println("Base Price in the beginning is " + price1);

// Step 4
				
				driver.findElement(By.className("m-14-quick-link")).click();

// Step 5
				
				Set<String> handles = driver.getWindowHandles();
				Iterator<String> it = handles.iterator();
				String parent = it.next();
				String child = it.next();
				driver.switchTo().window(child);
	
				String basePrice2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
				System.out.println(basePrice2);
				double price2 = Methods.fromStringToNumberFormat(basePrice2);
				System.out.println("New Base Price is " + price2);

				if (price1 == price2) {
					System.out.println("Pass: two base prices are same ");
				} else {
					System.out.println("Pass: two base prices are different ");

				}
				
//Step 7
				
				String equipmentPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
				double equipPrice = Methods.fromStringToNumberFormat(equipmentPrice);
				System.out.println("Equipment price is " + equipPrice);
				if (equipPrice == 0) {
					System.out.println("Pass: equipment price is " + equipPrice);
				} else {
					System.out.println("Fail: equipment price is " + equipPrice);
				}
//Step 8
				
				String totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
				double total = Methods.fromStringToNumberFormat(totalPrice);
				System.out.println("Total price: " + total);

		
				String handlingPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
				double handlingFee = Methods.fromStringToNumberFormat(handlingPrice);
				double sum = handlingFee + equipPrice + price2;
				
				if(total==sum) {
					System.out.println("Pass");
				}else{
					System.out.println("Fail");;
				}
				
//Step 9
	driver.findElement(By.cssSelector("span[style='background-color: rgb(0, 120, 138);']")).click();
						
// Step 10 
						String colorPrice = driver.findElement(By.id("s_exterieur_x_FJ5")).getAttribute("data-price");

						Methods.checkEquipmentPrice(driver, colorPrice);

// Step 11 
						Methods.checkTotalPrice(driver);
						
//Step 12 Select 20" Carrera Sport Wheels
								driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
								driver.findElement(By.xpath("//*[@id=\"submenu_exterieur_x_AA_submenu_x_IRA\"]/a")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
								
// Step 13
								String wheelsPrice = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IRA\"]/div[2]/div[1]/div/div[2]"))
										.getText();
								
								Methods.checkEquipmentPrice(driver, colorPrice, wheelsPrice);

// Step 14 
								Methods.checkTotalPrice(driver);

//Step 15 
								driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
								driver.findElement(By.xpath("//*[@id=\"submenu_interieur_x_AI_submenu_x_submenu_parent\"]/span")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//*[@id=\"submenu_interieur_x_AI_submenu_x_submenu_seats\"]/a")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//*[@id=\"s_interieur_x_PP06\"]")).click();

// Step 16 
								String seatPrice = driver.findElement(By.xpath("//*[@id=\"seats_73\"]/div[2]/div[1]/div[3]/div")).getText();

								Methods.checkEquipmentPrice(driver, colorPrice, wheelsPrice, seatPrice);
// Step 17 
								Methods.checkTotalPrice(driver);

// Step 18 
								driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
								driver.findElement(
										By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_submenu_parent\"]/span")).click();
								Thread.sleep(3000);
								driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IIC\"]/a")).click();
								Thread.sleep(1000);
								
//	Step 19 
								driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH_x_c01_PEKH\"]")).click();

//	Step 20 
								
								String interiorPrice = driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div"))
										.getText();

								Methods.checkEquipmentPrice(driver, colorPrice, wheelsPrice, seatPrice, interiorPrice);

// Step 21 
								Methods.checkTotalPrice(driver);

// Step 22 
								driver.findElement(By.xpath("//*[@id=\"s_conf_submenu\"]/div/div")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//*[@id=\"submenu_individualization_x_individual_submenu_x_IMG\"]/a")).click();
								Thread.sleep(1000);
								
//	Step 23 
								driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250_x_c11_M250\"]")).click();

// Step 24 
								driver.findElement(By.xpath("//*[@id=\"search_x_inp\"]")).sendKeys("porsche ceramic");
								driver.findElement(By.xpath("//*[@id=\"search_x_M450_x_c94_M450_x_shorttext\"]")).click();

// Step 25 
								
								
								String brakesPrice = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450\"]/div[1]/div[2]/div"))
										.getText();
								String speedPrice = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250\"]/div[1]/div[2]/div"))
										.getText();

								Methods.checkEquipmentPrice(driver, colorPrice, wheelsPrice, seatPrice, interiorPrice, brakesPrice, speedPrice);

// Step 26 
								Methods.checkTotalPrice(driver);

	}			
}
