import NamooSorter from './NamooSorter';
import NumberCollection from './NumberCollection';
import CharactersCollection from './CharatersCollection';
import CustomerCollection from './CustomerCollection';
import { Customer } from './Customer';

let numCollection = new NumberCollection([10, -7, 55, 3, 21]);
let CharactersCollection1 = new CharactersCollection("typescript");
let customerCollection = new CustomerCollection([
    new Customer("A111", "Kim"),
    new Customer("B111", "Park"),
    new Customer("C111", "Lee"),
    
]);

// let sorter = new NamooSorter(numCollection);

// let sorter2 = new NamooSorter(CharactersCollection1);

let sorter = new NamooSorter(customerCollection);
sorter.sort();
console.log(sorter.printCollection());


// sorter2.sort();

// console.log(sorter2.getCollection);

