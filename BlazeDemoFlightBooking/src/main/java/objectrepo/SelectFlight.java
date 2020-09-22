package objectrepo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectFlight{
	public WebDriver driver;
	@FindBy(xpath="/html/body/div[2]/h3")
	WebElement title;
	@FindBy(xpath="/html/body/div[2]/table/tbody/tr[1]/td[1]/input")
	WebElement chooseFlight;
	@FindBy(xpath="/html/body/div[2]/table/thead/tr/th[4]")
	WebElement departs;
	@FindBy(xpath="/html/body/div[2]/table/thead/tr/th[5]")
	WebElement arrives;

	
	public SelectFlight(WebDriver driver)
	{
		this.driver=driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	

	public String getHeading()
	{
		return this.title.getText();
	}
	
	public String getArrives()
	{
		return this.arrives.getText();
	}
	
	public String getDeparts()
	{
		return this.departs.getText();
	}
	
	public void flightSelection()
	{
		
	    this.chooseFlight.click();
	    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	
}
