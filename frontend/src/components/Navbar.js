import React from 'react';
import { Link } from 'react-router-dom';
import TokenManager from "../LocalStorage/TokenManager";
import api from '../api/api';
import './Navbar.css';


const Navbar = () => {
    const isAdmin = TokenManager.getRole() === 'ADMIN';
    const isUser = TokenManager.getRole() === 'USER';
    const isConnect = TokenManager.getToken() !== null;
    const pseudoUser = TokenManager.getPseudo();

    const handleLogout = async () => {
        try {
            await api.logout()
        } catch (error) {
            console.error('Erreur lors de la déconnexion:', error);
        }
    }

    return (
        <nav className="navbar">
            <ul className="navbar-links">
                <li><Link to="/">Recherche</Link></li>
                {isUser && isUser && <li><Link to="/sell">Vendre un objet</Link></li>}
                {isUser && <li><Link to="/profil">Mon Profil</Link></li>}
                {!isConnect && <li><Link to="/signup">Inscription</Link></li>}
                {!isConnect && <li><Link to="/login">Connexion</Link></li>}
                {isAdmin && <li><Link to="/admin">Admin</Link></li>}
                {isConnect && <li><Link to="/" onClick={handleLogout}>Déconnexion</Link></li>}
            </ul>

            <ul className="navbar-connected">
                {isConnect && <h3 className={"connected-text"}>Bienvenue <li>{pseudoUser}</li> !</h3>}
            </ul>
        </nav>
    );
};

export default Navbar;