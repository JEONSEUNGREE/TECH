"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var NamooSorter_1 = __importDefault(require("./NamooSorter"));
var sorter = new NamooSorter_1.default([10, -7, 22, 3, 15]);
console.log(sorter.sort());
var sorterStr = new NamooSorter_1.default("typscript");
console.log(sorterStr.sort());
// let data : string | number [];
//                                             // data is string에서 is는 타입가드하는부분
// function isString(data: string | number[]): data is string{
//             // 타입 단언 string타입으로
//     return (<string>data).split !== undefined;
// }
// data = "TypeScript";
// if (isString(data)) {
//     data.split('');
// }else {
//     // number []
// }
