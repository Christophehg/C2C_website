import React, { useState } from 'react';
import './Connexion.css';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import api  from '../api/api';
import tokenManager from "../LocalStorage/TokenManager";

const Connexion = () => {
    const [formData, setFormData] = useState({ login: '', password: '' });
    const [message, setMessage] = useState('');



    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await api.login(formData.login, formData.password);
            console.log("reponse server", response);
            console.log("TokenManager", tokenManager.getToken());
            console.log("TokenManager role ", tokenManager.getRole());
            setMessage('Connexion réussie !');
            setFormData({login: '', password: ''});
            window.location.href = '/';
        } catch (error) {
            console.error('Erreur lors de la soumission du formulaire:', error);
            setMessage('Une erreur est survenue lors de l\'inscription');
        }

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
