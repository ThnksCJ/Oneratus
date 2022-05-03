const cfg = require("../config");

module.exports = function (app) {
    app.get('/version', (req, res) => {
        res.type('text/plain');
        res.send(cfg.version);
    })
}