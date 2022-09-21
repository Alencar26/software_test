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

- **Exemplo de refatoração da classe DriverFactory para utilizar threads**
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
---
## Selenium GRID
Executar o teste de forma remota em várias máquinas diferentes e controla-las com uma máquina host.

[Link do Selenium GRID](https://www.selenium.dev/documentation/grid/getting_started/)

#### Como montar o "servidor host" com GRID:
1. Primeiro baixe o selenium GRID;
2. Acesse o diretório onde está o Selenium GRID (será um arquivo jar);
3. Dentro do diretório:
  a. Execute:
  ```java
    java -jar nomeDoArquivoSeleniumGRID.jar -role hub
  ```
  b. O código Acima vai executar um servido host com a porta 4444;
  c. Ao acessar localhost:4444 será possível ver o GRID rodando e as máquinas que estão conectadas;
#### Configurando as máquinas que irão executar os teste
1. Primeiro baixe o selenium GRID;
2. Acesse o diretório onde está o Selenium GRID (será um arquivo jar);
3. Dentro do diretório:
  a. Execute:
  ```java
    java -jar nomeDoArquivoSeleniumGRID.jar -role node
  ```
  b.Agora a máquina está conectada no GRID na porta 5555;
  c. Para conectar outra máquina teremos que escolher outra porta:
  ```java
    java -jar nomeDoArquivoSeleniumGRID.jar -role node -port 5556
  ```
4. Pode conectar quantas máquinas quiser.

- **Exemplo de refatoração no código nas classes DriverFactory e Propriedades**
*Classe Propriedades*
```java
public class Propriedades {

    public static boolean FECHAR_BROWSER = false;
    public static Browsers browser = Browsers.FIREFOX;
    public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.GRID;

    public enum Browsers {
        CHROME,
        FIREFOX
    }

    public enum TipoExecucao {
        LOCAL,
        GRID
      }
}
```
*Classe DriverFactory*
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
        if (Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
          switch (Propriedades.browser){
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", "C:\\Users\\andre\\www\\drivers\\Selenium\\geckodriver\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    //aqui colocar o caminho do driver do selenium para Chrome.
                    driver = new ChromeDriver(); break;
            }

          }
        if (Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
            DesiredCapabilities cap = null;
            switch (Propriedades.browser) {
                case FIREFOX: cap = DesiredCapabilities.firefox(); break;
                case CHROME: cap = DesiredCapabilities.chrome(); break;
              }
            try {
                  // na url abixo informe o mesmo endereço que é mostrado no terminal após rodar o grid host.
                  driver = new RemotedWebDriver(new URL("http://192.168.0.161:4444/wd/hub"), cap);
              } catch(MalformedURLException e) {
                 System.out.println("Falha na conexão com GRID");
                 e.printStackTrace();
              }
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

**PARAEXECUÇÃO DOS TESTES VIA GRID, BASTA:**
1. Navegar até o diretório do projeto via terminal;
2. Executar:
  ```bash
  mvn test
  ```
3. Agora os teste serão executados nas máquinas que estão conectadas ao hub.
