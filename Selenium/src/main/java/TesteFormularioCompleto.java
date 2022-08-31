import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteFormularioCompleto {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    public WebDriver instanciarNavegador(String path) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(path);
        return driver;
    }

    @Test
    public void devePreencherFormularioESubmeterCadastro() {

        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);

        //capturar elementos
        WebElement inputNome = driver.findElement(By.id("elementosForm:nome"));
        WebElement inputSobrenome = driver.findElement(By.id("elementosForm:sobrenome"));
        WebElement inputSexo = driver.findElement(By.id("elementosForm:sexo:0"));
        WebElement inputComidaFavorita = driver.findElement(By.id("elementosForm:comidaFavorita:0"));
        Select comboEscolaridade = new Select(driver.findElement(By.id("elementosForm:escolaridade")));
        Select comboEsportes = new Select(driver.findElement(By.id("elementosForm:esportes")));
        WebElement buttonCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));

        //preencher fomulario
        inputNome.sendKeys("André");
        Assert.assertEquals("André", inputNome.getAttribute("value"));

        inputSobrenome.sendKeys("Alencar");
        Assert.assertEquals("Alencar", inputSobrenome.getAttribute("value"));

        inputSexo.click();
        Assert.assertTrue(inputSexo.isSelected());

        inputComidaFavorita.click();
        Assert.assertTrue(inputComidaFavorita.isSelected());

        comboEscolaridade.selectByVisibleText("Superior");
        String escolaridadeSelecionado = comboEscolaridade.getFirstSelectedOption().getText();
        Assert.assertEquals("Superior", escolaridadeSelecionado);

        comboEsportes.selectByVisibleText("Natacao");
        comboEsportes.selectByVisibleText("Corrida");
        boolean natacao = false;
        boolean corrida = false;
        List<WebElement> esportesSelecionados = comboEsportes.getAllSelectedOptions();
        for (WebElement esporte : esportesSelecionados) {
            if (esporte.getText().equals("Natacao")) natacao = true;
            if (esporte.getText().equals("Corrida")) corrida = true;
        }
        Assert.assertTrue(natacao && corrida);

        buttonCadastrar.click();
        //validar resultado
        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("André"));
        Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Alencar"));
        Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
        Assert.assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Carne"));
        Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
        Assert.assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("Natacao Corrida"));
    }
}
