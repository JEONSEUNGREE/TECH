class NamooSorter {

    constructor(private collection : number[] | string) {
}


    sort() : number[] | string{

        const { length } = this.collection;

        for (let i = 0; i < length; i++) {
            for (let j = 0; j < length - i; j++) {
                if(typeof this.collection === "string") {
                    if(this.collection[j].toLowerCase() > this.collection[j+1].toLowerCase()) {
                        const characters = this.collection.split('');
                        const temp = characters[j]
                        characters[j] = characters[j+1];
                        characters[j+1] = temp;
                        this.collection = characters.join("");
                    }
                } else {
                    // if(typeof this.collection === "number") //옆에 코드는 걸리지않는다 이유는 숫자배열을 전달받기때문에
                    if(this.collection instanceof Array) { // 배열 여부 확인
                        if(this.collection[j] > this.collection[j+1]) {
                            const temp = this.collection[j]
                            this.collection[j] = this.collection[j+1]
                            this.collection[j+1] = temp;
                        }
                    }
                }

                // if(this.collection[j] > this.collection[j+1]) {
                //     // swap
                //     const temp = this.numbers[j];
                //     this.collection[j] = this.collection[j+1];
                //     this.collection[j+1] = temp
                // }
            }
        }
        return this.collection;
    }
}

export default NamooSorter;