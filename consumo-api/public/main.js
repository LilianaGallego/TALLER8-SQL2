/**
 * Variable que captura el id del contacto
 */
const inputId = document.querySelector("#id");
/**
 * Variable que captura la entrada del nombre completo del contacto
 */
const inputName = document.querySelector("#fullName");
/**
 * Variable que captura la entrada del telefono del contacto
 */
const inputPhone = document.querySelector("#phone");
/**
 * Variable que captura la entrada del correo electronico del contacto
 */
const inputEmail = document.querySelector("#email");
/**
 * Variable que captura la entrada de la fecha de nacimiento del contacto
 */
const inputBirthday = document.querySelector("#birthday");

/**
 * boton para crear un contacto
 */
const btnCreateContact = document.querySelector("#btnCreateContact");

/**
 * boton para editar un contacto
 */
const btnEdit = document.querySelector("#btnEdit");
/**
 * boton para eliminar un contacto
 */
//const btnDeleteContact = document.querySelector("#btnDeleteContact");

/**
 * variable para llenar la tabla de contactos
 */
const tbody = document.querySelector("#tbody").content;

window.addEventListener("DOMContentLoaded", () => {
  orderContacts();
});

/**
 * funcion para mostrar la informacion de los contactos obtenidos
 * de la peticion GET en una tabla en el html
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const showContacts = (contacts) => {
  let body = "";

  contacts.forEach((contact) => {
    
    let id = contact.id
    console.log(id)
    
    let listPhones = contact.phones 
    let phone = '';
    for (let i = 0; i < listPhones.length; i++) {
      phone += listPhones[i].number + '-';
      
    }
    body += `<tr >
          <td>${contact.id}</td>
          <td>${contact.fullName}</td>
          
          <td>${phone}</td>
          <td>${contact.email}</td>
          <td>${contact.birthday}</td>   
          <td>
            <button type="button" class="btn btn-primary" id="btnEditContact"  onclick="getContactById(${
              contact.id
            })">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
              </svg>
            </button>
          </td>   
          <td>
            <button class="btn btn-danger" id="btnDeleteContact" onclick="deleteContact(${
              contact.id
            })">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
              </svg>
            </button>
          </td>   

              </tr>`;
  });

  document.getElementById("tbody").innerHTML = body;
};

/**
 * Se agrega el evento click al boton crear contacto
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
btnCreateContact.addEventListener("click", async (e) => {
  e.preventDefault();

  if (
    inputName.value == 0 &&
    inputEmail.value == "" &&
    inputBirthday.value == "" &&
    inputPhone.value == ""
  ) {
    const msg = "*Debes llenar todos los campos";
    document.querySelector("#msgAdd").innerHTML = msg;
  } else if (
    !inputName.value == 0 &&
    !inputEmail.value == 0 &&
    !inputBirthday.value == 0 &&
    !inputPhone.value == 0

  ) {
    document.querySelector("#msgAdd").innerHTML = "";
    /**
     * variable para capturar el valor de la entrada del nombre completo
     */
    const fullName = inputName.value;

    /**
     * variable para capturar el valor de la entrada del correo electronico
     */
    const email = inputEmail.value;

    /**
     * variable para capturar el valor de la entrada del correo electronico
     */
    const number = inputPhone.value;
    /**
     * variable para capturar el valor de la entrada de la fecha de nacimiento
     */
    const birthday = inputBirthday.value;

    document.querySelector("#msgAdd").value = "";
    await fetch("http://localhost:9090/api/v1/contact", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ fullName, email, birthday }),
    })
    .then((res) => res.json())
    .catch((error) => {
      alertManager("error", error);
    })
    .then((data) => {
      const message = "Contacto agregado";
      alertManager("success", message);
    });
    createPhone();
    reset();
    orderContacts();
  }
});

