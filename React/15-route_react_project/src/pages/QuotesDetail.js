import React from "react";

import { Route, useParams, Link } from "react-router-dom";

import Comments from "../components/comments/Comments";

import HighlightedQuote from "../components/quotes/HighlightedQuote";

const DUMMY_QUOTES = [
  { id: "q1", author: "Max", text: "Learning React is Fun!" },
  { id: "q2", author: "Maximilian", text: "Learning React is Great!" },
];

const Quotesdetail = () => {
  const params = useParams();

  const quote = DUMMY_QUOTES.find((quote) => quote.id === params.quoteId);

  console.log(quote);

  if (!quote) {
    return <p>No quote Found!</p>;
  }

  return (
    <div>
      <HighlightedQuote text={quote.text} author={quote.author} />
      {/* 중첩라우팅을이용한 코멘트 클릭후 버튼 숨기기 */}
      <Route path={`/quotes/${params.quoteId}`} exact>
        <div className="centered">
          <Link className="btn--flat" to={`/quotes/${params.quoteId}/comments`}>
            Load Comments
          </Link>
        </div>
      </Route>
      <Route path={`/quotes/${params.quoteId}/comments`}>
        <Comments />
      </Route>
    </div>
  );
};

export default Quotesdetail;
