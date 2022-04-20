import './App.css';
import React, { useEffect } from 'react';
// import Counter from './components/Counter'
import Navbar from './components/Navbar';
import {
  BrowserRouter as Router,
  Route,
  Routes,
} from 'react-router-dom'
import routes from './routes';

function App() {

  useEffect(() => {
    console.log("render");
  })


  return (
    <div className='app'>
      <Router>
        <Navbar />
        <div className='container'>
          <Routes>
            {routes.map(route => {
              return (
                <Route key={route.path}
                  path={route.path}
                  element={
                    <route.component />
                  }>
                  </Route>
              )
            })}
          </Routes>
        </div>
      </Router>
    </div>
  )
}

export default App;
