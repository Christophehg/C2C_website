import React from 'react';
import "./ItemCard.css"

const ItemCard = ({ item }) => {
    return (
        <div className="item-card">
            <h3>{item.title}</h3>
            <p>{item.description}</p>
            <p>Prix: {item.price} €</p>
            <p>Status: {item.isSold ? 'Vendu' : 'Disponible'}</p>
        </div>
    );
};

export default ItemCard;