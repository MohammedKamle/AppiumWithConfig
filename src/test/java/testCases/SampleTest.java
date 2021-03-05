package testCases;

import base.AppFactory;
import base.DeviceConfigurator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import java.io.File;
import java.io.IOException;

public class SampleTest extends AppFactory {
    private AppFactory appFactory;
    private HomePage homePage;


    @BeforeMethod
    public void setUp() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("config.yml").getFile());
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        DeviceConfigurator deviceConfigurator;
        deviceConfigurator =  om.readValue(file,DeviceConfigurator.class);
        appFactory = new AppFactory();
        if(deviceConfigurator.getPlatformName().equalsIgnoreCase("android")){
            System.out.println("Test launching on android");
        }else {
            System.out.println("Test launching on iOS");
        }
        appFactory.launchApp(deviceConfigurator.getPlatformName(),deviceConfigurator.getOs()
                , deviceConfigurator.getDevice(), deviceConfigurator.getApp());
        homePage = new HomePage();
    }

    @Test
    public void Test_EchoTest() throws InterruptedException {
        String input = "demoTest";
        Thread.sleep(3000);
        homePage.navigateToEchoBox();
        homePage.enterSomething(input);
        homePage.saveText();
        homePage.verifySavedText(input);
    }

    @AfterMethod
    public void tearDown(){
        appFactory.closeApp();
    }
}
