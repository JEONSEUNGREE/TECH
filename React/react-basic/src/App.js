import './App.css';
import React, { useState, useEffect } from 'react';
// import Counter from './components/Counter'
import Movielist from './components/MovieList';

function App() {

  const movies = [
    { title: 'Kossie Coder1', year: 2001 },
    { title: 'Kossie Coder2', year: 2002 },
    { title: 'Kossie Coder3', year: 2003 }
  ]
  const renderMovies = movies.map(movie => {
    return (
      <Movielist movie={movie}/>
    )
  })

  return (
    <div className='app'>

      {renderMovies}
      {/* <div className='movie'>
        <div className='movie-title'>{movies[0].title}</div>
        <div className='movie'>{movies[0].year}</div>
      </div> */}
      {/* <div className='movie'>
        <div className='movie-title'>{movies[1].title}</div>
        <div className='movie'>{movies[1].year}</div>
      </div>
      <div className='movie'>
        <div className='movie-title'>{movies[2].title}</div>
        <div className='movie'>{movies[2].year}</div>
      </div> */}
    </div>
  )
}

export default App;
