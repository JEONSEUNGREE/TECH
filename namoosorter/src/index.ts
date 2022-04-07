import NamooSorter from "./NamooSorter"


const sorter = new NamooSorter([10, -7, 22, 3, 15]);
console.log(sorter.sort());

const sorterStr = new NamooSorter("typscript");
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