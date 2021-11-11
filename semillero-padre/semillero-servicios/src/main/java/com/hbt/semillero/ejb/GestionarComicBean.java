package com.hbt.semillero.ejb;

import java.util.List;

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
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarLengthNombreComicDTO;
import com.hbt.semillero.entidad.Comic;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	@PersistenceContext
	public EntityManager em;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		String consulta = "SELECT new com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO(c.nombre, c.precio)  "
				+ " FROM Comic c WHERE c.id = :idComic";
		ConsultaNombrePrecioComicDTO consultaNombrePrecioDTO = new ConsultaNombrePrecioComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNombrePrecioDTO = (ConsultaNombrePrecioComicDTO) consultaNativa.getSingleResult();
			consultaNombrePrecioDTO.setExitoso(true);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}

		return consultaNombrePrecioDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {

		if (comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}

		ComicDTO comicDTOResult = null;
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		em.persist(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic ha sido creado exitosamente");
		return comicDTOResult;
	}

	@Override
	public ComicDTO actualizarNombreComic(Long idComic, String nombre) {
		String consulta = "UPDATE Comic c SET c.nombre = :nombre WHERE id = :idComic";
		ComicDTO comicActualizado = new ComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("nombre", nombre);
			consultaNativa.setParameter("idComic", idComic);
			consultaNativa.executeUpdate();
			Comic comic = new Comic();
			if (comic.getId() == idComic) {
				comicActualizado = convertirComicToComicDTO(comic);
			}
			comicActualizado.setExitoso(true);
			comicActualizado.setMensajeEjecucion("El comic ha sido actualizado exitosamente");
		} catch (Exception e) {
			comicActualizado.setExitoso(false);
			comicActualizado.setMensajeEjecucion("Error al intentar actualizar el comic por nombre");
		}
		return comicActualizado;
	}

	@Override
	public ComicDTO eliminarComic(Long idComic) {
		ComicDTO comicEliminado = new ComicDTO();
		String consulta = "DELETE FROM Comic c WHERE c.id = :idComic";
		try {
			Query consultaEliminarUnComic = em.createQuery(consulta);
			consultaEliminarUnComic.setParameter("idComic", idComic);
			consultaEliminarUnComic.executeUpdate();
			Comic comic = new Comic();
			if (comic.getId() == idComic) {
				comicEliminado = convertirComicToComicDTO(comic);
			}
			comicEliminado.setExitoso(true);
			comicEliminado.setMensajeEjecucion("El comic fue eliminado correctamente");
		} catch (NoResultException e) {
			comicEliminado.setExitoso(false);
			comicEliminado.setMensajeEjecucion("El id no pertenece a ningún comic");
		} catch (Exception e) {
			comicEliminado.setExitoso(false);
			comicEliminado.setMensajeEjecucion("El comic no pudo ser eliminado, error técnico");
		}
		
		return comicEliminado;
	}

	@Override
	public List<ComicDTO> consultarComics() {
		String consulta = "SELECT c FROM Comic c";
		Query consultaTodosComics = em.createQuery(consulta);
		List<ComicDTO> listaComics = consultaTodosComics.getResultList();
		return listaComics;
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultarLengthNombreComicDTO consultarComicTamanioNombre(Short lengthCadena) {
		String consulta = "SELECT c.nombre "
				        + "FROM Comic c";
		ConsultarLengthNombreComicDTO resultadoDTO = new ConsultarLengthNombreComicDTO();
		try {
			if (lengthCadena > 300) {
				throw new Exception("La longitud máxima permitida es de 300 carácteres");
			}
			Query consultaTamanioCadena = em.createNamedQuery(consulta);
			List<String> nombresComics = consultaTamanioCadena.getResultList();
			for (String nombre : nombresComics) {
				if (nombre.length() >= lengthCadena) {
					resultadoDTO.getComicsSuperanTamanio().add(nombre);
				} else {
					resultadoDTO.getComicsNoSuperanTamanio().add(nombre);
				}
			}
			resultadoDTO.setExitoso(true);
			resultadoDTO.setMensajeEjecucion("Comics procesados exitosamente");
		} catch (Exception e) {
			resultadoDTO.setExitoso(false);
			resultadoDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar los comics");
		}
		return resultadoDTO;
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
