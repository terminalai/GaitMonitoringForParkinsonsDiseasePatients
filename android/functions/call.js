const ml = require("./ml.js")

function call(data) {
    const freezeY = data.freezeY
    const freezeZ = data.freezeZ

    return {
        freeze: ml(freezeY, freezeZ)
    };
}

exports = call