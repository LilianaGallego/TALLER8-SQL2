import { Contact } from "./contact";

/**
 * Clase telefono
 *
 * @version 1.0.0 2022-07-11
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
export class Phone {
    /**
     * Número de teléfono
     */
    id: number = 0;
    /**
     * Número de teléfono
     */
    number: string = "";

    contact: Contact = new Contact();
}
