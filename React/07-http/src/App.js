import React, { useState, useEffect, useCallback } from "react";

import MoviesList from "./components/MoviesList";
import "./App.css";
// 컴포넌트의 상태를 바꾸는 fetch는 사이드이펙트에 해당하기에
// useEffect훅에 넣어야한다.
function App() {
  const [movies, setMovies] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchMoviesHandler = useCallback(async function() {
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch("https://swapi.dev/api/films/");
      
      if (!response.ok) {
        throw new Error("Somthing went wrong!!");
      }

      const data = await response.json();
      // .then((res) => {
      //   // 프로미스임으로 객체를 반환후 뒤에 작업 진행
      //   return res.json();
      // })
      // .then(data => {
      // console.log(data)
      const transformedMovies = data.results.map((movieData) => {
        return {
          id: movieData.episode_id,
          title: movieData.title,
          openingText: movieData.opening_crawl,
          releaseDate: movieData.release_date,
        };
      });
      setMovies(transformedMovies);
      setIsLoading(false);
    } catch (error) {
      setError(error.message);
    }
  }, []);

  useEffect(() => {
    fetchMoviesHandler();
  }, [fetchMoviesHandler]);

  let content = <p>Found no Movies.</p>;

  if (movies.length > 0) {
    content = <MoviesList movies={movies} />
  }

  if (error) {
    content = <p>{error}</p>
  }

  if (isLoading) {
    content = <p>Loading...</p>;
  }

  return (
    <React.Fragment>
      <section>
        <button onClick={fetchMoviesHandler}>Fetch Movies</button>
      </section>
      <section>
        <section>{content}</section>
        {/* {!isLoading && movies.length > 0 && } */}
        {/* {!isLoading && movies.length === 0 && !error && <p>Found No Moives.</p>} */}
        {/* {isLoading && <p>Loading...</p>} */}
        {/* {isLoading && error && <p>{error}</p>} */}
      </section>
    </React.Fragment>
  );
}

export default App;
