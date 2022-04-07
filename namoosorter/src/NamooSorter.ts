import NumbersCollection from './NumberCollection';

interface Sortable {
    length : number;
    compare(leftIndex : number, rightIndex : number) : boolean;
    swap(leftIndex : number, rightIndex : number ) : void;
}
// duck typing으로 구현하지않아도 같은 파라미터와 메서드가 존재한다면 구현없이 사용가능
class NamooSorter {

    constructor(private collection : Sortable) {
}
    get getCollection() {
        return this.collection;
    }

    printCollection(){
        console.log(this.collection)
    }


    sort() : void {
        const length = this.collection.length;
        for (let i = 0; i < length; i++) {
            for (let j = 0; j < length - i; j++) {
                if(this.collection.compare(j, j+1)) {
                    this.collection.swap(j, j+1);
                }
            }
        }
    }
}

export default NamooSorter;