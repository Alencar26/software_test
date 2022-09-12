import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escrever(By by, String texto) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(texto);
    }

    public void escrever(String idCampo, String texto) {
        escrever(By.id(idCampo), texto);
    }

    public String obterValorCampoText(String idCampo) {
        return driver.findElement(By.id(idCampo)).getAttribute("value");
    }

    public boolean textoComecaCom(String id, String texto) {
        return driver.findElement(By.id(id)).getText().startsWith(texto);
    }

    public boolean textoTerminaCom(String id, String texto) {
        return driver.findElement(By.id(id)).getText().endsWith(texto);
    }

    public void clicarNoCampo(String idCampo) {
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean campoEstaMarcado(String idCampo) {
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    public void selecionarCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<WebElement> retornaTodosOsItensComboBox(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getOptions();
    }

    public boolean procurarItemNaLista(List<WebElement> lista, String valor) {
        boolean encontrou = false;
        for(WebElement elemento : lista) {
            if (elemento.getText().equals(valor)) {
                encontrou = true;
                break;
            }
        }
        return encontrou;
    }

    public List<WebElement> retornaItensSelecionadosComboBox(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getAllSelectedOptions();
    }

    public boolean validarItensSelecionados(List<WebElement> lista, String[] itens) {
        int n = 0;
        boolean allTrue = false;
        boolean[] validados = new boolean[itens.length];

        for (int i = 0; i < itens.length; i++) {
            validados[i] = false;
        }

        for(WebElement item : lista) {
            if (item.getText().equals(itens[n])) {
                validados[n] = true;
            }
            n++;
        }

        for(boolean b : validados) {
            if(!b){
                allTrue = false;
                break;
            }
            allTrue = true;
        }

        return allTrue;
    }

    public void clicarBotao(String id) {
        driver.findElement(By.id(id)).click();
    }

    public String obterTextoBotao(String id, String atributo) {
        return driver.findElement(By.id(id)).getAttribute(atributo);
    }

    public void clicarLink(String id) {
        driver.findElement(By.linkText(id)).click();
    }

    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }
    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    //****************** JS ******************

    public Object executarJS(String comando, Object... parametros) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(comando, parametros);
    }

    public Object executarJSAsync(String comando, Object... parametros) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeAsyncScript(comando, parametros);
    }
}
