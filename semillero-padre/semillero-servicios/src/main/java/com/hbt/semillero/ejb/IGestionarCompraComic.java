/**
 * IGestionarCompraComic.java
 */
package com.hbt.semillero.ejb;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;

/**
 * <b>Descripción:<b> Clase que determina
 * <b>Caso de Uso:<b> 
 * @author danie
 * @version 
 */

@Local
public interface IGestionarCompraComic {
	
	public ComicDTO comprarComic(String nombre, Long cantidad);
}
