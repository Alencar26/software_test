package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogle {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","/home/al3ncar/www/drivers-selenium/new/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");
        System.out.println(driver.getTitle());
    }
}
