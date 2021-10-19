package dietaBovinosLeitePL;

public class Animal {

	// atributos modificáveis pelo usuário
	private double pesoCorporal;
	private double producaoLeite;
	private int diasLactacao;
	private int numeroLactacao;
	private int diasPrenhez;
	
	private double pesoMaturidade;
	private double ganhoPeso;
	private double fatorManutencao;
	
	// atributos não-modificáveis pelo usuário
	
	private double teorGorduraLeite = 3.5;
	private double teorProteinaLeite = 3.2;
	private double exNDT = 70;
	private double exNDF = 28;
	
	// atributos que serão calculados
	private double exMateriaSeca;
	private double exEnergia;
	private double exProteina;
	private double exCalcio;
	private double exFosforo;
	
	public void cadastraAnimal(double pesoCorporal, double producaoLeite, int diasLactacao, int numeroLactacao, int diasPrenhez) {
		
		this.setPesoCorporal(pesoCorporal);
		this.setProducaoLeite(producaoLeite);
		this.setDiasLactacao(diasLactacao);
		this.setNumeroLactacao(numeroLactacao);
		this.setDiasPrenhez(diasPrenhez);
		this.setGanhoPeso(0.0003 * this.pesoCorporal);
		this.setPesoMaturidade(1.12 * this.pesoCorporal);
        this.setFatorManutencao(1);;

        if (this.getNumeroLactacao() == 1) {
            this.setFatorManutencao(1.2);
        } else if (this.getNumeroLactacao() == 2) {
            this.setFatorManutencao(1.1);
        }
		
	}
	
	public void calculaMateriaSeca() {
		
		this.setExMateriaSeca(0.0185 * this.getPesoCorporal() + 0.305 * (0.4 + 0.15 * this.getTeorGorduraLeite()) * this.getProducaoLeite());
		
	}
	
	public void calculaEnergia() {
		
        double energiaManutencao = this.getFatorManutencao() * 0.08 * (Math.pow(this.getPesoCorporal(), 0.75));

        double energiaLactacao = (0.3512 + (0.0962 * this.getTeorGorduraLeite())) * this.getProducaoLeite();

        double energiaCrescimento = 5.12 * this.getGanhoPeso();

        double energiaPrenhez = 0;

        if(this.getDiasPrenhez() > 210) {
            energiaPrenhez = 0.024 * Math.pow(this.getPesoCorporal(), 0.75);
        }

        double energiaTotal = energiaManutencao + energiaLactacao + energiaCrescimento + energiaPrenhez;
        
        double NDTAjustado = this.getExNDT() - (((energiaTotal) / energiaManutencao - 1) * 3);

        double energiaLiquida = 0.0266 * NDTAjustado - 0.12;
        
        this.setExEnergia(energiaTotal);
		
	}
	
