package es.upm.dit.adsw.julio2013;

public class SimularGaraje {

	public static void main(String[] args) {

		java.util.Random generador = new java.util.Random(System.currentTimeMillis());
		int paraEmpezar     = 5;
		int nCoches         = 20;
		int cochesGaraje    = 8;
		int idCoche         = 0;
		long retardoInicial;
		long maxRetardoInicial = 10000; //milisegundos
		GestorGaraje elGestor = new GestorGaraje(cochesGaraje);

		//Creo unos cuantos coches para empezar
		for (int i = 0; i < paraEmpezar; i++) {

			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			new CocheEste(elGestor, idCoche, retardoInicial); 
			idCoche++;

			retardoInicial = (long) (maxRetardoInicial * generador.nextFloat());
			new CocheOeste(elGestor, idCoche, retardoInicial);
			idCoche++;
		}

		//Luego voy creando poco a poco

		for (int i = paraEmpezar; i < nCoches; i++){

			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			new CocheEste(elGestor, idCoche, retardoInicial);
			idCoche++;

			retardoInicial = (long)(maxRetardoInicial * generador.nextFloat());
			new CocheOeste(elGestor, idCoche, retardoInicial);
			idCoche++;
		}
	}
}
