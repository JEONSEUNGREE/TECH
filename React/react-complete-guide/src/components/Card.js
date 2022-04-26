import './Card.css';

function Card(props) {
    
    const classes = "card " + props.className;
    // 클래스 이름 props로 상속받기

    return <div className={classes}>{props.children}</div>;
    // children은 예약어이다.
}

export default Card;