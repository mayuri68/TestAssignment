package objectrepo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchaseFlight{
	public WebDriver driver;
	public WebDriverWait wd;
	
	@FindBy(xpath="/html/body/div[2]/p[6]")
	WebElement heading;
	@FindBy(name="inputName")
	WebElement name;
	@FindBy(name="address")
	WebElement address;
	@FindBy(name="city")
	WebElement city;
	@FindBy(name="state")
	WebElement state;
	@FindBy(name="zipCode")
	WebElement zipCode;
	@FindBy(name="cardType")
	WebElement cardType;
	@FindBy(name="creditCardNumber")
	WebElement creditCardNumber;
	@FindBy(name="creditCardMonth")
	WebElement creditCardMonth;
	@FindBy(name="creditCardYear")
	WebElement creditCardYear;
	@FindBy(name="nameOnCard")
	WebElement nameOnCard;
	@FindBy(xpath="/html/body/div[2]/form/div[11]/div/input")
	WebElement find;

	
	public PurchaseFlight(WebDriver driver)
	{
		this.driver=driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	

	public String title()
	{
		return this.heading.getText();
	}
	
	public void providePassengerDetails()
	{
		this.name.sendKeys("test");
		this.address.sendKeys("testAddress");
		this.city.sendKeys("Bangalore");
		this.state.sendKeys("Karnataka");
		this.zipCode.sendKeys("560060");
		Select cardTypeList = new Select(this.cardType);
		cardTypeList.selectByValue("visa");
		this.creditCardNumber.sendKeys("1122112211221122");
		this.creditCardMonth.sendKeys("11");
		this.creditCardYear.sendKeys("2021");
		this.nameOnCard.sendKeys("test");
		this.find.click();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	
}
