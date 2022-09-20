package core;

import resources.Usuario;

public class BasePage {

    protected DSL dsl;
    protected Usuario user;
    public BasePage() {
        this.dsl = new DSL();
        this.user = new Usuario("Garrafinha","garrafinha@email.com","g123456");
    }
}
