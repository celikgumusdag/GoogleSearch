package Listener;

import Run.Run;
import Base.Base;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import javax.imageio.ImageIO;


public class Listener extends Run implements ITestListener {


    String timeStamp = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(Calendar.getInstance().getTime());

    @Override
    public void onFinish(ITestContext arg0) {
        extentReports.flush();
    }

    @Override
    public void onStart(ITestContext arg0) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

    }

    @Override
    public void onTestFailure(ITestResult arg0) {

        if (methodName.equals("")) {
            methodName = arg0.getName().toString();
        }
        extentTest.log(LogStatus.FAIL, "Test tamamlanamadı. " + arg0.getThrowable().getMessage());

        logger.info(arg0.getMethod().getMethodName() + " başarısız!\n");
        String imageLocation = "";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        BufferedImage screenshotImage = null;
        try {
            screenshotImage = ImageIO.read(scrFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("C:\\tmp\\reports\\" + timeStamp + "\\screenshots");
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("tmp klasoru altinda runid ve screenshots klasoru olusturuldu.");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            methodName = methodName.trim();
            imageLocation = "C:\\tmp\\reports\\" + timeStamp + "\\screenshots\\" + methodName + timeStamp + ".png";
            FileUtils.copyFile(scrFile, new File(imageLocation));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        methodName = "";
        BufferedImage resizedImage=resizeImage(screenshotImage);
        String screenshotBase64=imageToString(resizedImage);
        extentTest.log(LogStatus.WARNING, extentTest.addBase64ScreenShot(screenshotBase64));

        extentReports.endTest(extentTest);

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        logger.info(arg0.getTestClass().getName() + " - " + arg0.getMethod().getMethodName() + " başladı.");
        if (extentReports == null) {
            extentReports = new ExtentReports("C:\\tmp\\reports\\" + timeStamp + "\\SeleniumTestResults.html", true);
            extentReports.addSystemInfo("Selenium", "Selenium Test Otomasyon");


        }
        extentTest = extentReports.startTest(arg0.getName());
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        extentTest.log(LogStatus.PASS, "Test başarıyla tamamlandı.");
        if (methodName.equals("")) {
            methodName = arg0.getName().toString();
        }
        logger.info(arg0.getMethod().getMethodName() + " başarılı.\n");
        methodName = "";
        extentReports.endTest(extentTest);
    }

}
