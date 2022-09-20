package pages;

import core.BasePage;

public class CadastroPage extends BasePage {

    public void clicarLinkNovoUsuario() {
        dsl.clicaElemento("//a[@href='/cadastro']");
    }

    public String getTextoBotaoCadastro() {
        return dsl.obterTextoBotaoByClass("btn", "value");
    }

    public void clicarBotaoCadastro() {
        dsl.clicarBotaoByClass("btn");
    }

    public String getAlertDangerNome() {
        return dsl.obterMensagemDoAlert("//div[.='Nome é um campo obrigatório']");
    }

    public String getAlertDangerEmail() {
        return dsl.obterMensagemDoAlert("//div[.='Email é um campo obrigatório']");
    }

    public String getAlertDangerSenha() {
        return dsl.obterMensagemDoAlert("//div[.='Senha é um campo obrigatório']");
    }

    public void setNome() {
        dsl.escrever("nome", user.getNome());
    }

    public void setEmail() {
        dsl.escrever("email", user.getEmail());
    }

    public void setSenha() {
        dsl.escrever("senha", user.getSenha());
    }

    public String StringgetAlertSucessCadastro() {
        return dsl.obterMensagemDoAlert("//div[.='Usuário inserido com sucesso']");
    }
}
