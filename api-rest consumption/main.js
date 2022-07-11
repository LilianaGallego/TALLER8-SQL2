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
 * boton para retornar la lista de contactos
 */
const btnSearchContacts = document.querySelector("#btnSearchContact");
/**
 * boton para editar un contacto
 */
const btnEditContact = document.querySelector("#btnEditContact");
/**
 * boton para eliminar un contacto
 */
const btnDeleteContact = document.querySelector("#btnDeleteContact");
/**
 * variable para capturar el valor de la entrada del nombre completo
 */
const fullName = inputName.value;
/**
 * variable para capturar el valor de la entrada del numero de telefono
 */
const number = inputPhone.value;
/**
 * variable para capturar el valor de la entrada del correo electronico
 */
const email = inputEmail.value;
/**
 * variable para capturar el valor de la entrada de la fecha de nacimiento
 */
const birthday = inputBirthday.value;

/**
 * Llama el metodo GET para obtener la lista de contactos
 *
 * @param url url de la peticion GET
 * 
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
fetch("http://localhost:8080/api/v1/contacts")
  .then((response) => {
    response.json();
  })
  .then((data) => {
    const str = data;
    console.log(str);
  })
  .catch((error) => console.log(error));

/**
 * Se agrega el evento click al boton crear contacto
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
btnCreateContact.addEventListener("click", async (e) => {
  e.preventDefault();

  await fetch("http://localhost:8080/api/v1/contact", {
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({ fullName, email, birthday }),
  }).catch((error) => console.log(error));

  await fetch("http://localhost:8080/api/v1/contacts")
    .then((response) => {
      console.log(response.id);
    })
    .then(async (data) => {
      var contact = data;
      console.log(contact);
      fetch("http://localhost:8080/api/v1/phone", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ number, contact }),
      }).catch((error) => console.log(error));
      alert("telefono creado");
    })
    .catch((error) => console.log(error));
});

/**
 * Se agrega el evento click al boton buscar contactos
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
btnSearchContacts.addEventListener("click", async (e) => {
  e.preventDefault();
  await fetch("http://localhost:8080/api/v1/index")
    .then((response) => showData(response))
    .then((data) => console.log(data[2]))
    .catch((error) => console.log(error));
  console.log("buscando");
});


/**
 * funcion mostrar data para pasar la informacion del objeto JSON a una 
 * tabla en el html
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
const showData = async (data) => {
  let body = "";
  console.log(data2);
  for (let i = 0; i < data2.length; i++) {
    body += `<tr>
                <td>${datai.id}</td>
                <td>${datai.fullName}</td>
                <td>${datai.email}</td>
                <td>${datai.birthday}</td>                
            </tr>`;
  }
  document.getElementById("tbody").innerHTML = body;
};

/**
 * Se agrega el evento click al boton editar contacto
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
 btnEditContact.addEventListener("click", async (e) => {
    e.preventDefault();
    await fetch(`http://localhost:8080/api/v1/contact/${id}`,{
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({ fullName, email, birthday }),
  }).catch((error) => console.log(error));
 });
/**
 * Se agrega el evento click al boton eliminar contacto
 *
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
 btnDeleteContact.addEventListener("click", async (e) => {
    e.preventDefault();
    await fetch(`http://localhost:8080/api/v1/contact/${id}`)
      .then((response) => response.json)
      .then((data) => console.log(data))
      .catch((error) => console.log(error))
    
  })