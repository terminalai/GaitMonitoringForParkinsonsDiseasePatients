const ml = require("./ml.js")

module.exports = function(server){
	server.post("/", function (req, res, next) {
		var freezeY = req.params["freezeY"]
		var freezeZ = req.params["freezeZ"]

		res.status(200).json({
			freeze: ml(freezeY, freezeZ)
		})
	})
}
