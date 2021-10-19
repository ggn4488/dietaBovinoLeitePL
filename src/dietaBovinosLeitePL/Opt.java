package dietaBovinosLeitePL;



public class Opt {
	
	public static void main(String args[]) {
		
		// Cria novo animal
		Animal animal = new Animal();
		
		// Cadastra animal com suas características iniciais (double pesoCorporal, double producaoLeite, int diasLactacao, int numeroLactacao, int diasPrenhez)
		animal.cadastraAnimal(506, 26, 275, 1, 211);
		
		// Faz os cálculos de exigências nutricionais
		animal.calculaMateriaSeca();
		animal.calculaEnergia();
		animal.calculaProteina();
		animal.calculaCalcio();
		animal.calculaFosforo();
		
		//System.out.println(animal.getExMateriaSeca());   // kg/dia
		//System.out.println(animal.getExEnergia());      //  Mcal/dia
		//System.out.println(animal.getExProteina());     //  g/dia
		//System.out.println(animal.getExCalcio());       //  g/dia
		//System.out.println(animal.getExFosforo());      //  g/dia
		
		// Falta chamar o Solver para otimizar a dieta.
	
	}

}
