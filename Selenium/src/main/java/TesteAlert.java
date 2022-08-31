import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    public WebDriver instanciarNavegador(String path) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(path);
        return driver;
    }

    @Test
    public void deveInteragirComAlertSimples() {
        //instância do browser
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        //clica no botão de alert
        WebElement alertElement = driver.findElement(By.id("alert"));
        alertElement.click();
        //muda o foco para o Alert, pega texto e clica no botão "OK"
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();
        //muda o foco para o contexto padrão da página, escreve texto do
        //alert no textfield.
        driver.switchTo().defaultContent();
        WebElement textFieldNome = driver.findElement(By.id("elementosForm:nome"));
        textFieldNome.sendKeys(textAlert);
        //valida se texto do textField é o mesmo do Alert.
        Assert.assertEquals("Alert Simples", textFieldNome.getAttribute("value"));
    }
}
