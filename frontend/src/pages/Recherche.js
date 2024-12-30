import React, { useState, useEffect } from 'react';
import './Recherche.css';
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import SearchBar from '../components/SearchBar';
import ItemCard from '../components/ItemCard';
import api from '../api/api';
import TokenManager from "../LocalStorage/TokenManager";

const SearchItemsPage = () => {
    const [query, setQuery] = useState('');
    const [items, setItems] = useState([]);
    const [selectedItem, setSelectedItem] = useState(null); // Pour afficher les détails de l'objet

    // Fonction de conversion des données
    function convertItems(items) {
        return items.map(item => {
            return {
                id: item.numeroE,
                title: item.nom,
                description: item.description,
                price: item.prix,
                isSold: item.vendu,
                proprietaireNom: item.proprietaireNom
            };
        });
    }

    useEffect(() => {
        // Fonction pour récupérer les objets non vendus
        const fetchItems = async () => {
            try {
                const items = await api.getALLItemsNonVendus();
                setItems(convertItems(items));
            } catch (error) {
                console.error('Erreur lors de la récupération des objets: aie', error);
            }
        };

        fetchItems();
    }, []);

    // Fonction pour filtrer les objets selon la recherche
    const handleSearch = (searchQuery) => {
        setQuery(searchQuery.toLowerCase());
    };

    // Filtrer les objets en fonction de la recherche
    const filteredItems = items.filter((item) =>
        item.title.toLowerCase().includes(query) ||
        item.description.toLowerCase().includes(query)
    );

    // Fonction pour afficher les détails d'un objet
    const handleItemClick = (item) => {

        var isAdmin = TokenManager.getRole() === 'ADMIN';

        if (isAdmin){
            alert('Vous ne pouvez pas acheter un objet en tant qu\'administrateur');
            return
        }
        setSelectedItem(item); // Met à jour l'objet sélectionné
    };

    // Fonction pour acheter l'objet
    const handleBuy = async (item) => {
        const isConnect = TokenManager.isExistedToken();
        if (!isConnect) {
            alert('Vous devez vous connecter pour acheter un objet');
            return
        }

        console.log('Achat de l\'objet', item);
        try {
            // Appel à l'API pour acheter l'objet (exemple)
            await api.acheterItem(item.id);
            alert('Objet acheté !');
            window.location.href = '/';
        } catch (error) {
            console.error('Erreur lors de l\'achat de l\'objet', error);
            alert('vous ne pouvez pas acheter votre propre objet');
        }
    };

    return (
        <div id="secondBodyRecherche">
            <Navbar />
            <h1>Rechercher des objets</h1>
            <SearchBar onSearch={handleSearch} />
            <div className="items-container">
                {filteredItems.length > 0 ? (
                    filteredItems.map((item) => (
                        <div key={item.id} className="item-card" onClick={() => handleItemClick(item)}>
                            <ItemCard item={item} />
                        </div>
                    ))
                ) : (
                    <p>Aucun objet trouvé.</p>
                )}
            </div>

            {selectedItem && (
                <div className="item-details">
                    <div className="details-content">
                        <h2>{selectedItem.title}</h2>
                        <p>{selectedItem.description}</p>
                        <p className="price">{selectedItem.price}€</p>
                        <p><strong>Vendu par: {selectedItem.proprietaireNom}</strong></p> {/* Affichage du vendeur */}
                        {selectedItem.isSold ? (
                            <p><strong>Ce produit est déjà vendu.</strong></p>
                        ) : (
                            <button onClick={() => handleBuy(selectedItem)}>Acheter</button>
                        )}
                        <button className="close-button" onClick={() => setSelectedItem(null)}>Fermer</button>
                    </div>
                </div>
            )}


            <Footer />
        </div>
    );
};

export default SearchItemsPage;
