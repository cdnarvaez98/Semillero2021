package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarLengthNombreComicDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;

@Path("/gestionarComic")
public class GestionarComicRest {

	@EJB
	private IGestionarComicLocal gestionarComicLocal;

	// http://localhost:8085/semillero-servicios/rest/gestionarComic/consultarNombrePrecioComic
	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}

	// http://localhost:8085/semillero-servicios/rest/gestionarComic/crearComic
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.crearComic(comicDTO);
		} catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	}

	// http://localhost:8085/semillero-servicios/rest/gestionarComic/actualizarNombreComic?idComic=25&nombre=GOKU
	@GET
	@Path("/actualizarNombreComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO actualizarNombreComic(@QueryParam("idComic") Long idComic, @QueryParam("nombre") String nombre) {
		return this.gestionarComicLocal.actualizarNombreComic(idComic, nombre);
	}

	// http://localhost:8085/semillero-servicios/rest/gestionarComic/eliminarComic?idComic=4
	@GET
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO eliminarComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.eliminarComic(idComic);
	}

	// http://localhost:8085/semillero-servicios/rest/gestionarComic/consultarComics
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComics() {
		return this.gestionarComicLocal.consultarComics();
	}

	// http://localhost:8085/semillero-servicios/rest/gestionarComic/consultarComicTamanioNombre?lengthComic=5
	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultarLengthNombreComicDTO consultarComicTamanioNombre(@QueryParam("lengthComic") Short lengthComic) {
		return this.gestionarComicLocal.consultarComicTamanioNombre(lengthComic);
	}

}
