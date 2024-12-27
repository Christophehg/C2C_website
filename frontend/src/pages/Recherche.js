import React, { useState, useEffect } from 'react';
import './Recherche.css';
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import SearchBar from '../components/SearchBar';
import ItemCard from '../components/ItemCard';

const SearchItemsPage = () => {
    const [query, setQuery] = useState('');
    const [items, setItems] = useState([]);

    useEffect(() => {
        // Simulate an API call to fetch items
        const fetchItems = async () => {
            const mockData = [
                { id: 1, title: 'Téléphone', description: 'iPhone 13 en excellent état', price: 800, isSold: false },
                { id: 2, title: 'Ordinateur', description: 'MacBook Pro 2020, 16 Go RAM', price: 1500, isSold: true },
                { id: 3, title: 'Chaise', description: 'Chaise ergonomique pour bureau', price: 120, isSold: false },
            ];
            setItems(mockData);
        };
        fetchItems();
    }, []);

    const handleSearch = (searchQuery) => {
        setQuery(searchQuery.toLowerCase());
    };

    const filteredItems = items.filter((item) =>
        item.title.toLowerCase().includes(query) ||
        item.description.toLowerCase().includes(query)
    );

    return (
        <div>
            <Navbar/>
            <h1>Rechercher des objets</h1>
            <SearchBar onSearch={handleSearch}/>
            <div className="items-container">
                {filteredItems.length > 0 ? (
                    filteredItems.map((item) => <ItemCard key={item.id} item={item}/>)
                ) : (
                    <p>Aucun objet trouvé.</p>
                )}
            </div>
            <Footer/>
        </div>
    );
};

export default SearchItemsPage;
