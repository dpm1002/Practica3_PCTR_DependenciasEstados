package src.p03.c01;
/*
 * Autor: David Perez
 * Steven Alba
 * 
 * Esta es la clase principal de parque donde indicamos cuando entran y salen y por que puerta.
 * 
 * Version 1.3
 * 
 */
import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{
	//Contador de personas totales en el parque.
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	// Definimos la variable capacidadMaxParque y le asignamos el valor de 50 segun el enunciado.
	public static final int capacidadMaxParque = 50;
	// Definimos la variable capacidadMinParque y le asignamos el valor de 0 ya que es 0 si esta vacio.
	public static final int capacidadMinParque = 0;
	
	//Constructor del parque
	public Parque() {	
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		
	}


	@Override
	public synchronized void entrarAlParque(String puerta){		
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		//Comprobamos si puede una persona entrar al parque
		comprobarAntesDeEntrar();	
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		//Comprobamos los invariantes
		checkInvariante();
		
		notifyAll();
	}
	
	// 
	// TODO Método salirDelParque
	//
	@Override
	public synchronized void salirDelParque(String puerta) {
		// Si no hay salidas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		//Comprobamos si puede una persona salir del parque
		comprobarAntesDeSalir();	
		
		// Reducimos el contador total y el individual
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");
		
		//Comprobamos los invariantes
		checkInvariante();
		
		notifyAll();
		
	}
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); 
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert contadorPersonasTotales <= capacidadMaxParque : "INV: El numero de personas supera el aforo maximo del parque permitido";
		assert contadorPersonasTotales >= capacidadMinParque: "INV: No puede haber un numero negativo de personas en el parque";
	}

	protected void comprobarAntesDeEntrar(){	
		
		while(contadorPersonasTotales >= capacidadMaxParque){
			try {
				wait();	
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void comprobarAntesDeSalir(){		
		
		while(contadorPersonasTotales <= capacidadMinParque){
			try {
				wait();	
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	


}
