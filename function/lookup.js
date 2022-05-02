

module.exports = function lookup(name, hwid) {
    if (name === "ThnksCJ" && hwid === "123"){
        return "Valid";
    }else{
        return "Invalid";
    }
}