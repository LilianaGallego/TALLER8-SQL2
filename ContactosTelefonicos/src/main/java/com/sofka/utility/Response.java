package com.sofka.utility;

/**
 * Clase para el manejo de las respuestas de las API
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
public class Response {

    /**
     * Indica si existe un error o no en la respuesta del API
     */
    public Boolean error;

    /**
     * Mensaje del API cuando es utilizada
     */
    public String message;

    /**
     * Información del API cuando es necesario
     */
    public Object data;

    /**
     * Constructor de la clase
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public Response() {
        error = false;
        message = "";
        data = null;
    }

    /**
     * Restaura a ceros la respuesta del API
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public void restart() {
        error = false;
        message = "";
        data = null;
    }
}