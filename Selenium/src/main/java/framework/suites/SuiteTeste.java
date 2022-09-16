package framework.suites;

import framework.test.TesteAlert;
import framework.test.TesteCaixasTexto;
import framework.test.TesteFormularioCompleto;
import framework.test.TesteRadioButtonCheckBox;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteFormularioCompleto.class,
        TesteAlert.class,
        TesteCaixasTexto.class,
        TesteRadioButtonCheckBox.class
})
public class SuiteTeste {
}
