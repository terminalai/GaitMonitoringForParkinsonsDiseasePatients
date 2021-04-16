

module.exports = function (server) {
    server.post("/freeze", function(req, res, next) {
        var freezeY = req.params["freezeY"]
        var freezeZ = req.params["freezeZ"]

        var spawn = require('child_process').spawn;
        var py = spawn('python', [__dirname + '/model.py', user]);

        var classification = 0.0

        py.stdout.on("data", function(data) {
            classification = parseDouble(data.toString());
        })

        py.stderr.on("data", (data) => {
            console.log(data.toString());
            console.log("Error occurred!");
        });

        res.status(200).json({
            freeze: classification
        })
    })

}
