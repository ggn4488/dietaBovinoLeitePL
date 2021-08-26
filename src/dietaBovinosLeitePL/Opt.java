package dietaBovinosLeitePL;

public class Opt {
	
	public static void main(String args[]) {
		
		Animal animal = new Animal();
		animal.cadastraAnimal(506, 26, 275, 1, 211);
		animal.calculaMateriaSeca();
		animal.calculaEnergia();
		animal.calculaProteina();
		System.out.println(animal.getExProteina());
	
	}

}
