package framework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static framework.core.DriverFactory.getDriver;

public class DSL {

    public void escrever(By by, String texto) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(texto);
    }

    public void escrever(String idCampo, String texto) {
        escrever(By.id(idCampo), texto);
    }

    public String obterValorCampoText(String idCampo) {
        return getDriver().findElement(By.id(idCampo)).getAttribute("value");
    }

    public boolean textoComecaCom(String id, String texto) {
        return getDriver().findElement(By.id(id)).getText().startsWith(texto);
    }

    public boolean textoTerminaCom(String id, String texto) {
        return getDriver().findElement(By.id(id)).getText().endsWith(texto);
    }

    public void clicarNoCampo(String idCampo) {
        getDriver().findElement(By.id(idCampo)).click();
    }

    public boolean campoEstaMarcado(String idCampo) {
        return getDriver().findElement(By.id(idCampo)).isSelected();
    }

    public boolean elementoPrimeFacesEstaMarcadoXPATH(String xpath) {
        return getDriver().findElement(By.xpath(xpath)).getAttribute("aria-checked").equals("true");
    }

    public void selecionarCombo(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<WebElement> retornaTodosOsItensComboBox(String id) {
        WebElement element = getDriver().findElement(By.id(id));
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
        WebElement element = getDriver().findElement(By.id(id));
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
        getDriver().findElement(By.id(id)).click();
    }

    public String obterTextoBotao(String id, String atributo) {
        return getDriver().findElement(By.id(id)).getAttribute(atributo);
    }

    public void clicarLink(String id) {
        getDriver().findElement(By.linkText(id)).click();
    }

    public String obterTexto(By by) {
        return getDriver().findElement(by).getText();
    }
    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    //****************** JS ******************

    public Object executarJS(String comando, Object... parametros) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(comando, parametros);
    }

    public Object executarJSAsync(String comando, Object... parametros) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeAsyncScript(comando, parametros);
    }

    //************** TABELA com XPATH ***************

    public void clicarBotaoTabela(String id, String coluna, String valor, String colunaBotao) {
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='"+id+"']"));

        int indiceColuna = obterIndiceColunaOuLinha(tabela,".//th", coluna);
        int indiceLinha = obterIndiceColunaOuLinha(tabela, "./tbody/tr/td["+indiceColuna+"]", valor);
        int indiceColunaBotao = obterIndiceColunaOuLinha(tabela,".//th", colunaBotao);

        WebElement celulaTabela = tabela.findElement(By.xpath(".//tr["+indiceLinha+"]/td["+indiceColunaBotao+"]"));
        celulaTabela.findElement(By.xpath(".//input")).click();

    }

    private int obterIndiceColunaOuLinha(WebElement tabela, String xpath, String valor) {
        List<WebElement> listaElementos = tabela.findElements(By.xpath(xpath));
        int indice = -1; //-1 segnifica que não encontrou
        for (int i = 0; i < listaElementos.size(); i++) {
            if (listaElementos.get(i).getText().equals(valor)) {
                indice = i+1;
                break;
            }
        }
        return  indice;
    }

    //************** XPATH **************

    public void clicaElemento(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void esperarAte(int tempo, TimeUnit unidadeMedida) {
        getDriver().manage().timeouts().implicitlyWait(tempo, unidadeMedida);
    }

    public void esperarElementoById(String idElemento ,int segundos) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(segundos));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idElemento)));
    }
}
