var config = module.exports = {};

config.port = '8008';
config.version = require('./package.json').version;
config.installerpath = `${__dirname}/api/1/installer.jar`;
config.clientpath = `${__dirname}/api/1/client.jar`;