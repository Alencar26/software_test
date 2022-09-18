package framework.core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    @Rule
    public TestName testName = new TestName();
    @After
    public void finalizar() throws InterruptedException, IOException {

        tirarPrint();

        if(Propriedades.FECHAR_BROWSER) {
            Thread.sleep(5000);
            DriverFactory.killDriver();
        }
    }

    private void tirarPrint() throws IOException {

        TakesScreenshot ss = (TakesScreenshot) DriverFactory.getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE); //forma de saída da screenshot.
        FileUtils.copyFile(arquivo, new File(testName+".jpg")); //precisa importar no maven o FileUtils
    }
}
