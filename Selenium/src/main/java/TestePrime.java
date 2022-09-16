import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrime {

    final String PATH_WEB_PAGE = "https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=ba24b";
    final String PATH_WEB_PAGE_COMBO = "https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=ba24b";
    private PrimeFacePage page;

    @Before
    public void inicializar() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(PATH_WEB_PAGE_COMBO);
        page = new PrimeFacePage(driver);
    }

//    @After
//    public void finalizar() {
//        driver.quit();
//    }

    @Test
    public void deveInteragirComRadioPrime() {
        page.clicarRadioButtonPrimeFaces("Option1");
        Assert.assertTrue(page.radioPrimeEstaMarcado("Option1"));
    }
}
