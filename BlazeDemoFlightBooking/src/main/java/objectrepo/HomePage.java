package objectrepo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage{
	public WebDriver driver;
	public WebDriverWait wd;
	@FindBy(name="fromPort")
	WebElement departureCity;
	@FindBy(name="toPort")
	WebElement destinationCity;
	@FindBy(xpath="/html/body/div[3]/form/div/input")
	WebElement find;

	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		wd=new WebDriverWait(driver,30);
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	

	public void MakeSelections(String departCity,String arrivalCity)
	{
		//wd.until(ExpectedConditions.visibilityOf(this.departureCity));
		Select departureCityList=new Select(this.departureCity);
			departureCityList.selectByValue(departCity);
		
		//wd.until(ExpectedConditions.visibilityOf(this.destinationCity));
		Select destinationCityList=new Select(this.destinationCity);
			destinationCityList.selectByValue(arrivalCity);
			
		//wd.until(ExpectedConditions.visibilityOf(this.find));
		this.find.click();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	
}
