# Selenium - testes funcionais

## Como executar testes em paralelo:

- JUnit não tem suporte nativo.
  - Precisamos instalar um plugin chamado Surefire
  - Segue links do maven:
    - [Informações do Surfire](https://maven.apache.org/surefire/maven-surefire-plugin/)
    - [Surfire no Maven](https://maven.apache.org/surefire/maven-surefire-plugin/examples/fork-options-and-parallel-execution.html)

  Exemplo do import com maven
  ```xml
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surfire-plugin</artifactId>
      <version>2.18.1</version>
      <configuration>
        <includes>
          <include>com/pacote/caminho/das/suites/*.java</include>
        </includes>
        <paralel>all</paralel>
        <threadCount>3</threadCount>
      </configuration>
    </plugin>
  </plugins>
  ```

  Para executar os testes por fora da IDE é necessário baixar o Maven no sistema operacional e adicionar o /bin nas variáveis do sistema.

Para compilar o projeto basta executar:
```bash
mvn compile
```

Para compilar classes de teste:
```bash
mvn test-compile
```

Para executar os testes:
```bash
mvn test
```
---
## Exemplo de refatoração da classe DriverFactory para utilizar threads
```java
public class DriverFactory {
 
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
        @Override
        protected synchronized WebDriver initialValue() {
            return initDriver();
          }
      }

    private DriverFactory() {}

    public static WebDriver getDriver() {
        return threadDriver.get();
      }

    public static WebDriver initDriver() {
        WebDrvier driver = null;
            switch (Propriedades.browser){
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    //aqui colocar o caminho do driver do selenium para Chrome.
                    driver = new ChromeDriver(); break;
            }
            driver.manage().window().setSize(new Dimension(1200,765));
        return driver;
    }

    public static void killDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (threadDriver != null) {
            threadDriver.remove();
          }
    }
}
```
