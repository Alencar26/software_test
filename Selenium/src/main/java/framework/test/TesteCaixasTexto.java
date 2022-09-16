package framework.test;

import framework.core.BaseTest;
import framework.core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static framework.core.DriverFactory.getDriver;

public class TesteCaixasTexto extends BaseTest {

    private final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
    private DSL dsl;

    @Before
    public void inicializar() {
        getDriver().get(PATH_WEB_PAGE);
        dsl = new DSL();
    }

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
