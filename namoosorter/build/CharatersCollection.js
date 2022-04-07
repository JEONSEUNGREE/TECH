"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var CharacterCollection = /** @class */ (function () {
    function CharacterCollection(data) {
        this.data = data;
    }
    Object.defineProperty(CharacterCollection.prototype, "length", {
        get: function () {
            return this.data.length;
        },
        enumerable: false,
        configurable: true
    });
    CharacterCollection.prototype.compare = function (leftIndex, rightIndex) {
        return this.data[leftIndex] > this.data[rightIndex];
    };
    CharacterCollection.prototype.swap = function (leftIndex, rightIndex) {
        var characters = this.data.split("");
        var temp = characters[leftIndex];
        characters[leftIndex] = characters[rightIndex];
        characters[rightIndex] = temp;
        this.data = characters.join("");
    };
    return CharacterCollection;
}());
exports.default = CharacterCollection;
