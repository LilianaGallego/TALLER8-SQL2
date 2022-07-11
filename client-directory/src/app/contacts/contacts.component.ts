import { Component, OnInit } from '@angular/core';
import { Contact } from './contact';
import { ContactService } from './contact.service';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  title:string = "CONTACTOS TELEFONICOS";
  /**
   * lista de contactos inicializada
   */
  contacts: Array<Contact>;
  
  /**
   * Metodo constructor 
   * 
   * @param  contactService servicio del contacto para interactuar
   * con la apirest
   * 
   * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
   * @since 1.0.0
   */
  constructor(private contactService:ContactService) {
    this.contacts= new Array<Contact>()
  }

   /**
   * Metodo para hacer el llamado de los metodos del servicio
   * 
   * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
   * @since 1.0.0
   */
  ngOnInit(): void {

   /**
   * Se llama el metodo getAll del servicio contacto 
   */
    /* this.contactService.getAll().subscribe(
      c => this.contacts=c
    ) */

    this.contactService.getAll().subscribe((res) => {
      this.contacts = res;
      // si la longitud del arreglo en la posición 0 es 0 entonces está vacío
      if (!this.contacts.length) {
        // está vacío
      }
    });
  }

}
