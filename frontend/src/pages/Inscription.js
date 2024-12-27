import React, { useState } from 'react';
import './Inscription.css';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

const Inscription = () => {
    const [formData, setFormData] = useState({ login: '', password: '', city: '' });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Simulate form submission
        setMessage('Inscription réussie !');
        setFormData({ login: '', password: '', city: '' });
    };

    return (
        <div>
            <Navbar />
            <div className="signup-container">
                <h1>Inscription</h1>
                <form className="signup-form" onSubmit={handleSubmit}>
                    <input
                        type="text"
                        name="login"
                        placeholder="Pseudo"
                        value={formData.login}
                        onChange={handleChange}
                        required
                    />
                    <input
                        type="password"
                        name="password"
                        placeholder="Mot de passe"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                    <input
                        type="text"
                        name="city"
                        placeholder="Ville"
                        value={formData.city}
                        onChange={handleChange}
                        required
                    />
                    <button type="submit">S'inscrire</button>
                </form>
                {message && <p className="success-message">{message}</p>}
            </div>
            <Footer />
        </div>
    );
};

export default Inscription;