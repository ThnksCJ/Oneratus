const cfg = require("../../../config");
const lookup = require("../../../../function/lookup");

module.exports = function (app) {
    app.get('/api/1/client/jar', (req, res) => {
        res.status(403)
        res.type('text/plain');
        res.send("Who Is This For Again?")
    })

    app.get('/api/1/client/jar/:username', (req, res) => {
        const name = req.params.username;
        const hwid = req.query.hwid;

        if (name && hwid == null){
            res.status(403)
            res.type('text/plain');
            res.send("Hold on did your computer not tell us who it is?")
        }else{
            if (lookup(name, hwid) === "Valid"){
                res.download(cfg.clientpath);
            }else{
                res.send("Error Invalid User")
            }
        }
    })
}