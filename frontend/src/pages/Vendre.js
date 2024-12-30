import React, { useState } from 'react';
import './Vendre.css';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import api from '../api/api';
import tokenManager from "../LocalStorage/TokenManager";

const Vendre = () => {
    const [formData, setFormData] = useState({ title: '', description: '', price: '' });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await api.vendreItem(formData.title, formData.description, formData.price);
            console.log("reponse server", response);

            setMessage('Votre item a été mis en vente avec succès !');
            setFormData({ title: '', description: '', price: '' });
        } catch (error) {
            console.error('Erreur lors de la soumission du formulaire:', error);
            setMessage('Une erreur est survenue lors de l\'inscription');
        }
    };

    return (
        <div>
            <Navbar />
            <div className="sell-container">
                <h1>Vendre un item</h1>
                <form className="sell-form" onSubmit={handleSubmit}>
                    <input
                        type="text"
                        name="title"
                        placeholder="Titre de l'item"
                        value={formData.title}
                        onChange={handleChange}
                        required
                    />
                    <textarea
                        name="description"
                        placeholder="Description de l'item"
                        value={formData.description}
                        onChange={handleChange}
                        required
                    ></textarea>
                    <input
                        type="number"
                        name="price"
                        placeholder="Prix en €"
                        value={formData.price}
                        onChange={handleChange}
                        required
                    />
                    <button type="submit">Mettre en vente</button>
                </form>
                {message && <p className="success-message">{message}</p>}
            </div>
            <Footer />
        </div>
    );
};

export default Vendre;
