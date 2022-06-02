import { Route, Switch, Redirect } from "react-router-dom";

import Allquotes from "./pages/AllQuotes";
import Newquote from "./pages/NewQuote";
import Quotesdetail from "./pages/QuotesDetail";
import Layout from "./components/layout/Layout";
import Notfound from "./pages/NotFound";

function App() {
  return (
    <Layout>
      <Switch>
        <Route path="/" exact>
          <Redirect to='/quotes'/>
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
        <Route path='*'>
          <Notfound />
        </Route>
      </Switch>
    </Layout>
  );
}

export default App;
