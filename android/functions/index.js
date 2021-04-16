const functions = require("firebase-functions");
const express = require("express");


const app = express();
app.get("/timestamp", (request, response) => {
    functions.logger.info("My first actual function yay!", { structuredData: true });
    response.send(`${Date.now()}`);
})

app.get("/timestamp-cached", (request, response) => {
    functions.logger.info("My first actual function yay!", { structuredData: true });
    response.set("Cache-Control", "public, max-age=300, s-maxage=600");
    response.send(`${Date.now()}`);
})

app.post("/freeze")

exports.app = functions.https.onRequest(app)