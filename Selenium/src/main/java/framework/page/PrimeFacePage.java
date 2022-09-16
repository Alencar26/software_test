package framework.page;

import framework.core.BasePage;

public class PrimeFacePage extends BasePage {

    public void clicarRadioButtonPrimeFaces(String option) {
        dsl.clicaElemento("//*[@id='j_idt340:console']//label[contains(.,'"+option+"')]/..//span");
    }

    public boolean radioPrimeEstaMarcado(String option) {
        return dsl.elementoPrimeFacesEstaMarcadoXPATH("//*[@id='j_idt340:console']//label[contains(.,'"+option+"')]/..");
    }
}
