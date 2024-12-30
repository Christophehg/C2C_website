import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import api from '../api/api';
import './Profil.css';

const Profil = () => {

    function convertItems(items) {
        return items.map(item => {
            return {
                id: item.numeroE,
                title: item.nom,
                description: item.description,
                price: item.prix,
                isSold: item.vendu
            };
        });
    }

    const [purchasedItems, setPurchasedItems] = useState([]);
    const [soldItems, setSoldItems] = useState([]);
    const [itemsForSale, setItemsForSale] = useState([]);

    useEffect(() => {
        const fetchItems = async () => {
            try {
                const purchased = await api.mesItemsAchetes();
                const mesItems = await api.mesItems();
                const sold = mesItems.filter(item => item.vendu);
                const forSale = mesItems.filter(item => !item.vendu);
                setPurchasedItems(convertItems(purchased));
                setSoldItems(convertItems(sold));
                setItemsForSale(convertItems(forSale));
                console.log(purchased, sold, forSale);
            } catch (error) {
                console.error('Error fetching items:', error);
            }
        };

        fetchItems();
    }, []);

    return (
        <div id="secondBody">
            <Navbar />
            <div className="profile-container">
                <h1>Mon Profil</h1>
                <div className="items-list">
                    <h2>Objets achetés</h2>
                    <ul>
                        {purchasedItems.map(item => (
                            <li key={item.id}>{item.title} - {item.price} €</li>
                        ))}
                    </ul>
                </div>
                <div className="items-list">
                    <h2>Objets vendus</h2>
                    <ul>
                        {soldItems.map(item => (
                            <li key={item.id}>{item.title} - {item.price} €</li>
                        ))}
                    </ul>
                </div>
                <div className="items-list">
                    <h2>Objets à vendre</h2>
                    <ul>
                        {itemsForSale.map(item => (
                            <li key={item.id}>{item.title} - {item.price} €</li>
                        ))}
                    </ul>
                </div>
            </div>
            <Footer />
        </div>
    );
};

export default Profil;