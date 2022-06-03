// LAZY LOAD
// Suspense는 컴포넌트 청크사이즈를 받는 잠깐의 시간동안 보여질 화면
import React, { Suspense } from "react";
import { Route, Switch, Redirect } from "react-router-dom";

// import Allquotes from "./pages/AllQuotes";

// import Quotesdetail from "./pages/QuotesDetail";
import Layout from "./components/layout/Layout";
// import Notfound from "./pages/NotFound";
import LoadingSpinner from "./components/UI/LoadingSpinner";

const Newquote = React.lazy(() => import("./pages/NewQuote"));
const Quotesdetail = React.lazy(() => import("./pages/QuotesDetail"));
const Notfound = React.lazy(() => import("./pages/NotFound"));
const Allquotes = React.lazy(() => import("./pages/AllQuotes"));

function App() {
  return (
    <Layout>
      <Suspense
        fallback={
          <div className="centered">
            <LoadingSpinner />
          </div>
        }
      >
        <Switch>
          <Route path="/" exact>
            <Redirect to="/quotes" />
          </Route>
          <Route path="/quotes" exact>
            <Allquotes />
          </Route>
          <Route path="/quotes/:quoteId">
            <Quotesdetail />
          </Route>
          <Route path="/new-quote">
            <Newquote />
          </Route>
          {/* 매칭 /url이 없으면 아래 표시 */}
          <Route path="*">
            <Notfound />
          </Route>
        </Switch>
      </Suspense>
    </Layout>
  );
}

export default App;
