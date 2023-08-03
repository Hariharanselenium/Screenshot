package Screenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class context {
WebDriver driver;
    
	public context(WebDriver driver){
		this.driver=driver;	
	}
	public void Validati() {
		driver.get("https://thinktime.in/");
		driver.findElement(By.linkText("Services")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		WebElement sss=driver.findElement(By.xpath("//h2[contains(text(),'What We Offer')]"));
		System.out.println(sss.getText());
		Assert.assertEquals(sss.getText()," We Offer");
		System.out.println(sss.getText());
		
	}
}