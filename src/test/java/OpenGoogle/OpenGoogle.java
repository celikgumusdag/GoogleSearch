package OpenGoogle;

import Run.Run;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author celik.gumusdag
 */
public class OpenGoogle extends Run {

    @Test
    public void OpenGoogle() {
        try {
            driver.get("http://www.google.com/");
            extentTest.log(LogStatus.PASS, "Sayfa Açıldı");
        } catch (Exception e) {
            Assert.fail("<br>Sayfa Açılmadı:<br><br>" + e);
        }
    }

}