	public void calculaProteina() {
		
        double proteinaManutencao = this.getFatorManutencao() * (0.2 * Math.pow(this.getPesoCorporal(), 0.6)) / 0.67 + (2.75 * Math.pow(this.getPesoCorporal(), 0.5)) / 0.67;

        double proteinaLactacao = (((1.9 + 0.4 * this.getTeorGorduraLeite()) / 100) * (1000 * this.getProducaoLeite())) / 0.7;

        double proteinaCrescimento = (211-26.2 * ((0.035 * Math.pow(this.getPesoCorporal(), 0.75) * Math.pow((this.getGanhoPeso()/1000), 1.119) + (this.getGanhoPeso()/1000)) / (this.getGanhoPeso()/1000)))/0.5;

        double proteinaPrenhez = 0;
        
        if(this.getDiasPrenhez() > 210) {
            proteinaPrenhez = (1.136 * Math.pow(this.getPesoCorporal(), 0.7)) / 0.5;
        }

        double proteinaTotal = proteinaManutencao + proteinaLactacao + proteinaCrescimento + proteinaPrenhez;

        double proteinaBacteriana = 6.25*(-30.93 + 11.45 * ((0.3512 + (0.0962 * this.getTeorGorduraLeite())) * this.getProducaoLeite()));

        double proteinaBacterianaDisponivel = proteinaBacteriana / 0.9;

        double proteinaBacterianaDisponivelVerdadeira = proteinaBacteriana * 0.8;

        double proteinaBacterianaDigestivel = proteinaBacterianaDisponivelVerdadeira * 0.8;

        double proteinaDigerivelNaoDegradada = proteinaTotal - proteinaBacterianaDigestivel;

        double ingestaoProteinaNaoDegradada = proteinaDigerivelNaoDegradada / 0.8;

        this.setExProteina((proteinaBacterianaDisponivel + ingestaoProteinaNaoDegradada)/ 1.15);
		
	}
	
	
	public void calculaCalcio() {

        double calcioFecal = 3.1 * (this.getPesoCorporal() / 100);

        double calcioUrinario = 0.08 * (this.getPesoCorporal() / 100);

        double calcioPrenhez = 0;

        if(this.getDiasPrenhez() > 190) {
            calcioPrenhez = 0.02456 * Math.exp((0.05581 - (0.00007 * this.getDiasPrenhez())) *
                    this.getDiasPrenhez()) - 0.02456 * Math.exp((0.05581 - (0.00007 *
                    (this.getDiasPrenhez() - 1))) * (this.getDiasPrenhez() - 1));
        }

        double calcioLactacao = 1.22 * (0.4 * this.getProducaoLeite() + 15 *(this.getProducaoLeite() * 
        		this.getTeorGorduraLeite()/100));

        double calcioCrescimento = (9.83 * Math.pow(this.getPesoMaturidade(), 0.22) *
                Math.pow(this.getPesoCorporal(), -0.22)) *
                (this.getGanhoPeso() / 0.96);

        this.setExCalcio(calcioFecal + calcioUrinario + calcioPrenhez + calcioLactacao + calcioCrescimento);
	}
	
	public void calculaFosforo() {
		
        double fosforoFecal = this.getExMateriaSeca();

        double fosforoUrinario = 0.002 * this.getPesoCorporal();

        double fosforoPrenhez = 0;

        if(this.getDiasPrenhez() > 190) {
            fosforoPrenhez = 0.02743 * Math.exp((0.05527 - (0.000075 * this.getDiasPrenhez())) *
                    this.getDiasPrenhez()) - 0.02743 * Math.exp((0.05527 - (0.000075 *
                    (this.getDiasPrenhez() - 1))) * (this.getDiasPrenhez() - 1));
        }

        double fosforoLactacao = 0.9 * (0.4 * this.getProducaoLeite() + 15 *(this.getProducaoLeite() * 
        		this.getTeorGorduraLeite()/100));

        double fosforoCrescimento = (1.2 + (4.635 * Math.pow(this.getPesoMaturidade(), 0.22) *
                Math.pow(this.getPesoCorporal(), -0.22))) *
                (this.getGanhoPeso() / 0.96);

        this.setExFosforo(fosforoFecal + fosforoUrinario + fosforoPrenhez + fosforoLactacao + fosforoCrescimento);
		
	}
	
	// getters e setters
	public double getPesoCorporal() {
		return pesoCorporal;
	}
	public void setPesoCorporal(double pesoCorporal) {
		this.pesoCorporal = pesoCorporal;
	}
	public double getProducaoLeite() {
		return producaoLeite;
	}

	public void setProducaoLeite(double producaoLeite) {
		this.producaoLeite = producaoLeite;
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
	public double getFatorManutencao() {
		return fatorManutencao;
	}

	public void setFatorManutencao(double fatorManutencao) {
		this.fatorManutencao = fatorManutencao;
	}
	public double getTeorGorduraLeite() {
		return teorGorduraLeite;
	}

	public void setTeorGorduraLeite(double teorGorduraLeite) {
		this.teorGorduraLeite = teorGorduraLeite;
	}

	public double getTeorProteinaLeite() {
		return teorProteinaLeite;
	}

	public void setTeorProteinaLeite(double teorProteinaLeite) {
		this.teorProteinaLeite = teorProteinaLeite;
	}

	public double getExNDT() {
		return exNDT;
	}

	public void setExNDT(double exNDT) {
		this.exNDT = exNDT;
	}

	public double getExNDF() {
		return exNDF;
	}

	public void setExNDF(double exNDF) {
		this.exNDF = exNDF;
	}
	public double getExMateriaSeca() {
		return exMateriaSeca;
	}
	public void setExMateriaSeca(double exMateriaSeca) {
		this.exMateriaSeca = exMateriaSeca;
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
