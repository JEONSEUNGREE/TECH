import './App.css';
import React, { useState, useEffect } from 'react';
// import Counter from './components/Counter'
import Movielist from './components/MovieList';
import MovieForm from './components/MovieForm';


function App() {

  const [movies, setMovies] = useState([
    { title: 'Kossie Coder1', year: 2001 },
    { title: 'Kossie Coder2', year: 2002 },
    { title: 'Kossie Coder3', year: 2003 }
  ])

  useEffect(() => {
    console.log("render");
  })

  const renderMovies = movies.map(movie => {
    return (
      <Movielist movie={movie} key={movie.title} />
    )
  })



  const addMovie = (movie) => {
    setMovies([...movies, movie]);
  };

  return (
    <div className='app'>
      <h1>Movie list</h1>
      <MovieForm addMovie={addMovie}/>
      {renderMovies}
    </div>
  )
}

export default App;
