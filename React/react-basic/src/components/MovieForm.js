import React, { useState, useEffect } from 'react';

const Movieform = ({ addMovie }) => {

    const [movieTitle, setMovieTitle] = useState('');
    const [movieYear, setMovieYear] = useState('');
    const [titleError, setTitleError] = useState('');
    const [yearError, setYearError] = useState('');

    const resetForm = () => {
        setMovieTitle("");
        setMovieYear("");
    }

    const validateForm = () => {
        let validated = true;

        if (!movieTitle) {
            setTitleError("영화 제목을 넣어주세요")
            validated = false;
        }
        if (!movieYear) {
            setYearError("개봉년도를 넣어주세요")
            validated = false;
        }

        return validated;
    }

    const resetErrors = () => {
        setTitleError("");
        setYearError("");
    }

    const onSubmit = (event) => {
        event.preventDefault();
        if(validateForm()) {
            addMovie({
                id: Date.now(),
                title: movieTitle,
                year: movieYear
            });
        resetErrors();
        resetForm();
        }
    }


    return (
        <form onSubmit={onSubmit}>
            <input
                type="text"
                placeholder='영화제목'
                value={movieTitle}
                onChange={e => setMovieTitle(e.target.value)
                } />
            <br />
            <div style={{color: 'red'}}>{titleError}</div>
            <input
                type="number"
                placeholder='개봉년도'
                value={movieYear}
                onChange={e => setMovieYear(e.target.value)
                } />
            <br />
            <div style={{color: 'red'}}>{yearError}</div>
            <button type='submit'>
                영화추가
            </button>
        </form>
    )
}

export default Movieform;
