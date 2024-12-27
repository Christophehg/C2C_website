import React, { useState } from 'react';
import "./SearchBar.css"

const SearchBar = ({ onSearch }) => {
    const [inputValue, setInputValue] = useState('');

    const handleChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSearch(inputValue);
    };

    return (
        <form className="SearchBar" onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Rechercher par mots-clés..."
                value={inputValue}
                onChange={handleChange}
            />
            <button type="submit">Rechercher</button>
        </form>
    );
};

export default SearchBar;