/**
 * Se ordena los contactos por el Id en forma ascendente
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const orderContacts = async () => {
  await fetch(`http://localhost:9090/api/v1/index/orderby/${"id"}/${"ASC"}`)
    .then((res) => {
      if (res.status >= 400) {
        throw new Error("Mala respuesta del servidor");
      }
      return res.json();
    })
    .catch((error) => {
      const message = "Ocurrio un problema al listar los contactos";
      alertManager("error", message);
    })
    .then((json) => {
      const contacts = json["data"];
      //listPhonesByContact(contacts)
      showContacts(contacts);
    })
    .catch((err) => {
      console.error(err);
    });
};

/**
 * Trae los datos del contacto a actualizar al formulario
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const getContactById = async (id) => {
  btnCreateContact.style.display = "none";
  btnEdit.type = "button";
  btnEdit.style.display = "block";

  await fetch(`http://localhost:9090/api/v1/contacts`)
    .then((res) => {
      if (res.status >= 400) {
        throw new Error("Mala respuesta del servidor");
      }
      return res.json();
    })
    .catch((error) => {
      const message = "Ocurrio un problema al listar los contactos";
      alertManager("error", message);
    })
    .then((json) => {
      const contacts = json["data"];
      contacts.forEach((contact) => {
        if (contact.id == id) {
          document.getElementById("id").value = contact.id;
          document.getElementById("fullName").value = contact.fullName;
          const phones = contact.phones;

          let phone = "";
          for (let i = 0; i < phones.length; i++) {
            phone += phones[i].number + "-";
          }
          document.getElementById("phone").value = phone;
          document.querySelector("#email").value = contact.email;
          document.querySelector("#birthday").value = contact.birthday;
          let idContact = contact.id;

          /**
           * se agrega el evento click a btnEdit para actualizar el contacto
           */
          btnEdit.addEventListener("click", async (e) => {
            e.preventDefault();

            /**
             * variable para capturar el valor de la entrada del nombre completo
             */
            const fullName = inputName.value;

            /**
             * variable para capturar el valor de la entrada del correo electronico
             */
            const email = inputEmail.value;

            /**
             * variable para capturar el valor de la entrada del correo electronico
             */
            const number= inputPhone.value;
            /**
             * variable para capturar el valor de la entrada de la fecha de nacimiento
             */
            const birthday = inputBirthday.value;

            updatePhone(number, phone, contact);
            document.querySelector("#msgAdd").value = "";
            await fetch(`http://localhost:9090/api/v1/contacts/${idContact}`, {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({ fullName, email, birthday }),
            })
              .then((res) => res.json())
              .catch((error) => {
                alertManager("error", error);
              })
              .then((data) => {
                const message = "Contacto actualizado";
                alertManager("success", message);
                btnCreateContact.style.display = "block";
                btnEdit.type = "hidden";
                btnEdit.style.display = "none";
              });
            
            orderContacts();
            reset();
          });
        }
      });
    })
    .catch((err) => {
      console.error(err);
    });
};

/**
 * Se elimina un contacto
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const deleteContact = async (id) => {
  await fetch(`http://localhost:9090/api/v1/contact/${id}`, {
    method: "DELETE",
  })
    .then((res) => {
      const message = "Contacto eliminado";
      alertManager("success", message);
    })
    .catch((error) => {
      alertManager("error", error);
    });

  orderContacts();
};

/**
 * crea un telefono
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const createPhone = async () => {
  /**
   * variable para capturar el valor de la entrada del numero de telefono
   */
  const number = inputPhone.value;

  console.log(number);
  await fetch(`http://localhost:9090/api/v1/index/orderby/${"id"}/${"ASC"}`)
    .then((res) => {
      if (res.status >= 400) {
        throw new Error("Mala respuesta del servidor");
      }
      return res.json();
    })
    .then((json) => {
      const contacts = json["data"];
      for (let i = 0; i < contacts.length; i++) {
        if (i == contacts.length - 1) {
          const contact = contacts[i];
          console.log(contact);

          fetch("http://localhost:9090/api/v1/phone", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ number, contact }),
          })
            .then((res) => res.json())
            .catch((error) => {
              alertManager("error", error);
            })
            .then((data) => {
              console.log(data);
            });
        }
      }
    })
    .catch((err) => {
      console.error(err);
    });
};

/**
 * Actualiza el numero de telefono
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const updatePhone = async (number, phoneNumber, contact) =>{
  console.log(number);
  await fetch('http://localhost:9090/api/v1/phones')
  .then((res) => {
    if (res.status >= 400) {
      throw new Error("Mala respuesta del servidor");
    }
    return res.json();
  })
  .catch((error) => {
    console.log("Ocurrio un problema al listar los telefonos");
  })
  .then((json) => {
    const phones = json["data"];
    phones.forEach(p => {
      console.log(p.number);
      thisPhone = p.number + '-';

      if (phoneNumber == thisPhone){
        let id = p.id;
        console.log(id);
        fetch(`http://localhost:9090/api/v1/phone/${id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({number, contact}),
        })
        .then((res) => res.json())
        .then((data) => {
          orderContacts();
          console.log(data.data);
          console.log('telefono actualizado');
        });
      }
    });
  })
  .catch((err) => {
    console.error(err);
  });
}


/**
 * funcion para crear alertas
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const alertManager = (typeMsg, message) => {
  const alert = document.querySelector("#alert");
  alert.innerHTML = message || "Se produjo un cambio";
  alert.classList.add(typeMsg);
  alert.style.border = "dotted";
  alert.style.display = "block";

  setTimeout(() => {
    alert.style.display = "none";
    alert.style.border = "none";

    alert.classList.remove(typeMsg);
  }, 3500);
};

/**
 * funcion para limpiar los campos del formulario
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const reset = () => {
  document.getElementById("id").value = "";
  document.getElementById("fullName").value = "";
  document.getElementById("phone").value = "";
  document.querySelector("#email").value = "";
  document.querySelector("#birthday").value = "";
};
