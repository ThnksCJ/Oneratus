const cfg = require('../../../config.js')

module.exports = function (app) {
    app.get('/api/1/installer/jar', (req, res) => {
        res.status(403)
        res.type('text/plain');
        res.send("Who Is This For Again?")
    })

    app.get('/api/1/installer/:username', (req, res) => {
        const name = req.params.username;

        if (name == null){
            res.status(403)
            res.type('text/plain');
            res.send("Who Is This For Again?")
        }else{
            res.download(cfg.installerpath);
        }
    })
}