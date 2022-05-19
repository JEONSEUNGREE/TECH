import { Component } from "react";

class ErrorBoundary extends Component {
  constructor() {
    super();
    this.state = {
      hasError: false,
    };
  }
  // 일반적으로 리액트에서 try-catch문인 자바스크립트 형태를 사용할수없기에
  // 다음과같이 사용한다.
  componentDidCatch(error) {
    //   리액트에 에러를 인자로 받아서 수행한다.
    this.setState({ hasError: true });
  }

  render() {
    if (this.state.hasError) {
      return <p>SomeThing went wrong!</p>
    }
    return this.props.children;
  }
}

export default ErrorBoundary;
