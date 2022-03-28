package src.p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Autor: David Perez
 * Steven Alba
 * 
 * Clase ActividadSalidaPuerta donde indicamos cuando se sale del parque y por que puerta.
 * 
 * Version 1.1
 * 
 */

public class ActividadSalidaPuerta implements Runnable{

	//
	// TODO
	//
	
	private static final int NUMENTRADAS = 20;
	private String puerta;
	private IParque parque;

	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i < NUMENTRADAS; i ++) {
			try {
				parque.salirDelParque(puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Entrada interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}
