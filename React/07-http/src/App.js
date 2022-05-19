import React, { useState } from "react";

import MoviesList from "./components/MoviesList";
import "./App.css";

function App() {

  const [movies, setMovies] = useState([]);

  function fetchMoviesHandler() {
    fetch("https://swapi.dev/api/films/")
      .then((res) => {
        // 프로미스임으로 객체를 반환후 뒤에 작업 진행
        return res.json();
      })
      .then(data => {
        console.log(data)
        const transformedMovides = data.results.map(movieData => {
          return {
            id: movieData.episode_id,
            title: movieData.title,
            openingText: movieData.opening_crawl,
            releaseDate: movieData.release_date
          }
        });
        setMovies(data.results);
      });
  }

  return (
    <React.Fragment>
      <section>
        <button onClick={fetchMoviesHandler}>Fetch Movies</button>
      </section>
      <section>
        <MoviesList movies={movies} />
      </section>
    </React.Fragment>
  );
}

export default App;
