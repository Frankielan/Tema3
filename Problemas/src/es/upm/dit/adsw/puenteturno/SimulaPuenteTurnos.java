package es.upm.dit.adsw.puenteturno;

/**
 * @author Alejandro Alonso
 * @since 20150323
**/
public class SimulaPuenteTurnos {


	public static void main(String[] args) {

		java.util.Random generador = new java.util.Random(System.currentTimeMillis());
		int paraEmpezar     = 2;
		int nCoches         = 8;
		int idCoche         = 0;
		long retardoInicial;
		long maxRetardoInicial = 10000; //milisegundos
		GestorPuenteTurnos elGestor = new GestorPuenteTurnos();

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