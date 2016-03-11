package es.upm.dit.adsw.puentesencillo;

public class SimulaPuenteSencillo {


	public static void main(String[] args) {

		java.util.Random generador = new java.util.Random(System.currentTimeMillis());
		int paraEmpezar     = 2;
		int nCoches         = 8;
		int idCoche         = 0;
		long retardoInicial;
		long maxRetardoInicial = 10000; //milisegundos
		GestorPuenteSencillo elGestor = new GestorPuenteSencillo();

		//Creo unos cuantos coches para empezar
		for (int i = 0; i < paraEmpezar; i++) {

			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			new    CocheNorte(elGestor, idCoche, retardoInicial); 
			idCoche++;

			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			new    CocheSur(elGestor, idCoche, retardoInicial);
			idCoche++;
		}

		//Luego voy creando poco a poco

		for (int i = paraEmpezar; i < nCoches; i++){

			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			new CocheSur(elGestor, idCoche, retardoInicial);
			idCoche++;

			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			new CocheNorte(elGestor, idCoche, retardoInicial);
			idCoche++;
		}
	}
}