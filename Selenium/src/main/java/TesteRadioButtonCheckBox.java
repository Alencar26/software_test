import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class TesteRadioButtonCheckBox {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    public WebDriver instanciarNavegador(String path) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(path);
        return driver;
    }

    @Test
    public void deveInteragirComRadioButton() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        driver.findElement(By.id("elementosForm:sexo:0")).click();

        boolean isSelected = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
        Assert.assertTrue(isSelected);

        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        boolean isSelected = driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected();
        Assert.assertTrue(isSelected);

        driver.quit();
    }

    @Test
    public void deveInteragirComComboBox() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        //combo.selectByIndex(2);
        //combo.selectByValue("2grauincomp");
        combo.selectByVisibleText("2o grau completo");

        String valorCombo = combo.getFirstSelectedOption().getText();
        Assert.assertEquals("2o grau completo", valorCombo);

        driver.quit();
    }

    @Test
    public void deveValidarItensComComboBox() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

        Select combo = new Select(element);
        List<WebElement> listComboBox = combo.getOptions();

        Assert.assertEquals(8, listComboBox.size());

        boolean encontrou = false;
        for(WebElement elemento : listComboBox) {
            if (elemento.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);
    }

    @Test
    public void deveValidarTresElementosCorrespondentesNoComboMultiplo() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));

        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        boolean elementoUmCorresponde = false;
        boolean elementoDoisCorresponde = false;
        boolean elementoTresCorresponde = false;

        List<WebElement> selecionados = combo.getAllSelectedOptions();
        for(WebElement elemento : selecionados) {
            if(elemento.getText().equals("Natacao")) {
                elementoUmCorresponde = true;
            } else if(elemento.getText().equals("Corrida")) {
                elementoDoisCorresponde = true;
            } else if(elemento.getText().equals("O que eh esporte?")) {
                elementoTresCorresponde = true;
            }
        }
        Assert.assertTrue(elementoUmCorresponde
                            && elementoDoisCorresponde
                            && elementoTresCorresponde);
    }

    @Test
    public void deveInteragirComBotoes() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        WebElement botao = driver.findElement(By.id("buttonSimple"));

        botao.click();
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveInteragirComLinks() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        WebElement link = driver.findElement(By.linkText("Voltar"));

        link.click();
        boolean voltou = driver.findElement(By.id("resultado")).getText().equals("Voltou!");
        Assert.assertTrue(voltou);
    }
}
