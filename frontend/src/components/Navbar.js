import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
    return (
        <nav>
            <ul>
                <li><Link to="/">Recherche</Link></li>
                <li><Link to="/sell">Vendre un objet</Link></li>
                <li><Link to="/signup">Inscription</Link></li>
                <li><Link to="/login">Connexion</Link></li>
                <li><Link to="/admin">Admin</Link></li>
            </ul>
        </nav>
    );
};

export default Navbar;