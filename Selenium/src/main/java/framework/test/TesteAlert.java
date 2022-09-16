package framework.test;

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

    @Test
    public void deveInteragirComAlertConfirm() {

        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);

        WebElement alertElement = driver.findElement(By.id("confirm"));
        alertElement.click();

        Alert alert = driver.switchTo().alert();
        String msgAlert = alert.getText();

        Assert.assertEquals("Confirm Simples", msgAlert);
        alert.accept();

        String msgConfirmado = alert.getText();

        Assert.assertEquals("Confirmado", msgConfirmado);
        alert.accept();
    }

    @Test
    public void deveInteragirComAlertConfirmNegando() {

        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);

        WebElement alertElement = driver.findElement(By.id("confirm"));
        alertElement.click();

        Alert alert = driver.switchTo().alert();
        String msgAlert = alert.getText();

        Assert.assertEquals("Confirm Simples", msgAlert);
        alert.dismiss();

        String msgConfirmado = alert.getText();

        Assert.assertEquals("Negado", msgConfirmado);
        alert.accept();
    }

    @Test
    public void deveInteragirComAlertPrompt() {

        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);

        WebElement alertElement = driver.findElement(By.id("prompt"));
        alertElement.click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("15");
        alert.accept();

        String textAlert = alert.getText();
        Assert.assertEquals("Era 15?", textAlert);
        alert.accept();

        textAlert = alert.getText();
        Assert.assertEquals(":D", textAlert);
        alert.accept();
    }

    @Test
    public void deveInteragirComAlertPromptNegando() {

        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);

        WebElement alertElement = driver.findElement(By.id("prompt"));
        alertElement.click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        String textAlert = alert.getText();
        Assert.assertEquals("Era null?", textAlert);
        alert.dismiss();

        textAlert = alert.getText();
        Assert.assertEquals(":(", textAlert);
        alert.accept();
    }

    //exemplo de como usar javascript no selenium
    @Test
    public void testeJavascript() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("alert('Testando js via Selenium')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito com javascript via selenium'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

        WebElement element = driver.findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }
}
