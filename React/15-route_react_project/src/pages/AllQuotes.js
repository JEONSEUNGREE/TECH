import React, { useEffect } from "react";

import useHttp from "../hooks/use-http";
import { getAllQuotes } from "../lib/api";

import QuoteList from "../components/quotes/QuoteList";
import LoadingSpinner from "../components/UI/LoadingSpinner";
import NoQuotedsFound from '../components/quotes/NoQuotesFound';

// const DUMMY_QUOTES = [
//   { id: "q1", author: "Max", text: "Learning React is Fun!" },
//   { id: "q2", author: "Maximilian", text: "Learning React is Great!" },
// ];

const Allquotes = () => {
  const {
    sendRequest,
    status,
    data: loadedQuotes,
    error,
  } = useHttp(getAllQuotes, true);

  useEffect(() => {
    sendRequest();
  }, [sendRequest]);

  if (status === "pending") {
    return (
      <div className="centered">
        <LoadingSpinner />
      </div>
    );
  }

  if (error) {
      return <p className="centered foucused">{error}</p>
  }

  if (status === 'completed' && (!loadedQuotes || !loadedQuotes.length === 0)) {
      return <NoQuotedsFound />
  }

  return <QuoteList quotes={loadedQuotes} />;
};

export default Allquotes;
