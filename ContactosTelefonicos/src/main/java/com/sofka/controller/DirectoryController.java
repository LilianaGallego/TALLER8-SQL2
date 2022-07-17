package com.sofka.controller;

import com.sofka.domain.ContactDomain;
import com.sofka.domain.PhoneDomain;
import com.sofka.service.DirectoryService;
import com.sofka.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controlador para el directorio de contactos
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class DirectoryController {

    /**
     * Servicio para el manejo del directorio telefonico
     */
    @Autowired
    private DirectoryService directoryService;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();

    /**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz, API del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz, API del sistema y versión, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Index del sistema, responde con el listado de contactos y sus teléfonos
     *
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.data = directoryService.getList();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Devuelve todos los contactos con sus teléfonos
     *
     * @return Objeto lista ce contactos Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @GetMapping(path="/api/v1/contacts")
    public ResponseEntity<Response> getListContacts(){
        response.restart();
        try {
            response.data = directoryService.getList();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }
    /**
     * Devuelve todos los contactos con sus teléfonos ordenados por su nombre completo de forma ascendente o descendente
     *
     * @param orderBy Nombre del campo por donde se desea ordenar la información
     * @param order Tipo de orden que debe tener la información ASC o DESC
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/api/v1/index/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> indexOrderBy(
            @PathVariable(value="orderBy") String orderBy,
            @PathVariable(value="order") Sort.Direction order
    ) {
        response.restart();
        try {
            response.data = directoryService.getList(orderBy, order);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Devuelve el listado de contactos y sus teléfonos basados en su nombre completo
     *
     * @param dataToSearch Información a buscar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @GetMapping(path = "/api/v1/search/contact/{dataToSearch}")
    public ResponseEntity<Response> searchContactByFullName(
            @PathVariable(value="dataToSearch") String dataToSearch
    ) {
        response.restart();
        try {
            response.data = directoryService.searchContact(dataToSearch);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea un nuevo contacto en el sistema
     *
     * @param contact Objeto Contacto a crear
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PostMapping(path = "/api/v1/contact")
    public ResponseEntity<Response> createContact(@RequestBody ContactDomain contact) {
        response.restart();
        try {
            log.info("Contacto a crear: {}", contact);
            response.data = directoryService.createContact(contact);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea un nuevo número de teléfono en el sistema
     *
     * @param phone Objeto Telefono a crear
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PostMapping(path = "/api/v1/phone")
    public ResponseEntity<Response> createPhone(@RequestBody PhoneDomain phone) {
        response.restart();
        try {
            log.info("Telefono a crear: {}", phone);
            response.data = directoryService.createPhone(phone);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Devuelve todos los contactos con sus teléfonos
     *
     * @return Objeto lista de telefonos Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @GetMapping(path="/api/v1/phones")
    public ResponseEntity<Response> getListPhones(){
        response.restart();
        try {
            response.data = directoryService.getListPhones();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de un contacto
     *
     * @param contact Objeto contacto a actualizar
     * @param id Identificador del contacto a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PutMapping(path = "/api/v1/contacts/{id}")
    public ResponseEntity<Response> updateContact(
            @RequestBody ContactDomain contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = directoryService.updateContact(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de un número de teléfono
     *
     * @param phone Objeto telefono a actualizar
     * @param id Identificador del número de teléfono a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PutMapping(path = "/api/v1/phone/{id}")
    public ResponseEntity<Response> updatePhone(
            @RequestBody PhoneDomain phone,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = directoryService.updatePhone(id, phone);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza el nombre completo de un contacto basado en su identificador
     *
     * @param contact Objeto Contacto
     * @param id Identificador del contacto a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PatchMapping(path = "/api/v1/contact/{id}/fullName")
    public ResponseEntity<Response> updateFullName(
            @RequestBody ContactDomain contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = directoryService.updateFullName(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza el correo electronico de un contacto basado en su identificador
     *
     * @param contact Objeto Contacto
     * @param id Identificador del contacto a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PatchMapping(path = "/api/v1/contact/{id}/email")
    public ResponseEntity<Response> updateEmail(
            @RequestBody ContactDomain contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = directoryService.updateEmail(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     * Actualiza la fecha de nacimiento de un contacto basado en su identificador
     *
     * @param contact Objeto Contacto
     * @param id Identificador del contacto a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PatchMapping(path = "/api/v1/contact/{id}/birthday")
    public ResponseEntity<Response> updateBirthday(
            @RequestBody ContactDomain contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = directoryService.updateBirthday(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza el número de teléfono basado en su identificador
     *
     * @param phone Objeto Contacto
     * @param id Identificador del número de teléfono a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @PatchMapping(path = "/api/v1/phone/{id}/number")
    public ResponseEntity<Response> updateOnlyNumberPhone(
            @RequestBody PhoneDomain phone,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = directoryService.updateOnlyNumberPhone(id, phone);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra un contacto del sistema
     *
     * @param id Identificador del contacto a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @DeleteMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> deleteContact(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = directoryService.deleteContact(id);
            if (response.data == null) {
                response.message = "El contacto no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El contacto fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra un teléfono del sistema
     *
     * @param id Identificador del teléfono a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @CrossOrigin(origins = "http://localhost:8080/")
    @DeleteMapping(path = "/api/v1/phone/{id}")
    public ResponseEntity<Response> deletePhone(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = directoryService.deletePhone(id);
            if (response.data == null) {
                response.message = "El telefono no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El telefono fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para redireccionar al controllador /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse para el manejo de la redirección
     * @return Objeto Response en formato JSON
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/api/v1/index");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}