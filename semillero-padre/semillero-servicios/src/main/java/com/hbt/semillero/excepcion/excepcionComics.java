package com.hbt.semillero.excepcion;

import java.util.ArrayList;

import com.hbt.semillero.dto.ComicDTO;

/**
 * 
 * <b>Descripción:<b> Clase que determina excepciones para los métodos de CreacionComicTest
 * 
 * @author Cristian Daniel Narvaez Medina
 * @version 1.0
 */
public class excepcionComics extends Exception{

	/**
	 * Variable para conocer cuando una excepcion es valida. Es decir,
	 * si efectivamente se ha alcanzado la condicion de falla 
	 * para poder lanzar la excepcion correspondiente 
	 */
	private boolean falla;
	
	/**
	 * Método constructor para el mensaje de la excepcion a generar en la clase CreacionComicTest
	 */
	public excepcionComics (int tamanoListaTotal, int numeroTotalActivos, int numeroTotalInactivos, boolean falla) {
		super("Se ha detectado que de "+tamanoListaTotal+" comics se encontraron que "+numeroTotalActivos+" se encuentran activos y "+numeroTotalInactivos+" inactivos.");
		this.setFalla(falla);		
	}

	/**
	 * Metodo encargado de retornar el valor del atributo falla
	 * @return El falla asociado a la clase
	 */
	public boolean isFalla() {
		return falla;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo falla
	 * @param falla El nuevo falla a modificar.
	 */
	public void setFalla(boolean falla) {
		this.falla = falla;
	}
	
}
