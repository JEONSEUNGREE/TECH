import './App.css';
import React, { useState, useEffect } from 'react';
// import Counter from './components/Counter'
import Movielist from './components/MovieList';
import MovieForm from './components/MovieForm';


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
      <Movielist movie={movie} key={movie.id} removeMovie={removeMovie}/>
    );
  }) : "추가된 영화가 없습니다.";



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
