package dietaBovinosLeitePL;

public class Animal {

	// atributos modificáveis pelo usuário
	private double pesoCorporal;
	private int diasLactacao;
	private int numeroLactacao;
	private int diasPrenhez;
	
	private double pesoMaturidade;
	private double ganhoPeso;
	
	// atributos não-modificáveis pelo usuário
	
	private double teorGorduraLeite = 3.5;
	private double teorProteinaLeite = 2.8;
	private double exNDT = 70;
	private double exNDF = 28;
	
	// atributos que serão calculados
	private double exMS;
	private double exEnergia;
	private double exProteina;
	private double exCalcio;
	private double exFosforo;
	
	public void cadastraAnimal(double pesoCorporal, int diasLactacao, int numeroLactacao, int diasPrenhez) {
		
		this.setPesoCorporal(pesoCorporal);
		this.setDiasLactacao(diasLactacao);
		this.setNumeroLactacao(numeroLactacao);
		this.setDiasPrenhez(diasPrenhez);
		this.setGanhoPeso(0.003 * this.pesoCorporal);
		this.setPesoMaturidade(1.12 * this.pesoCorporal);
		
	}
	
	// getters e setters
	public double getPesoCorporal() {
		return pesoCorporal;
	}
	public void setPesoCorporal(double pesoCorporal) {
		this.pesoCorporal = pesoCorporal;
	}
	public int getDiasLactacao() {
		return diasLactacao;
	}
	public void setDiasLactacao(int diasLactacao) {
		this.diasLactacao = diasLactacao;
	}
	public int getNumeroLactacao() {
		return numeroLactacao;
	}
	public void setNumeroLactacao(int numeroLactacao) {
		this.numeroLactacao = numeroLactacao;
	}
	public int getDiasPrenhez() {
		return diasPrenhez;
	}
	public void setDiasPrenhez(int diasPrenhez) {
		this.diasPrenhez = diasPrenhez;
	}
	public double getPesoMaturidade() {
		return pesoMaturidade;
	}
	public void setPesoMaturidade(double pesoMaturidade) {
		this.pesoMaturidade = pesoMaturidade;
	}
	public double getGanhoPeso() {
		return ganhoPeso;
	}
	public void setGanhoPeso(double ganhoPeso) {
		this.ganhoPeso = ganhoPeso;
	}
	public double getExMS() {
		return exMS;
	}
	public void setExMS(double exMS) {
		this.exMS = exMS;
	}
	public double getExEnergia() {
		return exEnergia;
	}
	public void setExEnergia(double exEnergia) {
		this.exEnergia = exEnergia;
	}
	public double getExProteina() {
		return exProteina;
	}
	public void setExProteina(double exProteina) {
		this.exProteina = exProteina;
	}
	public double getExCalcio() {
		return exCalcio;
	}
	public void setExCalcio(double exCalcio) {
		this.exCalcio = exCalcio;
	}
	public double getExFosforo() {
		return exFosforo;
	}
	public void setExFosforo(double exFosforo) {
		this.exFosforo = exFosforo;
	}
	

}
