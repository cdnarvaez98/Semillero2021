import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ComicDTO } from '../../dto/comic-dto';

/**
 * @description Componente bienvenida, el cual contiene la imagen de bienvenida al semillero
 * 
 * @author Cristian Daniel Narvaez Medina <cdnarvaez@unicauca.edu.co>
 */
@Component({
  selector: 'bienvenida',
  templateUrl: './bienvenida-component.html',
})
export class BienvenidaComponent implements OnInit {
  
  public urlImagen : string;

  public comicDTO : ComicDTO;
  
  constructor(private router : Router, private activatedRoute: ActivatedRoute) {
    console.log("entro al constructor del componente bienvenida");
  }

  ngOnInit(): void {
    this.urlImagen = "https://www.elempleo.com/sitios-empresariales/colombia/heinsohn-business-technology/img/elempleo-02.jpg";
    let comic : any = this.activatedRoute.snapshot.params;
    this.comicDTO = comic;
    
    //alert("Parametros recibidos " + JSON.stringify(dataComic));
    
  }

  public ejecucionEventoClick( parametroEvento : any, numero : number ) : void {
    alert("Hola: " + parametroEvento + ' ' + numero);
    
  }

}