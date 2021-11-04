package com.hbt.semillero.dto;

public class consultarComicTamanioNombre extends ResultadoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Short lengthComic;

	public consultarComicTamanioNombre() {
		//Constructor vacio
	}
	
	public consultarComicTamanioNombre(Short lengthComic) {
		super();
		this.lengthComic = lengthComic;
	}

	public Short getLengthComic() {
		return lengthComic;
	}

	public void setLengthComic(Short lengthComic) {
		this.lengthComic = lengthComic;
	}
	

}
