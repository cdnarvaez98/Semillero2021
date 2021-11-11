import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { GestionarComicService } from 'src/app/semillero/servicios/gestionar-comic.service';

@Component({
  selector: 'app-comprar-comic',
  templateUrl: './comprar-comic.component.html',
  styleUrls: ['./comprar-comic.component.css']
})
export class ComprarComicComponent implements OnInit {

  public comprarComicForm : FormGroup;

  public comicDTO : ComicDTO;

  public mostrarItem : boolean;

  public submitted : boolean;

  public mensajeEjecucion : string;

  public listaComics : Array<ComicDTO>;
  
  constructor(private fb : FormBuilder, private router : Router, private gestionComicsService : GestionarComicService) {
    this.comprarComicForm = this.fb.group({
      nombre : [null, Validators.required],
      cantidad : [null, Validators.required],
    });
  }

  ngOnInit() {
    this.submitted = false;
    this.mostrarItem = false;
    this.comicDTO = new ComicDTO();
    this.listaComics = new Array<ComicDTO>();
  }

  public irAGestionComic(comic : ComicDTO) : void {
    this.router.navigate(['gestionar-comic', comic]);
  }
}
