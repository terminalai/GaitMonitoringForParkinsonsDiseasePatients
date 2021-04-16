function _respond_convo (res, next, status, data, http_code){
	var response = {
		'status': status,
		'response': data
	};
	res.setHeader('content-type', 'application/json');
	res.writeHead(http_code);
	res.end(JSON.stringify(response));
	return next();
}

module.exports.success_convo = function(res, next, data){
	_respond_convo(res, next, 'success', data, 200);
}

module.exports.failure_convo = function(res, next, data, http_code){
	_respond_convo(res, next, 'failure', data, http_code);
}
