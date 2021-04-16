
var helpers = require('../config/helperFunctions.js');

module.exports = function(server){
	server.post("/convo", function(req, res, next){
		req.assert('user_response', 'user_response is required').notEmpty();
		var error = req.validationErrors();
		if(error){
			helpers.failure_convo(res, next, error[0], 400);
		}
		var user = req.params['user_response'];

		var spawn = require('child_process').spawn;
		var py = spawn('python', [__dirname+'/bot.py', user]);

		var bot_reply = '';

		py.stdout.on('data', function(data){
		  bot_reply += data.toString();
			helpers.success_convo(res, next, bot_reply);
		});

		py.stderr.on('data', (data) => {
				console.log(data.toString());
				console.log("Error occured!");
				helpers.failure_convo(res, next, "Error occured! Try again in a few hours!");
    });


		py.stdin.end();
	});

	server.post("/diagnose", function(req, res, next){
		req.assert('url', 'url is required').notEmpty()
		var error = req.validationErrors();
		if(error){
			helpers.failure_convo(res, next, error[0], "error", 400);
		}
		var url = req.params.url;

		var spawn = require('child_process').spawn;
		var py = spawn('python', [__dirname+'/web_detect.py', url]);


		py.stdout.on('data', function(data){
		  //var diagnosis += data;
			console.log(data.toString());
			var diagnosis = JSON.parse(data.toString());
			helpers.success_convo(res, next, diagnosis);
		});

		py.stderr.on('data', (data) => {
				console.log(data.toString());
				console.log("Error occured!");
				helpers.failure_convo(res, next, "Error occured! Try again in a few hours!");
    });

		py.stdin.end();
	});

}
