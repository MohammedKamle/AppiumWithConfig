package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppFactory {
    public static AppiumDriver<MobileElement> driver;
    public static DesiredCapabilities capabilities;


    public void launchApp(String platform, String os, String device
    , String app) throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName",platform);
        capabilities.setCapability("os_version", os);
        capabilities.setCapability("device", device);
        capabilities.setCapability("app", app);
        String username = "BS_USERNAME";
        String accessKey = "BS_ACCCESSKEY";
        String server = "hub-cloud.browserstack.com";
        if (platform.equalsIgnoreCase("android")){
            driver = new AndroidDriver<MobileElement>(
                    new URL("http://"+username+":"+accessKey+"@"+server+"/wd/hub"), capabilities
            );
            AppDriver.serDriver(driver);
        }else {
            driver = new IOSDriver(
                    new URL("http://"+username+":"+accessKey+"@"+server+"/wd/hub"), capabilities
            );
            AppDriver.serDriver(driver);
        }


    }

    public void closeApp(){
        driver.quit();
    }
}
