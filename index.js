const lookup = require('./function/lookup.js');
const cfg = require('./config.js')

const cors = require('cors');
const express = require('express');
const app = express();
const port = cfg.port;

app.use(cors());

app.get('/', (req, res) => {
    res.type('text/plain');
    res.send('Oneratus');
})

app.get('/version', (req, res) => {
    res.type('text/plain');
    res.send(cfg.version);
})

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

app.get('/api/1/installer/jar', (req, res) => {
    res.status(403)
    res.type('text/plain');
    res.send("Who Is This For Again?")
})

app.get('/api/1/client/installer/:username', (req, res) => {
    const name = req.params.username;

    if (name == null){
        res.status(403)
        res.type('text/plain');
        res.send("Who Is This For Again?")
    }else{
        res.download(cfg.installerpath);
    }
})

app.listen(port, () => {
    console.log(`Webserver listening on :${port}`)
});