package ClickFirst;

import Run.Run;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by celik.gumusdag on 27.05.2016.
 */
public class ClickFirst extends Run {

    @Test
    public void ClickFirst() {
        try {
            driver.findElement(By.cssSelector("#rso>.srg:nth-of-type(1)>.g:nth-of-type(1)>.rc>.r>a")).click(); //İlk Linke Tıklama İşlemi
            extentTest.log(LogStatus.PASS, "Tıklama Başarılı");
        } catch (Exception e) {
            Assert.fail("<br>Tıklama Başarısız:<br><br>" + e);
        }

    }
}
