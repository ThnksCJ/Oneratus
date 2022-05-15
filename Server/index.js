const cfg = require('./config.js')
const log = require("@thnkscj/logger-plus");

const cors = require('cors');
const express = require('express');
const app = express();
const port = cfg.port;

app.use(cors());
app.disable('x-powered-by');
app.use((req, res, next) => {
    res.append('x-made-by', 'ThnksCJ');
    next();
});

require('./route/api/1/installer.js')(app);
require('./route/api/1/client.js')(app);
require('./route/endpoints.js')(app);
require('./route/version.js')(app);
require('./route/settings.js')(app);

app.get('/', (req, res) => {
    res.type('text/plain');
    res.send('Oneratus');
})

app.listen(port, () => {
    log.CLEAR()
    log.INFO(`Webserver listening on :${port}`);
});