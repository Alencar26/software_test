import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escrever(String idCampo, String texto) {
        driver.findElement(By.id(idCampo)).sendKeys(texto);
    }

    public String obterValorCampoText(String idCampo) {
        return driver.findElement(By.id(idCampo)).getAttribute("value");
    }

    public void clicarNoCampo(String idCampo) {
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean campoEstaMarcado(String idCampo) {
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    public void selecionarCombo(String id, String valor) {
        
    }
}
