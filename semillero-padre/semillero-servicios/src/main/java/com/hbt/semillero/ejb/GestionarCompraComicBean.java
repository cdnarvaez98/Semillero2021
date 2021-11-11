/**
 * GestionarCompraComicBean.java
 */
package com.hbt.semillero.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;

/**
 * <b>Descripción:<b> Clase que determina
 * <b>Caso de Uso:<b> 
 * @author danie
 * @version 
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraComicBean implements IGestionarCompraComic{
	
	@PersistenceContext
	public EntityManager em;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO comprarComic(String nombre, Long cantidad) {
		
		String consulta = "UPDATE Comic c SET c.cantidad = SCCANTIDAD - :cantidadComic, c.fechaVenta = CURRENT_DATE WHERE c.nombre = :nombreComic";
		String consultaCeroComics = "UPDATE Comic c SET c.cantidad = SCCANTIDAD - :cantidadComic, c.fechaVenta = CURRENT_DATE, c.estadoEnum = INACTIVO WHERE c.nombre = :nombreComic";
		Long numeroComics = 0L;
		
		ComicDTO comicActualizado = new ComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("cantidadComic", cantidad);
			consultaNativa.setParameter("nombreComic", nombre);
			consultaNativa.executeUpdate();
			Comic comic = new Comic();
			if (comic.getNombre() == nombre) {
				comicActualizado = convertirComicToComicDTO(comic);
			}
			if (comic.getCantidad() == cantidad) {
				Query consultaNativaCero = em.createQuery(consultaCeroComics);
				consultaNativaCero.setParameter("cantidadComic", cantidad);
				consultaNativaCero.setParameter("nombreComic", nombre);
				consultaNativa.executeUpdate();
				Comic comicAlt = new Comic();
				comicActualizado = convertirComicToComicDTO(comicAlt);
			}
			if (cantidad > comic.getCantidad()) {
				numeroComics = comic.getCantidad();
			}
			comicActualizado.setExitoso(true);
			comicActualizado.setMensajeEjecucion("La compra del comic "+nombre+" fue exitosa");
		} catch (NoResultException e) {
			comicActualizado.setExitoso(false);
			comicActualizado.setMensajeEjecucion("La cantidad existente del comic es: "+numeroComics+ " ,y supera la ingresada");
		} catch (Exception e) {
			comicActualizado.setExitoso(false);
			comicActualizado.setMensajeEjecucion("No se ha podido comprar el comic");
		}
		return comicActualizado;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		comicDTO.setId(comic.getId());
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		comic.setId(comicDTO.getId());
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}
	
	

}
