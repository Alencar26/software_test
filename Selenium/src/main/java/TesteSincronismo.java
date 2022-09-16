import framework.core.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static framework.core.DriverFactory.getDriver;

public class TesteSincronismo {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
    private CampoTreinamentoPage page;

    @Before
    public void inicializar() {
        getDriver().get(PATH_WEB_PAGE);
        page = new CampoTreinamentoPage();
    }

    @After
    public void finalizar() {
        DriverFactory.killDriver();
    }

    @Test
    public void deveInteragirComRespostaDemorada_ESPERA_FIXA() throws InterruptedException {
        page.clicarBotaoDelay();
        Thread.sleep(3000);
        page.escreverNoCampoDepoisDoDelay();
    }

    @Test
    public void deveInteragirComRespostaDemorada_ESPERA_IMPLICITA() throws InterruptedException {
        page.clicarBotaoDelay();
        page.esperarAteSegundos(10);
        page.escreverNoCampoDepoisDoDelay();
        page.esperarAteSegundos(0); //desligar a espera
    }

    @Test
    public void deveInteragirComRespostaDemorada_ESPERA_EXPLICITA() throws InterruptedException {
        page.clicarBotaoDelay();
        page.esperarElementoByIdAteSegundos("novoCampo", 30);
        page.escreverNoCampoDepoisDoDelay();
    }
}
