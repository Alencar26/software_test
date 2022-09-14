import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFormularioCompleto {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
    private CampoTreinamentoPage page;

    @Before
    public void inicializar() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(PATH_WEB_PAGE);
        page = new CampoTreinamentoPage(driver);
    }

//    @After
//    public void finalizar() {
//        driver.quit();
//    }

    @Test
    public void devePreencherFormularioESubmeterCadastro() {
        //preencher campos
        page.setNome("André");
        page.setSobrenome("Alencar");
        page.setSexoMasculino();
        page.setComidaFavoritaCarne();
        page.setEscolaridade("Superior");
        page.setEsporte("Natacao", "Corrida");
        //validar campos
        Assert.assertEquals("André", page.getNome());
        Assert.assertEquals("Alencar", page.getSobrenome());
        Assert.assertTrue(page.sexoIsMasculino());
        Assert.assertTrue(page.comidaFavoritaIsCarne());
        Assert.assertEquals("Superior", page.getEscolaridade());
        Assert.assertTrue(page.esporteFavoritoIsNatacaoECorrida());
        //efetuar cadastro
        page.cadastrar();
        //validar resultado
        Assert.assertTrue(page.obterResultadoCadastro("Cadastrado"));
        Assert.assertTrue(page.obterNomeCadastro("André"));
        Assert.assertTrue(page.obterSobrenomeCadastro("Alencar"));
        Assert.assertTrue(page.obterSexoCadastro("Masculino"));
        Assert.assertTrue(page.obterComidaCadastro("Carne"));
        Assert.assertTrue(page.obterEscolaridadeCadastro("superior"));
        Assert.assertTrue(page.obterEsporteCadastro("Natacao Corrida"));
    }

    @Test
    public void deveClicarBotaoTabela() {
        page.clicarBotaoTabelaRelativoAoUsuario("Maria");
    }
}
