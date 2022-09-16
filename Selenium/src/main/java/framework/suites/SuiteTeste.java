package framework.suites;

import framework.core.DriverFactory;
import framework.test.*;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TesteFormularioCompleto.class,
        TestePrime.class
})
public class SuiteTeste {
    @AfterClass
    public static void finalizaTudo() {
        DriverFactory.killDriver();
    }
}
