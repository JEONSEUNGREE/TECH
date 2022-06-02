import React, { useEffect } from "react";

import { Route, useParams, Link, useRouteMatch } from "react-router-dom";
import Comments from "../components/comments/Comments";
import useHttp from "../hooks/use-http";
import { getSingleQuote } from "../lib/api";

import HighlightedQuote from "../components/quotes/HighlightedQuote";
import LoadingSpinner from "../components/UI/LoadingSpinner";

// const DUMMY_QUOTES = [
//   { id: "q1", author: "Max", text: "Learning React is Fun!" },
//   { id: "q2", author: "Maximilian", text: "Learning React is Great!" },
// ];

const Quotesdetail = () => {
  // useRouteMath
  // 중첩라우팅을 쉽게사용하기위함
  // url정보 뿐아니라 라우터 내부에서 관리되는 데이터를 보기위함
  const match = useRouteMatch();

  const params = useParams();

  const { quoteId } = params;

  const {
    sendRequest,
    status,
    data: loeadedQuote,
    error,
  } = useHttp(getSingleQuote, true);

  useEffect(() => {
    sendRequest(quoteId);
  }, [sendRequest, quoteId]);

  if (status === "pending") {
    return (
      <div className="centered">
        <LoadingSpinner />
      </div>
    );
  }

  if (error) {
    return <p className="centered">{error}</p>;
  }

//   const quote = loeadedQuote.find((quote) => quote.id === params.quoteId);
  console.log(match);

  if (!loeadedQuote) {
    return <p>No quote Found!</p>;
  }

  return (
    <div>
      <HighlightedQuote text={loeadedQuote.text} author={loeadedQuote.author} />
      {/* 중첩라우팅을이용한 코멘트 클릭후 버튼 숨기기 */}
      <Route path={`${match.path}`} exact>
        <div className="centered">
          <Link className="btn--flat" to={`/quotes/${params.quoteId}/comments`}>
            Load Comments
          </Link>
        </div>
      </Route>
      <Route path={`${match.path}/comments`}>
        <Comments />
      </Route>
    </div>
  );
};

export default Quotesdetail;
