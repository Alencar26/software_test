import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCaixasTexto {

    private final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
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
    public void testeTextField() {
        dsl.escrever("elementosForm:nome", "Escrevendo no text field.");
        Assert.assertEquals("Escrevendo no text field.", dsl.obterValorCampoText("elementosForm:nome"));
    }

    @Test
    public void interacaoComTextArea() {
        dsl.escrever("elementosForm:sugestoes", "teste");
        Assert.assertEquals("teste", dsl.obterValorCampoText("elementosForm:sugestoes"));
    }
}
