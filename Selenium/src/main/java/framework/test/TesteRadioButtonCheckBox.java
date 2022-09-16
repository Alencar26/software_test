package framework.test;

import framework.core.BaseTest;
import framework.core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static framework.core.DriverFactory.getDriver;


public class TesteRadioButtonCheckBox extends BaseTest {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
    private DSL dsl;

    @Before
    public void inicializar() {
        getDriver().get(PATH_WEB_PAGE);
        dsl = new DSL();
    }


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
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }
}
