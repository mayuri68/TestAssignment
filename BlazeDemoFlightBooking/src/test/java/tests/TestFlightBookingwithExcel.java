package tests;

import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;

import objectrepo.Confirmation;
import objectrepo.HomePage;
import objectrepo.PurchaseFlight;
import objectrepo.SelectFlight;
import operations.BrowserInitialization;
import operations.ScreenShots;


public class TestFlightBookingwithExcel {
	WebDriver driver;
	SelectFlight sf;
	HomePage hp;
	PurchaseFlight pf;
	Confirmation confirm;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	int i = 0;
	
	@Parameters({"URL"})
	@BeforeTest
	 public void browserlaunch(String URL)
     {
                    driver = BrowserInitialization.StartBrowser(URL);
                    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
                    hp = new HomePage(driver);
                    sf = new SelectFlight(driver);
                    pf= new PurchaseFlight(driver);
                    confirm =new Confirmation(driver);
     }
                    
	//Select destination and arrival cities
	@Test(priority=1,dataProvider="getDestinationsData")
	public void selectDestinations(String departureCity,String arrivalCity) {
		
		try {
		hp.MakeSelections(departureCity, arrivalCity);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//Select Flight
	@Test(priority=2,dataProvider="getDestinationsData")
	public void bookFlight(String departureCity,String arrivalCity) {
		
		
		//Verify if we landed on Flight Selection page
		Assert.assertEquals(sf.getHeading(), "Flights from Paris to Rome:");
				
		//Verify if departure city is as per selection
		Assert.assertEquals(sf.getDeparts(), "Departs: "+departureCity);
				
		//Verify if arrival city is as per selection
		Assert.assertEquals(sf.getArrives(), "Arrives: "+arrivalCity);
				
		//Choose one of the flights
		try {
		sf.flightSelection();	
		}
		catch(Exception e) {
			System.out.println(e);
		}
				
		//Verify if navigation is done to flight purchase page
		Assert.assertEquals(driver.getCurrentUrl(), "https://blazedemo.com/purchase.php");

	}
	
	//Purchase Flight
	@Test(priority=3)
	public void passengerDetails() {
		
		Assert.assertEquals(pf.title(),"Please submit the form below to purchase the flight.");
		
		try{
			pf.providePassengerDetails();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://blazedemo.com/confirmation.php");
		
	}
	
	//Check if Flight is booked and confirmation id received
	@Test(priority=4)
	public void flightConfirmation() {
		//Verify if flight gets confirmed
		Assert.assertEquals(confirm.getHeading(), "Thank you for your purchase today!");
		Assert.assertNotNull(confirm.getConfirmationID());
	}
	
	@DataProvider
	public Object[][] getDestinationsData() throws Exception
	{
		
		File src=new File("src/main/java/resources/testData.xlsx");
		FileInputStream fis=new FileInputStream(src);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheetAt(0);
		
		int rowCount=sheet.getLastRowNum();
		int colCount=sheet.getRow(0).getLastCellNum();
		
		Object[][] cityData = new Object[rowCount][colCount]; 
		for(int j=1;j<=sheet.getLastRowNum();j++)
		{
			cityData[j-1][0] = sheet.getRow(j).getCell(0).getStringCellValue();
			cityData[j-1][1] = sheet.getRow(j).getCell(1).getStringCellValue(); 
		}
		return cityData;
		
	}

	
	// Taking Screen shot on test fail
    @AfterMethod
    public void screenshot(ITestResult result)
    {
               i = i+1;
               String name = "ScreenShot";
               String x = name+String.valueOf(i);
              if(ITestResult.FAILURE == result.getStatus())
                {
                               ScreenShots.captureScreenShot(driver, x);
                 }
}
	
	@AfterTest
	public void closeBrowser() 
	{
        driver.quit();
	}

}
