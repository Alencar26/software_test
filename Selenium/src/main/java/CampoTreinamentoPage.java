import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        this.dsl = new DSL(driver);
    }

    public void setNome(String nome) {
        dsl.escrever("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome) {
        dsl.escrever("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino() {
        dsl.clicarNoCampo("elementosForm:sexo:0");
    }

    public void setComidaFavoritaCarne() {
        dsl.clicarNoCampo("elementosForm:comidaFavorita:0");
    }

    public void setEscolaridade(String escolaridade) {
        dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
    }

    public void setEsporte(String... esportes) {
        for (String esporte : esportes) {
            dsl.selecionarCombo("elementosForm:esportes", esporte);
        }
    }

    public void cadastrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public boolean obterResultadoCadastro(String valor) {
        return dsl.textoComecaCom("resultado",valor);
    }

    public boolean obterNomeCadastro(String nome) {
        return dsl.textoTerminaCom("descNome",nome);
    }

    public boolean obterSobrenomeCadastro(String sobrenome) {
        return dsl.textoTerminaCom("descSobrenome",sobrenome);
    }

    public boolean obterSexoCadastro(String sexo) {
        return dsl.textoTerminaCom("descSexo",sexo);
    }

    public boolean obterComidaCadastro(String comida) {
        return dsl.textoTerminaCom("descComida",comida);
    }

    public boolean obterEscolaridadeCadastro(String escolaridade) {
        return dsl.textoTerminaCom("descEscolaridade",escolaridade);
    }

    public boolean obterEsporteCadastro(String esporte) {
        return dsl.textoTerminaCom("descEsportes",esporte);
    }

    public String getNome() {
        return dsl.obterValorCampoText("elementosForm:nome");
    }

    public String getSobrenome() {
        return dsl.obterValorCampoText("elementosForm:sobrenome");
    }

    public boolean sexoIsMasculino() {
        return dsl.campoEstaMarcado("elementosForm:sexo:0");
    }

    public boolean comidaFavoritaIsCarne() {
        return dsl.campoEstaMarcado("elementosForm:comidaFavorita:0");
    }

    public String getEscolaridade() {
        return dsl.obterValorCombo("elementosForm:escolaridade");
    }

    public boolean esporteFavoritoIsNatacaoECorrida() {
        return dsl.validarItensSelecionados(
                dsl.retornaItensSelecionadosComboBox("elementosForm:esportes"),
                new String[] {"Natacao", "Corrida"});
    }
}
