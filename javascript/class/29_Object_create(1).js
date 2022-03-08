//  자바스크립트 클래스 - Object.create 상속
// class 문법 지원이 추가되 이전 자바스크립트에서 객체지향을 구현하기 위해 사용된 방식 --> Object.create()
// Object.create() 방식이 없던 시절에도 상당히 복잡하게 클래스적인 구현을 흉내냈었지만 지금은 그런 방식까지 알 필요는 X


// [1] : Object.create()
// 1. Object.create( 부모객체. )

// extends => Object.created()
// super => call(), apply

// -첫번째 인자(부모객체)로 들어온 해당 객체(부모)의 '프로토타입 객체'를 복제.
// -이렇게 복제된 것을 --> "자식객체.prototype"에 할당
// 2. 그러나 여전히 복제된 '프로토타입 객체는 부모객체를 가리키고 있기 때문에 이것을 자식 객체를 가리키도록 바꿔줘야한다.
// - '자식객체.prototype.constructor = 자식객체'를 할당해서 연결 고리를 맞춰준다.

// 3. 클래스에서 super 역할을 new 키워드를 통해서 인스턴스(객체)를 생성시 자식 객체의 this가 부모 객체까지 전달되도록 해줘야한다.
//    이를 해주기 위해서 --> 부모객체.call(=apply)(this.인자값) --> 이렇게 해줘야한다.
//    class의 super 역할이라고 보면 된다.

// 정리
// class 에서는 상속의 extends 와 super로 구현한다면
// class 이전에는 Object.create()와 prototype.call등을 이용해서 구현했다.