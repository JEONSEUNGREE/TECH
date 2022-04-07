"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var NumberCollection = /** @class */ (function () {
    function NumberCollection(data) {
        this.data = data;
    }
    Object.defineProperty(NumberCollection.prototype, "length", {
        get: function () {
            return this.data.length;
        },
        enumerable: false,
        configurable: true
    });
    NumberCollection.prototype.compare = function (leftIndex, rightIndex) {
        return this.data[leftIndex] > this.data[rightIndex];
    };
    NumberCollection.prototype.swap = function (leftIndex, rightIndex) {
        var temp = this.data[leftIndex];
        this.data[leftIndex] = this.data[rightIndex];
        this.data[rightIndex] = temp;
    };
    return NumberCollection;
}());
exports.default = NumberCollection;
