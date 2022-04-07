"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var NamooSorter_1 = __importDefault(require("./NamooSorter"));
var NumberCollection_1 = __importDefault(require("./NumberCollection"));
var CharatersCollection_1 = __importDefault(require("./CharatersCollection"));
var CustomerCollection_1 = __importDefault(require("./CustomerCollection"));
var Customer_1 = require("./Customer");
var numCollection = new NumberCollection_1.default([10, -7, 55, 3, 21]);
var CharactersCollection1 = new CharatersCollection_1.default("typescript");
var customerCollection = new CustomerCollection_1.default([
    new Customer_1.Customer("A111", "Kim"),
    new Customer_1.Customer("B111", "Park"),
    new Customer_1.Customer("C111", "Lee"),
]);
// let sorter = new NamooSorter(numCollection);
// let sorter2 = new NamooSorter(CharactersCollection1);
var sorter = new NamooSorter_1.default(customerCollection);
sorter.sort();
console.log(sorter.printCollection());
// sorter2.sort();
// console.log(sorter2.getCollection);
