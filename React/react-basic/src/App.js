import './App.css';
import React, { useEffect } from 'react';
// import Counter from './components/Counter'
import Navbar from './components/Navbar';
import {
  BrowserRouter as Router,
  Route,
  Routes,
} from 'react-router-dom'
import Users from './pages/Users';
import Home from './pages/Home';
import Movies from './pages/Movies';

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
            <Route path='/' element={
              <Home />
            }>
            </Route>
            <Route path='movies' element={
              <Movies />
            }/>
            <Route path='users' element={
              <Users />
            }>
            </Route>
          </Routes>
        </div>
      </Router>
    </div>
  )
}

export default App;
