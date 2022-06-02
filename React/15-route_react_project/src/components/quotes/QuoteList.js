import { Fragment } from 'react';

import { useHistory, useLocation, useRouteMatch } from 'react-router-dom';

import QuoteItem from './QuoteItem';
import classes from './QuoteList.module.css';

const sortQuotes = (quotes, ascending) => {
  return quotes.sort((quoteA, quoteB) => {
    if (ascending) {
      return quoteA.id > quoteB.id ? 1 : -1;
    } else {
      return quoteA.id < quoteB.id ? 1 : -1;
    }
  });
};

const QuoteList = (props) => {
  // url을 변경시켜주는 훅
  const history = useHistory();

  // 로드된 url 정보를 가져오는훅
  const location = useLocation();

  // default 자바스크립트 함수로 url 파람 추출
  // console.log(location);
  const queryParams = new URLSearchParams(location.search);

  const isSortingAscending = queryParams.get('sort') === 'asc';

  console.log(location.pathname)

  const changeSortingHandler = () => {
    // 읽기 편하게 변경
    history.push({
      pathname: location.pathname,
      search: `?sort=${(isSortingAscending ? 'desc' : "asc")}`
    })
    // history.push(`${location.pathname}/?sort=${(isSortingAscending ? 'desc' : "asc")}`);
  }

  return (
    <Fragment>
      <div className={classes.sorting}>
        <button onClick={changeSortingHandler}>Sort {isSortingAscending ? 'Descending' : 'Ascending'}</button>
      </div>
      <ul className={classes.list}>
        {props.quotes.map((quote) => (
          <QuoteItem
            key={quote.id}
            id={quote.id}
            author={quote.author}
            text={quote.text}
          />
        ))}
      </ul>
    </Fragment>
  );
};

export default QuoteList;
