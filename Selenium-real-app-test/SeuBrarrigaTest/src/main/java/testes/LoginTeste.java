package testes;

import core.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;

import static core.DriverFactory.getDriver;

public class LoginTeste extends BaseTest {

    private final String PATH_WEB_PAGE = "https://seubarriga.wcaquino.me/login";
    private LoginPage page;

    @Before
    public void init() {
        getDriver().get(PATH_WEB_PAGE);
        page = new LoginPage();
    }

    @Test
    public void deveTentarEfetuarLoginSemCadastro() {
        page.setEmail("email@qualqer.com");
        page.setSenha("123456");
        page.clicarBotaoEntrar();
        Assert.assertEquals("Problemas com o login do usuário", page.getAlertDanger());
    }

    @Test
    public void deveEfetuarLogin() {
        page.setEmail(user.getEmail());
        page.setSenha(user.getSenha());
        page.clicarBotaoEntrar();
        Assert.assertEquals("Bem vindo, "+user.getNome()+"!", page.getAlertSucessLogin());
    }
}
