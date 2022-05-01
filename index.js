const cors = require('cors');
const express = require('express');
const app = express();
const port = 8008;

app.use(cors());

app.get('/', (req, res) => {
    res.type('text/plain');
    res.send('Oneratus');
})

app.get('/api/1/client/jar', (req, res) => {
    res.status(403)
    res.send("Who Is This For Again?")
})

app.get('/api/1/client/jar/:username', (req, res) => {
    const name = req.params.username;
    const hwid = req.query.hwid;

    if (name && hwid == null){
        res.status(403)
        res.send("Hold on did your computer not tell us who it is?")
    }else{
        const file = `${__dirname}/api/1/client.jar`;
        res.download(file);
    }
})

app.listen(port, () => {
    console.log(`Webserver listening on ::${port}`)
});