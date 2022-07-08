package com.sofka.dao;

import com.sofka.domain.ContactDomain;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactDao extends CrudRepository<ContactDomain, Long>{
    @Modifying
    @Query("update ContactDomain  con set con.fullName = :full_name where con.id = :id")
    public void updateName (
            @Param(value = "id")  Long id,
            @Param(value = "fullName") String full_name
    );

    @Modifying
    @Query("update ContactDomain  con set con.phone = :phone where con.id = :id")
    public void updatePhone (
            @Param(value = "id")  Long id,
            @Param(value = "phone") String phone
    );

    @Modifying
    @Query("update ContactDomain  con set con.email = :email where con.id = :id")
    public void updateEmail (
            @Param(value = "id")  Long id,
            @Param(value = "email") String email
    );

    @Modifying
    @Query("update ContactDomain  con set con.birthday = :birthday where con.id = :id")
    public void updateBirthday (
            @Param(value = "id")  Long id,
            @Param(value = "birthday") String birthday
    );
}
