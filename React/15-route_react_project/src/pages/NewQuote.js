import { useEffect } from "react";

import React from "react";
import { useHistory } from 'react-router-dom'

import QuoteForm from '../components/quotes/QuoteForm';
import useHttp from '../hooks/use-http'
import { addQuote } from "../lib/api"; 

const Newquote = () => {
  const { sendRequest, status } =  useHttp(addQuote);
  const history = useHistory();

  useEffect(() => {
    if (status === 'completed')  {
      history.push('/quotes');
    }
  }, [status, history]);

  const addQuoteHandler = (quoteData) => {
    sendRequest(quoteData);
    // router와 연동하여 push를 사용시 이전페이지로 돌아갈수있으며
    // replace의 경우 불가하다
    history.push('/quotes')

  };

  return <QuoteForm isLoading={status === 'pending'} onAddQuote={addQuoteHandler} />;
};

export default Newquote;
