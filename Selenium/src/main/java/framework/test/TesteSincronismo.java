package framework.test;

import framework.core.DriverFactory;
import framework.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
