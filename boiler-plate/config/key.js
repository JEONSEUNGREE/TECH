if(process.env.NODE_ENV === "productiohn") {
    module.exports = require("./prod");
} else {
    module.exports = require("./dev");
}