package src.p03.c01;
/*
 * Autor: David Perez
 * Steven Alba
 * 
 * Interfaz IParque donde instanciamos los metodos de entrada y salida del parque.
 * 
 * Version 1.1
 * 
 */
public interface IParque {
	//Metodo Entrar al parque
	public abstract void entrarAlParque(String puerta);
	//Metodo Salir del parque
	public abstract void salirDelParque(String puerta);

}
