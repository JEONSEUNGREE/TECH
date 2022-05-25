import { useState, useCallback } from "react";

const useHttp = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const sendRequest = useCallback(async (requesConfig, applyData) => {
    // useEffect부분에서 아래의 상태값이 바뀜에따라 함수가 재실행되며 무한루프에 빠진다.
    // 해결법은 여러가지가 있겠지만 2가지로 추렸을떄
    // useCallback으로 함수를 감싸거나 상태변화 감지를 두지않기위해 인자를 받지않고
    // 받아온 함수로 파라미터를 가공하는 방법이있다.
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch(requesConfig.url, {
        method: requesConfig.method ? requesConfig.method : "GET",
        headers: requesConfig.headers ? requesConfig.headers : {},
        body: requesConfig.body ? JSON.stringify(requesConfig.body) : null,
      });

      if (!response.ok) {
        throw new Error("Request failed!");
      }

      const data = await response.json();

      applyData(data);
    } catch (err) {
      setError(err.message || "Something went wrong!");
    }
    setIsLoading(false);
  }, []);

  return {
    // isLoading : isLoading,
    // error: error,
    // sendRequest: sendRequest
    // 모던 자바스크립트로 속성과 이름이 같은경우 아래와같이 줄일수있다.
    isLoading,
    error,
    sendRequest,
  };
};

export default useHttp;
