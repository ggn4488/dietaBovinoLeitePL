package dietaBovinosLeitePL;

import it.ssc.log.SscLogger;
import it.ssc.pl.milp.ConsType;
import it.ssc.pl.milp.Constraint;
import it.ssc.pl.milp.GoalType;
import it.ssc.pl.milp.LP;
import it.ssc.pl.milp.LinearObjectiveFunction;
import it.ssc.pl.milp.Solution;
import it.ssc.pl.milp.SolutionType;
import it.ssc.pl.milp.Variable;

import java.util.ArrayList;

public class SolverNutrilac {

	public static void main(String args[]) {


		// Este é um exemplo. Adaptar o código para consultar a base de dados e recuperar informações dos alimentos que o usuário determinar.
		// 1 - Silagem de milho
		// 2 - Milho Fubá
		// 3 - Farelo de soja
		// 4 - Ureia
		// 5 - Calcário
		// 6 - Sal
		
		// custos (em Reais / Kg de matéria natural)

		double custo1 = 0.50, custo2 = 1.33, custo3 = 1.65, custo4 = 0.30, custo5 = 3.26, custo6 = 2.00;

		// proporção de matéria seca em cada alimento (em %)
		double MateriaSeca1 = 31.14, MateriaSeca2 = 87.96, MateriaSeca3 = 88.64, MateriaSeca4 = 97.88, MateriaSeca5 = 99.22, MateriaSeca6 = 98.97; 
				
	    
	    // quantidade de energia metabolizável em cada alimento (em Mcal/kg de matéria seca) 
		double EnergiaMetabolizavel1 = 2.29, EnergiaMetabolizavel2 = 3.26, EnergiaMetabolizavel3 = 3.40, EnergiaMetabolizavel4 = 0.00, EnergiaMetabolizavel5 = 0.00, EnergiaMetabolizavel6 = 0.00;

		// proporção de proteína bruta em cada alimento (em % da matéria seca)
		double ProteinaBruta1 = 7.18, ProteinaBruta2 = 9.01, ProteinaBruta3 = 48.78, ProteinaBruta4 = 281.92, ProteinaBruta5 = 0.00, ProteinaBruta6 = 0.00;

		// quantidade de cálcio em cada alimento
		double Calcio1 = 0.28, Calcio2 = 0.03, Calcio3 = 0.34, Calcio4 = 0.00, Calcio5 = 37.02, Calcio6 = 0.00;

		// quantidade de fósforo em cada alimento
		double Fosforo1 = 0.19, Fosforo2 = 0.26, Fosforo3 = 0.59, Fosforo4 = 0.00, Fosforo5 = 0.02, Fosforo6 = 0.30;

		// Restrições (lado esquerdo das inequações)
		// verificar se há mais restrições a acrescentar, na tabela de restrições que está no Drive
		double A[][] = { { 1, 1, 1, 1, 1, 1 }, 			   // ingestão diária de matéria seca
				{ 0.4, -0.6, -0.6, -0.6, -0.6, -0.6 }, 	  // proporção de volumoso x concentrado permitida
				{ 0, -0.03, -0.03, 0.97, -0.03, -0.03 }, //  ingestão de uréia diária permitida
				{ 0, 0, 0, 1, 0, 0 }, 					//   ingestão de uréia diária permitida
				{ 0, 0, 0, 1, 0, 0 }, 					//   ingestão de uréia diária permitida
				{ EnergiaMetabolizavel1, EnergiaMetabolizavel2, EnergiaMetabolizavel3, EnergiaMetabolizavel4, EnergiaMetabolizavel5, EnergiaMetabolizavel6 }, 		// 	 necessidade diária de energia
				{ 10 * ProteinaBruta1, 10 * ProteinaBruta2, 10 * ProteinaBruta3, 10 * ProteinaBruta4, 10 * ProteinaBruta5, 10 * ProteinaBruta6 }, // necessidade diária de proteína
				{ 10 * Calcio1, 10 * Calcio2, 10 * Calcio3, 10 * Calcio4, 10 * Calcio5, 10 * Calcio6 }, // necessidade diária de cálcio
				{ 10 * Fosforo1, 10 * Fosforo2, 10 * Fosforo3, 10 * Fosforo4, 10 * Fosforo5, 10 * Fosforo6 }, // necessidade diária de fósforo
		};

		// Restrições (lado direito das inequações)
		double b[] = {animal.exMateriaSeca,
				0,
				0,
				0.0005 * animal.pesoCorporal,
				0.02,
				animal.exEnergia,
				animal.exProteina,
				animal.exCalcio,
				animal.exFosforo
				};

		// Função-objetivo
		double c[] = { (100 * custo1) / MateriaSeca1, 
				(100 * custo2) / MateriaSeca2, 
				(100 * custo3) / MateriaSeca3, 
				(100 * custo4) / MateriaSeca4, 
				(100 * custo5) / MateriaSeca5, 
				(100 * custo6) / MateriaSeca6 };

		// Relações das restrições
		ConsType[] rel = { ConsType.GE, 
				ConsType.EQ, 
				ConsType.LE, 
				ConsType.LE, 
				ConsType.LE, 
				ConsType.GE, 
				ConsType.GE,
				ConsType.GE, 
				ConsType.GE };

// Função-objetivo
		LinearObjectiveFunction fo = new LinearObjectiveFunction(c, GoalType.MIN);

// Cria restrições

		ArrayList<Constraint> constraints = new ArrayList<Constraint>();

		for (int i = 0; i < A.length; i++) {
			constraints.add(new Constraint(A[i], rel[i], b[i]));
		}

// Cria o modelo
		LP lp = new LP(fo, constraints);
		SolutionType solution_type = lp.resolve();

// Resolve o modelo
		if (solution_type == SolutionType.OPTIMUM) {
			Solution solution = lp.getSolution();
			for (Variable var : solution.getVariables()) {
				SscLogger.log("Variable name :" + var.getName() + " value:" + var.getValue());
			}
			SscLogger.log("o.f. value:" + solution.getOptimumValue());
		}
	}
}