const lookup = require("../function/lookup");

module.exports = function (app) {
    app.get('/settings', (req, res) => {
        res.status(403)
        res.type('text/plain');
        res.send("No hwid AND No user")
    })

    app.get('/settings/:username', (req, res) => {
        const name = req.params.username;
        const hwid = req.query.hwid;

        if(hwid == null) {
            res.status(403)
            res.type('text/plain');
            res.send("No hwid")
        }

        if(name == null) {
            res.status(403)
            res.type('text/plain');
            res.send("No user")
        }

        if(name && hwid == null) {
            res.status(403)
            res.type('text/plain');
            res.send("No hwid AND No user")
        }else{
            res.type('text/plain');
            if (lookup(name, hwid) === "Valid"){
                res.send("Present In Database")
            }else{
                res.send("User Is Not In The Database")
            }
        }
    })
}