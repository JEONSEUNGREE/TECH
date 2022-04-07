import { Customer } from './Customer';

class CustomerCollection {
    
    constructor(private data : Customer []) {
    }

    get length() : number {
        return this.data.length;
    }

    compare(leftIndex : number , rightIndex : number) : boolean {
        // localeCompare 메서드를 이용하면 기준이 되는 문자열에 비교가 되는 문자열에 앞뒤에따라서 -1, 0, 1 로 반화한다.
        return this.data[leftIndex].name.localeCompare(this.data[rightIndex].name) > 0;
    }

    swap(leftIndex : number, rightIndex : number) : void {
        const temp = this.data[leftIndex];
        this.data[leftIndex] = this.data[rightIndex]
        this.data[rightIndex] = temp;
    }
}

export default CustomerCollection;