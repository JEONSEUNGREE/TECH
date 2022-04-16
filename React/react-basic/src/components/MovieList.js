import React from 'react';

const Movielist = ({ movie }) => {
    return (
        <div className='movie'>
            <div className='movie-title'>{movie.title}</div>
            <div className='movie'>{movie.year}</div>
        </div>
    )
}

// const Movielist = (props) => {
//     return (
//         <div className='movie' key={props.movie.title}>
//             <div className='movie-title'>props.movie.title</div>
//             <div className='movie'>props.movie.year</div>
//         </div>
//     )
// }

export default Movielist;
