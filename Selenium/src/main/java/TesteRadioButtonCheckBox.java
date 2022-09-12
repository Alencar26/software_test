import org.junit.Assert;
import org.junit.Before;
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
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializar() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(PATH_WEB_PAGE);
        dsl = new DSL(driver);
    }

    //    @After
//    public void finalizar() {
//        driver.quit();
//    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarNoCampo("elementosForm:sexo:0");
        Assert.assertTrue(dsl.campoEstaMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckBox() {
        dsl.clicarNoCampo("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.campoEstaMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComComboBox() {
        dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo",
                dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveValidarItensComComboBox() {
        List<WebElement> listComboBox = dsl.retornaTodosOsItensComboBox("elementosForm:escolaridade");
        Assert.assertEquals(8, listComboBox.size());
        Assert.assertTrue(dsl.procurarItemNaLista(listComboBox,"Mestrado"));
    }

    @Test
    public void deveValidarTresElementosCorrespondentesNoComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes","Natacao");
        dsl.selecionarCombo("elementosForm:esportes","Corrida");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");

        String[] valores = {"Natacao", "Corrida", "O que eh esporte?"};
        List<WebElement> selecionados = dsl.retornaItensSelecionadosComboBox("elementosForm:esportes");
        Assert.assertTrue(dsl.validarItensSelecionados(selecionados, valores));
    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterTextoBotao("buttonSimple", "value"));
    }

    @Test
    public void deveInteragirComLinks() {
        WebElement link = driver.findElement(By.linkText("Voltar"));

        link.click();
        boolean voltou = driver.findElement(By.id("resultado")).getText().equals("Voltou!");
        Assert.assertTrue(voltou);
    }
}
