package testes;

import core.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.CadastroPage;
import pages.LoginPage;

import static core.DriverFactory.getDriver;

public class CadastroTest extends BaseTest {

    private final String PATH_WEB_PAGE = "https://seubarriga.wcaquino.me/login";
    private CadastroPage page;

    @Before
    public void init() {
        getDriver().get(PATH_WEB_PAGE);
        page = new CadastroPage();
    }

    @Test
    public void deveClicarNaOpcaoDeCadastro() {
        page.clicarLinkNovoUsuario();
        Assert.assertEquals("Cadastrar", page.getTextoBotaoCadastro());
    }
}
