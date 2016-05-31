package Run;

import Base.Base;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
 
/**
 * @author celik.gumusdag
 */
public class Run extends Base {

    public static Logger logger=Logger.getLogger(Run.class.getName());
    public static WebDriver driver;
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static String methodName="";

    @BeforeSuite
    public static void a() throws Exception {
        logger.info("Before Suite");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();            //pencereyi oluşturur
        driver.manage().window().maximize();    //pencereyi fullscreen yapar
    }


    @AfterClass
    public static void b() throws Exception {

        Thread.sleep(3000); //3 saniye bekleme süresi
    }

    @AfterSuite
    public static void c() throws Exception {
        logger.info("After Suite");
        Thread.sleep(5000); //5 saniye bekleme süresi
        driver.close();     //chromedriveri kapatır
        driver.quit();      //chromedriveri uçurur


    }

}
