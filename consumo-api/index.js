const morgan = require("morgan");
const express = require("express");
const fetch = require("cross-fetch")
const cors = require('cors'); 
const app = express();

/**
 * CONFIGURACIONES
 */
app.set("name", "rest-api-contacts");
app.set("port", process.env.port || 8080);

/**
 * MIDDLEWARE
 */
app.use(express.json());
app.use(morgan("dev"));
app.use(cors());
app.options('/', (req, res)  => {
    res.setHeader("Access-Control-Allow-Origin", "*"); 
    res.setHeader('Access-Control-Allow-Methods', '*'); 
    res.setHeader("Access-Control-Allow-Headers", "*"); 
    res.end(); }); 

/**
 * LLAMADO DE RUTAS
 */
app.use(express.static("public"));

app.post('/', (req, res) => { 
    console.log("Success"); }); 

/**
 * Configuracion del puerto en el que se ejecuta la api
 */
app.listen(app.get("port"), () => {
  console.log(`listening at http://localhost:${app.get("port")}`);
  console.log("Nombre de la aplicación:", app.get("name"));
});