"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// duck typing으로 구현하지않아도 같은 파라미터와 메서드가 존재한다면 구현없이 사용가능
var NamooSorter = /** @class */ (function () {
    function NamooSorter(collection) {
        this.collection = collection;
    }
    Object.defineProperty(NamooSorter.prototype, "getCollection", {
        get: function () {
            return this.collection;
        },
        enumerable: false,
        configurable: true
    });
    NamooSorter.prototype.printCollection = function () {
        console.log(this.collection);
    };
    NamooSorter.prototype.sort = function () {
        var length = this.collection.length;
        for (var i = 0; i < length; i++) {
            for (var j = 0; j < length - i; j++) {
                if (this.collection.compare(j, j + 1)) {
                    this.collection.swap(j, j + 1);
                }
            }
        }
    };
    return NamooSorter;
}());
exports.default = NamooSorter;
