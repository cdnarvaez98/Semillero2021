/**
 * GestionarCompraComicRest.java
 */
package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.ejb.IGestionarCompraComic;

/**
 * <b>Descripción:<b> Clase que determina
 * <b>Caso de Uso:<b> 
 * @author danie
 * @version 
 */

@Path("/gestionarCompra")
public class GestionarCompraComicRest {
	
	@EJB
	private IGestionarCompraComic gestionarCompraLocal;
	
	@GET
	@Path("/comprarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO comprarComic(@QueryParam("nombre") String nombre, @QueryParam("cantidad") Long cantidad) {
		return this.gestionarCompraLocal.comprarComic(nombre, cantidad);
	}
	
}
