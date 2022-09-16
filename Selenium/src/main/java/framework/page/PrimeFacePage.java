package framework.page;

import org.openqa.selenium.WebDriver;

public class PrimeFacePage {

    private DSL dsl;

    public PrimeFacePage() {
        this.dsl = new DSL();
    }

    public void clicarRadioButtonPrimeFaces(String option) {
        dsl.clicaElemento("//*[@id='j_idt340:console']//label[contains(.,'"+option+"')]/..//span");
    }

    public boolean radioPrimeEstaMarcado(String option) {
        return dsl.elementoPrimeFacesEstaMarcadoXPATH("//*[@id='j_idt340:console']//label[contains(.,'"+option+"')]/..");
    }
}
