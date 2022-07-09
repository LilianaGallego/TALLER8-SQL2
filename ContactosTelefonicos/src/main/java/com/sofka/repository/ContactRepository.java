package com.sofka.repository;

import com.sofka.domain.ContactDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para la entidad Contacto
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
public interface ContactRepository extends JpaRepository<ContactDomain, Integer> {

    /**
     * Busca los contactos que empiezan por el nombre completo
     *
     * @param data Dato a buscar
     * @return Listado de contactos encontrados
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT con " +
            "FROM ContactDomain con " +
            "WHERE (con.fullName LIKE :data% ) " +
            "ORDER BY con.fullName  ASC")
    public List<ContactDomain> findByFullNameStartingWith(@Param("data") String data);

    /**
     * Busca los contactos que contienen el nombre completo
     *
     * @param data Dato a buscar
     * @return Listado de contactos encontrados
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT con " +
            "FROM ContactDomain con " +
            "WHERE (con.fullName LIKE :data% ) " +
            "ORDER BY con.fullName  ASC")
    public List<ContactDomain> findByFullNameContains(@Param("data") String data);

    /**
     * Busca los contactos que finalizan por X dato tanto por nombre como por apellido
     *
     * @param data Dato a buscar
     * @return Listado de contactos encontrados
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT con " +
            "FROM ContactDomain con " +
            "WHERE (con.fullName LIKE :data% ) " +
            "ORDER BY con.fullName  ASC")
    public List<ContactDomain> findByFullNameEndingWith(@Param("data") String data);

    /**
     * Actualiza el nombre de un contacto basado en su identificador
     *
     * @param id Identificador del contacto
     * @param fullName Nuevo nombre del contacto
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update ContactDomain con set con.fullName = :fullName where con.id = :id")
    public void updateNombre(@Param(value = "id") Integer id, @Param(value = "fullName") String fullName);

     /**
     * Actualiza el correo electronico de un contacto basado en su identificador
     *
     * @param id Identificador del contacto
     * @param email Nuevo apellido del contacto
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update ContactDomain con set con.email = :email where con.id = :id")
    public void updateEmail(@Param(value = "id") Integer id, @Param(value = "email") String email);

    /**
     * Actualiza la fecha de nacimiento de un contacto basado en su identificador
     *
     * @param id Identificador del contacto
     * @param birthday Nuevo apellido del contacto
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update ContactDomain con set con.birthday = :birthday where con.id = :id")
    public void updateBirthday(@Param(value = "id") Integer id, @Param(value = "birthday") String birthday);
}