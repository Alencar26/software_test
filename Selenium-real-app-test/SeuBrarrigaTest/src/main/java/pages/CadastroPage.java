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

    public void setNome(String nome) {
        dsl.escrever("nome", nome);
    }

    public void setEmail(String email) {
        dsl.escrever("email", email);
    }

    public void setSenha(String senha) {
        dsl.escrever("senha", senha);
    }

    public String getAlertSucessCadastro() {
        return dsl.obterMensagemDoAlert("//div[.='Usuário inserido com sucesso']");
    }

    public String getAlertDangerEmailEmUso() {
        return dsl.obterMensagemDoAlert("//div[.='Endereço de email já utilizado']");
    }
}
