package framework.core;

import org.junit.After;

public class BaseTest {

    @After
    public void finalizar() throws InterruptedException {
        if(Propriedades.FECHAR_BROWSER) {
            Thread.sleep(5000);
            DriverFactory.killDriver();
        }
    }
}
