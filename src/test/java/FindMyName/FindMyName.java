package FindMyName;

import Run.Run;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by celik.gumusdag on 27.05.2016.
 */
public class FindMyName extends Run {

    protected static String Search = System.getProperty("Search");

    @Test
    public void FindMyName() {

        if (Search == null) {
            Search = "Çelik Gümüşdağ";
        }

        try {
            WebElement searchbox = driver.findElement(By.cssSelector("#sb_ifc0>#gs_lc0>input:nth-of-type(1)"));
            searchbox.sendKeys(Search);
            searchbox.submit();
            extentTest.log(LogStatus.PASS, "İsim Arama Başarılı");
            logger.info(Search);
        } catch (Exception e) {
            Assert.fail("<br>İsim Arama Başarısız:<br><br>" + e);
        }


    }
}
