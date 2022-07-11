import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contact } from './contact';

@Injectable({
  providedIn: 'root'
})
/**
 * Servicio del contacto para establecer la conexion con la api de spring boot
 *
 * @version 1.0.0 2022-07-11
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
export class ContactService {
  /**
   * variable url para obtener la lista de contactos
   */
  private url:string = "http://localhost:8080/api/v1/contacts";

  /**
   * Metodo constructor
   * @param http libreria de angular tipo HttpClient para enviar
   * obtener, actualizar y eliminar informacion
   * 
   * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
   * @since 1.0.0
   */
  constructor(private http:HttpClient) { }

  
  /**
   * Metodo para obtener la lista de contactos de la apiRet
   * 
   * @return lista de contactos
   * 
   * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
   * @since 1.0.0
   */
  getAll():Observable<Contact[]>{
    return this.http.get<Contact[]>(this.url);
  }

  /**
   * Metodo para crear un contacto
   * 
   * @return contacto creado
   * 
   * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
   * @since 1.0.0
   */
  create(contact: Contact):Observable<Contact>{
    return this.http.post<Contact>(this.url, contact);
  }


/**
 * Metodo para obtener un contacto de la apiRet
 * 
 * @return contacto
 * 
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
  get(id:number):Observable<Contact>{
  return this.http.get<Contact>(this.url+'/'+id);
  }

  /**
   * Metodo para actualizar un contacto
   * 
   * @return contacto actualizado
   * 
   * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
   * @since 1.0.0
   */
  update(contact: Contact):Observable<Contact>{
    return this.http.put<Contact>(this.url, contact);
  }
  
  /**
 * Metodo para eliminar un contacto de la apiRet
 * 
 * @return contacto
 * 
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
  delete(id:number):Observable<Contact>{
    return this.http.delete<Contact>(this.url+'/'+id);
  }
}
