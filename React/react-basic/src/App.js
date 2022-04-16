import './App.css';
import React, { useState, useEffect } from 'react';
import Counter from './components/Counter'

function App() {

  const movies = [
    { title: 'Kossie Coder1', year: 2001 },
    { title: 'Kossie Coder2', year: 2002 },
    { title: 'Kossie Coder3', year: 2003 }
  ]

  useEffect(() => {
  })

  const renderMovies = movies.map(movie => {
    return (
      <div className='movie' key={movie.title}>
    {/* key넣지않는경우 에러 발생 react-jsx-dev-runtime.development.js:81 Warning: Each child in a list should have a unique "key" prop */}
        <div className='movie-title'>{movies[0].title}</div>
        <div className='movie'>{movies[0].year}</div>
      </div>
    )
  }
  )

  return (
    <div className='app'>
      <h1>Movie List</h1>
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
