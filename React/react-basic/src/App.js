import './App.css';
import React, { useState, useEffect } from 'react';
// import Counter from './components/Counter'
import Movielist from './components/MovieList';
import MovieForm from './components/MovieForm';
import Navbar from './components/Navbar';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Routes,
} from 'react-router-dom'

function App() {

  const [movies, setMovies] = useState([
  ])

  useEffect(() => {
    console.log("render");
  })

  const removeMovie = (id) => {
    setMovies(movies.filter(movie => {
      return movie.id !== id;
    }))
  }

  const renderMovies = movies.length ? movies.map(movie => {
    return (
      <Movielist movie={movie} key={movie.id} removeMovie={removeMovie} />
    );
  }) : "추가된 영화가 없습니다.";



  const addMovie = (movie) => {
    setMovies([...movies, movie]);
  };

  return (
    <div className='app'>
      <Router>
        <Navbar />
        <Routes>
          <Route path='/*' element={
            <div>
              <h1>Home</h1>
            </div>
          }>
          </Route>
          <Route path='movies' element={
            <div>
              <h1>Movie list</h1>
              <MovieForm addMovie={addMovie} />
              {renderMovies}
            </div>
          } />
          <Route path='users' element={
            <h1>Users</h1>
          }>
          </Route>
        </Routes>
      </Router>
    </div>
  )
}

export default App;
