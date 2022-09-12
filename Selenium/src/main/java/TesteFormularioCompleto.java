import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFormularioCompleto {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
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
    public void devePreencherFormularioESubmeterCadastro() {
        //preencher campos
        dsl.escrever("elementosForm:nome", "André");
        dsl.escrever("elementosForm:sobrenome", "Alencar");
        dsl.clicarNoCampo("elementosForm:sexo:0");
        dsl.clicarNoCampo("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        //validar campos
        Assert.assertEquals("André", dsl.obterValorCampoText("elementosForm:nome"));
        Assert.assertEquals("Alencar", dsl.obterValorCampoText("elementosForm:sobrenome"));
        Assert.assertTrue(dsl.campoEstaMarcado("elementosForm:sexo:0"));
        Assert.assertTrue(dsl.campoEstaMarcado("elementosForm:comidaFavorita:0"));
        Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.validarItensSelecionados(
                dsl.retornaItensSelecionadosComboBox("elementosForm:esportes"),
                new String[] {"Natacao", "Corrida"}));
        //efetuar cadastro
        dsl.clicarBotao("elementosForm:cadastrar");
        //validar resultado
        Assert.assertTrue(dsl.textoComecaCom("resultado","Cadastrado"));
        Assert.assertTrue(dsl.textoTerminaCom("descNome","André"));
        Assert.assertTrue(dsl.textoTerminaCom("descSobrenome","Alencar"));
        Assert.assertTrue(dsl.textoTerminaCom("descSexo","Masculino"));
        Assert.assertTrue(dsl.textoTerminaCom("descComida","Carne"));
        Assert.assertTrue(dsl.textoTerminaCom("descEscolaridade","superior"));
        Assert.assertTrue(dsl.textoTerminaCom("descEsportes","Natacao Corrida"));
    }
}
