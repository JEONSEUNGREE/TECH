"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var NamooSorter = /** @class */ (function () {
    function NamooSorter(collection) {
        this.collection = collection;
    }
    NamooSorter.prototype.sort = function () {
        var length = this.collection.length;
        for (var i = 0; i < length; i++) {
            for (var j = 0; j < length - i; j++) {
                if (typeof this.collection === "string") {
                    if (this.collection[j].toLowerCase() > this.collection[j + 1].toLowerCase()) {
                        var characters = this.collection.split('');
                        var temp = characters[j];
                        characters[j] = characters[j + 1];
                        characters[j + 1] = temp;
                        this.collection = characters.join("");
                    }
                }
                else {
                    if (this.collection[j] > this.collection[j + 1]) {
                        var temp = this.collection[j];
                        this.collection[j] = this.collection[j + 1];
                        this.collection[j + 1] = temp;
                    }
                }
                // if(this.collection[j] > this.collection[j+1]) {
                //     // swap
                //     const temp = this.numbers[j];
                //     this.collection[j] = this.collection[j+1];
                //     this.collection[j+1] = temp
                // }
            }
        }
        return this.collection;
    };
    return NamooSorter;
}());
exports.default = NamooSorter;
