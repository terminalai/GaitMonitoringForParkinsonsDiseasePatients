const ml = require("./ml.js")

const express = require("express")

const app = express();

app.post("/", function (req, res, next) {
    var freezeY = req.params["freezeY"]
    var freezeZ = req.params["freezeZ"]

    res.status(200).json({
        freeze: ml(freezeY, freezeZ)
    })
})

exports = app