import React from "react";
import { useHistory } from 'react-router-dom'

import QuoteForm from '../components/quotes/QuoteForm';


const Newquote = () => {
  const history = useHistory();

  const addQuoteHandler = (quoteData) => {
    console.log(quoteData);
    // router와 연동하여 push를 사용시 이전페이지로 돌아갈수있으며
    // replace의 경우 불가하다
    history.push('/quotes')

  };

  return <QuoteForm onAddQuote={addQuoteHandler} />;
};

export default Newquote;
