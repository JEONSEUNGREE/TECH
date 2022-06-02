import React from "react";

import { Route, useParams } from "react-router-dom";

import Comments from "../components/comments/Comments";

import HighlightedQuote from "../components/quotes/HighlightedQuote";

const DUMMY_QUOTES = [
  { id: "q1", author: "Max", text: "Learning React is Fun!" },
  { id: "q2", author: "Maximilian", text: "Learning React is Great!" },
];

const Quotesdetail = () => {
  const params = useParams();

  const quote = DUMMY_QUOTES.find(quote => quote.id === params.quoteId);

  console.log(quote)

  if (!quote) {
      return <p>No quote Found!</p>
  }

  return (
    <div>
      <HighlightedQuote text={quote.text} author={quote.author} />
      <Route path={`/quotes/${params.quoteId}/comments`}>
        <Comments />
      </Route>
    </div>
  );
};

export default Quotesdetail;
