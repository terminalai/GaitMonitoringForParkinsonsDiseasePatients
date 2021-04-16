const functions = require("firebase-functions")
const app = require("./app.js")
const call = require("./call.js")

exports.freeze = functions.https.onRequest(app)

exports.call = functions.https.onCall(call)
