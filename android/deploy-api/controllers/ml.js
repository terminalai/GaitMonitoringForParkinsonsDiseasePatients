const child_process = require("child_process")

function ml(freezeY, freezeZ) {
    var spawn = child_process.spawn;
    var py = spawn('python', [__dirname + '/model.py', freezeY, freezeZ]);

    var classification = 0.0

    py.stdout.on("data", function (data) {
        classification = parseDouble(data.toString());
    })

    py.stderr.on("data", (data) => {
        console.log(data.toString());
        console.log("Error occurred!");
    });

    return classification
}

exports = ml