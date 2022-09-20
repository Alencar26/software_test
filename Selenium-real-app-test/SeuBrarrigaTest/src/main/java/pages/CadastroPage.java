package pages;

import core.BasePage;

public class CadastroPage extends BasePage {

    public void clicarLinkNovoUsuario() {
        dsl.clicaElemento("//a[@href='/cadastro']");
    }

    public String getTextoBotaoCadastro() {
        return dsl.obterTextoBotaoByClass("btn", "value");
    }
}
