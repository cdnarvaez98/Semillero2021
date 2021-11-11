package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarLengthNombreComicDTO;


@Local
public interface IGestionarComicLocal {
	
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic);

	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	
	public ComicDTO actualizarNombreComic(Long idComic, String nombre);
	
	public ComicDTO eliminarComic(Long idComic);
	
	public List<ComicDTO> consultarComics();
	
	public ConsultarLengthNombreComicDTO consultarComicTamanioNombre(Short lengthCadena);
}
