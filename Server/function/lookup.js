const AllowedUsers = require('../../data/dataHandler.js')

module.exports = function lookup(name, hwid) {
    const obj1 = {"username": name, "hwid": hwid};

    for (var i=0; i<AllowedUsers.length; i++) {
        if (JSON.stringify(AllowedUsers[i]) === JSON.stringify(obj1) ) {
            return "Valid";
        }else{
            return "Invalid";
        }
    }
}