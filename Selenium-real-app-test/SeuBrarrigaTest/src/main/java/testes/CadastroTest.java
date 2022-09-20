package testes;

import core.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.CadastroPage;

import static core.DriverFactory.getDriver;

public class CadastroTest extends BaseTest {

    private final String PATH_WEB_PAGE = "https://seubarriga.wcaquino.me/login";
    private final String PATH_WEB_PAGE_CADASTRO = "https://seubarriga.wcaquino.me/cadastro";
    private CadastroPage page;

    @Before
    public void init() {
        getDriver().get(PATH_WEB_PAGE_CADASTRO);
        page = new CadastroPage();
    }

    @Test
    public void deveClicarNaOpcaoDeCadastro() {
        if(!getDriver().getCurrentUrl().equals(PATH_WEB_PAGE)) {
            getDriver().get(PATH_WEB_PAGE_CADASTRO);
        }
        page.clicarLinkNovoUsuario();
        Assert.assertEquals("Cadastrar", page.getTextoBotaoCadastro());
    }

    @Test
    public void deveValidarCamposObrigatorios() {
        page.clicarBotaoCadastro();
        Assert.assertEquals("Nome é um campo obrigatório", page.getAlertDangerNome());
        Assert.assertEquals("Email é um campo obrigatório", page.getAlertDangerEmail());
        Assert.assertEquals("Senha é um campo obrigatório", page.getAlertDangerSenha());
    }

    @Test
    public void deveEfetuarCadastro() {
        page.setNome();
        page.setEmail();
        page.setSenha();
        page.clicarBotaoCadastro();
        Assert.assertEquals("Usuário inserido com sucesso", page.StringgetAlertSucessCadastro());
    }
}
