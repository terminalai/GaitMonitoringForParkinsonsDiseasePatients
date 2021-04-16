fetch("https://gait-analyzer-1ac2c.web.app/freeze", {
    method: "POST",
    body: JSON.stringify({
        'freezeY': 10.0,
        'freezeZ': 11.0
    })
}).then(res => {
    console.log("Request complete! response:", res);
});