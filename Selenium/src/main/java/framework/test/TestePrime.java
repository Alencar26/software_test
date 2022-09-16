package framework.test;

import framework.core.BaseTest;
import framework.page.PrimeFacePage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static framework.core.DriverFactory.getDriver;

public class TestePrime extends BaseTest {

    final String PATH_WEB_PAGE = "https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=ba24b";
    final String PATH_WEB_PAGE_COMBO = "https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=ba24b";
    private PrimeFacePage page;

    @Before
    public void inicializar() {
        getDriver().get(PATH_WEB_PAGE);
        page = new PrimeFacePage();
    }

    @Test
    public void deveInteragirComRadioPrime() {
        page.clicarRadioButtonPrimeFaces("Option1");
        Assert.assertTrue(page.radioPrimeEstaMarcado("Option1"));
    }
}
