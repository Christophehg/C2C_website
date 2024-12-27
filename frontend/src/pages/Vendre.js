import React, { useState } from 'react';
import './Vendre.css';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

const Vendre = () => {
    const [formData, setFormData] = useState({ title: '', description: '', price: '' });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Simulate item submission
        setMessage('Votre objet a été mis en vente avec succès !');
        setFormData({ title: '', description: '', price: '' });
    };

    return (
        <div>
            <Navbar />
            <div className="sell-container">
                <h1>Vendre un objet</h1>
                <form className="sell-form" onSubmit={handleSubmit}>
                    <input
                        type="text"
                        name="title"
                        placeholder="Titre de l'objet"
                        value={formData.title}
                        onChange={handleChange}
                        required
                    />
                    <textarea
                        name="description"
                        placeholder="Description de l'objet"
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
