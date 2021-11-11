/**
 * ConsultarLengthNombreComicDTO.java
 */
package com.hbt.semillero.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * <b>Descripción:<b> Clase que determina los atributos y metodos para el DTO del servicio
 * 					  que consulta el tamaño de los nombres de los comics
 * <b>Caso de Uso:<b> 
 * @author Cristian Daniel Narvaez Medina
 * @version 1.0
 */
public class ConsultarLengthNombreComicDTO extends ResultadoDTO{

	/**
	 * Atributo que determina  
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> comicsSuperanTamanio;
	private List<String> comicsNoSuperanTamanio;
	
	/**
	 * Constructor de la clase.
	 * @param comicsSuperanTamanio
	 * @param comicsNoSuperanTamanio
	 */
	public ConsultarLengthNombreComicDTO() {
		this.comicsNoSuperanTamanio = new ArrayList<>();
		this.comicsSuperanTamanio = new ArrayList<>();
	}

	/**
	 * Metodo encargado de retornar el valor del atributo comicsSuperanTamanio
	 * @return El comicsSuperanTamanio asociado a la clase
	 */
	public List<String> getComicsSuperanTamanio() {
		return comicsSuperanTamanio;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo comicsSuperanTamanio
	 * @param comicsSuperanTamanio El nuevo comicsSuperanTamanio a modificar.
	 */
	public void setComicsSuperanTamanio(List<String> comicsSuperanTamanio) {
		this.comicsSuperanTamanio = comicsSuperanTamanio;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo comicsNoSuperanTamanio
	 * @return El comicsNoSuperanTamanio asociado a la clase
	 */
	public List<String> getComicsNoSuperanTamanio() {
		return comicsNoSuperanTamanio;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo comicsNoSuperanTamanio
	 * @param comicsNoSuperanTamanio El nuevo comicsNoSuperanTamanio a modificar.
	 */
	public void setComicsNoSuperanTamanio(List<String> comicsNoSuperanTamanio) {
		this.comicsNoSuperanTamanio = comicsNoSuperanTamanio;
	}
	
	
}
