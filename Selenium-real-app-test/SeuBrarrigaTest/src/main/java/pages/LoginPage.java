package pages;

import core.BasePage;

public class LoginPage extends BasePage {

    public void setEmail(String email) {
        dsl.escrever("email",email);
    }

    public void setSenha(String senha) {
        dsl.escrever("senha",senha);
    }

    public void clicarBotaoEntrar() {
        dsl.clicarBotaoByClass("btn");
    }

    public String getAlertDanger() {
        return dsl.obterMensagemDoAlert("//div[@role='alert']");
    }

    public String getAlertSucessLogin() {
        return dsl.obterMensagemDoAlert("//div[.='Bem vindo, Garrafinha!']");
    }
}
