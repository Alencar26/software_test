import framework.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static framework.core.DriverFactory.getDriver;

public class TesteFormularioCompleto {

    private final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";
    private CampoTreinamentoPage page;

    @Before
    public void inicializar() {
        getDriver().get(PATH_WEB_PAGE);
        page = new CampoTreinamentoPage();
    }

    @After
    public void finalizar() throws InterruptedException {
        Thread.sleep(5000);
        DriverFactory.killDriver();
    }

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
