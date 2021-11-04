package semillero.pruebasUnitarias;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.enums.*;
import com.hbt.semillero.excepcion.excepcionComics;

/**
 * 
 * <b>Descripción:<b> Clase que permitirá la creación de comics y la realización
 * de pruebas unitarias para gestionar los comics creados.
 * 
 * @author Cristian Daniel Narvaez Medina
 * @version 1.0
 */
public class CreacionComicTest {

	/**
	 * Constante que contendra el log de la clase CreacionComicTest
	 */
	private final static Logger log = Logger.getLogger(CreacionComicTest.class);

	/**
	 * 
	 * Metodo encargado de dar configuraciones iniciales para las pruebas unitarias 
	 * 
	 * @author Cristian Daniel Narvaez Medina
	 *
	 */
	@BeforeTest
	public void inicializar() {
		BasicConfigurator.configure(); // Inicializa el logger con una configuracion basica
		log.info(":::::::::::::::::::::::::::: INICIAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}

	/**
	 * ArrayList que tiene todos los comics
	 */
	private ArrayList<ComicDTO> comics = new ArrayList<ComicDTO>();

	/**
	 * ArrayList que tiene los comics de estado activo
	 */
	private ArrayList<ComicDTO> comicsActivos = new ArrayList<ComicDTO>();

	/**
	 * ArrayList que tiene los comics de estado inactivo
	 */
	private ArrayList<ComicDTO> comicsInactivos = new ArrayList<ComicDTO>();

	/**
	 * 
	 * Metodo encargado de crear los 10 comics para almacenarlos en las respectivas
	 * listas
	 * 
	 * @author Cristian Daniel Narvaez Medina
	 *
	 */
	@BeforeTest
	public void cargarComics() {

		ComicDTO comic1 = new ComicDTO(1L, "Dragon Ball GT", "Planeta Cómic", TematicaEnum.AVENTURAS, "Manga", 300,
				new BigDecimal(56000), "Akira Toriyama", false, LocalDate.of(1992, 5, 10), EstadoEnum.ACTIVO, 50L);
		ComicDTO comic2 = new ComicDTO(2L, "Bleach", "Norma", TematicaEnum.FANTASTICO, "Manga", 200,
				new BigDecimal(56000), "Akira Toriyama", false, LocalDate.of(1900, 1, 13), EstadoEnum.INACTIVO, 100L);
		ComicDTO comic3 = new ComicDTO(3L, "Uzumaki", "Norma", TematicaEnum.HORROR, "Manga", 100,
				new BigDecimal(56000), "Akira Toriyama", true, LocalDate.of(2010, 1, 13), EstadoEnum.ACTIVO, 20L);
		ComicDTO comic4 = new ComicDTO(4L, "Haikyuu!", "Norma", TematicaEnum.DEPORTIVO, "Manga", 115,
				new BigDecimal(56000), "Akira Toriyama", false, LocalDate.of(2015, 1, 13), EstadoEnum.INACTIVO, 80L);
		ComicDTO comic5 = new ComicDTO(5L, "One Piece", "Planeta Cómic", TematicaEnum.AVENTURAS, "Manga", 120,
				new BigDecimal(56000), "Akira Toriyama", true, LocalDate.of(2011, 1, 13), EstadoEnum.ACTIVO, 15L);
		ComicDTO comic6 = new ComicDTO(6L, "Samurai X", "Planeta Cómic", TematicaEnum.AVENTURAS, "Manga", 160,
				new BigDecimal(56000), "Akira Toriyama", false, LocalDate.of(2020, 1, 13), EstadoEnum.INACTIVO, 22L);
		ComicDTO comic7 = new ComicDTO(7L, "Dune", "Norma", TematicaEnum.CIENCIA_FICCION, "Novela grafica", 220,
				new BigDecimal(100000), "Akira Toriyama", false, LocalDate.of(2005, 1, 13), EstadoEnum.ACTIVO, 100L);
		ComicDTO comic8 = new ComicDTO(8L, "Berserk", "Panini", TematicaEnum.BELICO, "Manga", 100,
				new BigDecimal(56000), "Akira Toriyama", true, LocalDate.of(1998, 1, 13), EstadoEnum.INACTIVO, 16L);
		ComicDTO comic9 = new ComicDTO(9L, "Avatar", "Panini", TematicaEnum.CIENCIA_FICCION, "Novela grafica", 150,
				new BigDecimal(100000), "Akira Toriyama", false, LocalDate.of(1995, 1, 13), EstadoEnum.INACTIVO, 30L);
		ComicDTO comic10 = new ComicDTO(10L, "Chainsaw Man", "Panini", TematicaEnum.HORROR, "Manga", 111,
				new BigDecimal(56000), "Akira Toriyama", true, LocalDate.of(2021, 1, 13), EstadoEnum.ACTIVO, 96L);

		comics.add(comic1);
		comics.add(comic2);
		comics.add(comic3);
		comics.add(comic4);
		comics.add(comic5);
		comics.add(comic6);
		comics.add(comic7);
		comics.add(comic8);
		comics.add(comic9);
		comics.add(comic10);

		comicsActivos.add(comic1);
		comicsActivos.add(comic3);
		comicsActivos.add(comic5);
		comicsActivos.add(comic7);
		comicsActivos.add(comic10);

		comicsInactivos.add(comic2);
		comicsInactivos.add(comic4);
		comicsInactivos.add(comic6);
		comicsInactivos.add(comic8);
		comicsInactivos.add(comic9);
	}

	/**
	 * 
	 * Metodo encargado de determinar cuales son los comics con estado activo y almacenarlos
	 * en una lista designada para ello
	 * 
	 * @author Cristian Daniel Narvaez Medina
	 * @return lista auxiliar con comics activos
	 */
	private ArrayList<ComicDTO> listaComicsActivos() {
		ArrayList<ComicDTO> activosAux = new ArrayList<ComicDTO>();
		for (ComicDTO comicDTO : comics) {
			if (comicDTO.getEstadoEnum().equals(EstadoEnum.ACTIVO)) {
				activosAux.add(comicDTO);
				System.out.println(comicDTO.toString());
			}
		}

		return activosAux;
	}

	/**
	 * 
	 * Metodo encargado de determinar cuales son los comics con estado inactivo y almacenarlos
	 * en una lista designada para ello
	 * 
	 * @author Cristian Daniel Narvaez Medina
	 * @return lista auxiliar con comics inactivos
	 */
	private ArrayList<ComicDTO> listaComicsInactivos() {
		ArrayList<ComicDTO> inactivosAux = new ArrayList<ComicDTO>();
		for (ComicDTO comicDTO : comics) {
			if (comicDTO.getEstadoEnum().equals(EstadoEnum.INACTIVO)) {
				inactivosAux.add(comicDTO);
			}
		}

		return inactivosAux;
	}

	/**
	 * 
	 * Metodo encargado de devolver los nombres de los comics con un estado determinado
	 * 
	 * @author Cristian Daniel Narvaez Medina
	 * @return nombres de los comics seleccionados
	 */
	private String devuleveNombres(ArrayList<ComicDTO> entrada) {
		String cadena = "";
		for (ComicDTO comicDTO : entrada) {
			cadena += comicDTO.getNombre() + ", ";
		}
		return cadena;

	}

	/**
	 * 
	 * Metodo encargado de validar que efectivamente los comics de la lista de comics ativos
	 * tienen este estado
	 *
	 * @author Cristian Daniel Narvaez Medina
	 *
	 */
	@Test
	public void verificarComicsActivos() {
		log.info("Inicia ejecucion del metodo verificarComicsActivos()");
		
		Assert.assertEquals(comicsActivos, listaComicsActivos());
		
		log.info("Finaliza la ejecucion del metodo verificarComicsActivos()");

	}

	/**
	 * Creación de variables para los mensajes de la excepcion de verificarComicsInactivos()
	 */
	int tamanoListaTotal = comics.size();
	int numeroTotalActivos = comicsActivos.size();
	int numeroTotalInactivos = comicsInactivos.size();
	String nombresComicsInactivos = devuleveNombres(comicsInactivos);
	
	/**
	 * 
	 * Metodo encargado de verificar los comics inactivos y generar una excepcion que muestre los
	 * nombres de los comics con dicho estado
	 * 
	 * @author Cristian Daniel Narvaez Medina
	 *
	 */
	@Test
	public void verificarComicsInactivos() throws excepcionComics{
		log.info("Inicia ejecucion del metodo verificarComicsInactivos()");
		try {
			if (!comicsInactivos.equals(listaComicsInactivos())) {
				log.info("Se ha generado un error funcional probando el test verificarComicsInactivos()");
				throw new excepcionComics(tamanoListaTotal, numeroTotalActivos, numeroTotalInactivos, true);
			}
		} catch (excepcionComics e) {
			log.info(e.getMessage());
			log.info(" Los comics inactivos son: "+nombresComicsInactivos);
			Assert.assertFalse(e.isFalla());
		}
		log.info("Finaliza la ejecucion del metodo verificarComicsInactivos()");
	}
	

	/**
	 * 
	 * Metodo encargado de mostrar que las pruebas unitarias han finalizado efectivamente 
	 * 
	 * @author Cristian Daniel Narvaez Medina
	 *
	 */
	@AfterTest
	public void finalizaPruebasUnitarias() {
		log.info(":::::::::::::::::::::::::::: FINALIZAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
}
