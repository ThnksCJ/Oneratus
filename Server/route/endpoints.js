module.exports = function (app) {
    app.get('/endpoint', (req, res) => {
        res.type('text/plain');
        res.send('{"/api/1/installer", "/api/1/client", "/endpoints", "/version", "/settings"}');
    });

    app.post('/endpoint', (req, res) => {
        res.send('{"/api/1/installer", "/api/1/client", "/endpoints", "/version", "/settings"}');
    });
}

