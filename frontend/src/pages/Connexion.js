import React, { useState } from 'react';
import './Connexion.css';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

const Connexion = () => {
    const [formData, setFormData] = useState({ login: '', password: '' });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Simulate login submission
        setMessage('Connexion réussie !');
        setFormData({ login: '', password: '' });
    };

    return (
        <div>
            <Navbar />
            <div className="login-container">
                <h1>Connexion</h1>
                <form className="login-form" onSubmit={handleSubmit}>
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
                    <button type="submit">Se connecter</button>
                </form>
                {message && <p className="success-message">{message}</p>}
            </div>
            <Footer />
        </div>
    );
};

export default Connexion;
