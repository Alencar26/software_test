package framework.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PosicaoTamanho {

    @Test
    public void teste() {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // Pegando a posição do Browser
        driver.manage().window().setPosition(new Point(100, 100));

        // Definindo tamanho da janela do browser | new Dimension(largura, altura)
        driver.manage().window().setSize(new Dimension(1200, 765));

        // Iniciar browser maximizado
        driver.manage().window().maximize();

        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());

        // close(), só fecha uma aba do browser
        //driver.close();

        // quit(), fecha o browser e mata as instâncias criadas
        driver.quit();
    }
}
