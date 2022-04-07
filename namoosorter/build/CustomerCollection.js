"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var CustomerCollection = /** @class */ (function () {
    function CustomerCollection(data) {
        this.data = data;
    }
    Object.defineProperty(CustomerCollection.prototype, "length", {
        get: function () {
            return this.data.length;
        },
        enumerable: false,
        configurable: true
    });
    CustomerCollection.prototype.compare = function (leftIndex, rightIndex) {
        // localeCompare 메서드를 이용하면 기준이 되는 문자열에 비교가 되는 문자열에 앞뒤에따라서 -1, 0, 1 로 반화한다.
        return this.data[leftIndex].name.localeCompare(this.data[rightIndex].name) > 0;
    };
    CustomerCollection.prototype.swap = function (leftIndex, rightIndex) {
        var temp = this.data[leftIndex];
        this.data[leftIndex] = this.data[rightIndex];
        this.data[rightIndex] = temp;
    };
    return CustomerCollection;
}());
exports.default = CustomerCollection;
