import apiConfig from './config';
import TokenManager from "../LocalStorage/TokenManager";


const register = async (pseudo, mdp, villeResidence) => {
    try {

        const params = {
            pseudo: pseudo,
            mdp: mdp,
            villeResidence: villeResidence
        };

        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.post('/user/register', params);

        return response.data;
    } catch (error) {
        console.error('Erreur lors de la création de l\'utilisateur :', error);
        throw error;
    }
};


const login = async (pseudo, mdp) => {
    try {

        const params = {
            pseudo: pseudo,
            mdp: mdp
        };

        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.post('/user/login', params);
        TokenManager.setToken(response.data[0], response.data[1], response.data[2]);
        return response.data;
    } catch (error) {
        console.error('Erreur lors du login', error);
        throw error;
    }
};


const logout = async () => {
    try {
        // Envoie de la requête de déconnexion au backend
        await apiConfig.post('/user/logout');


        TokenManager.removeToken();
        // Redirige l'utilisateur après la déconnexion
        window.location.href = '/';  // ou utilise un routeur pour rediriger si nécessaire
    } catch (error) {
        console.error('Erreur lors du logout', error);
    }
};

const vendreItem = async (nom, description, prix) => {
    try {

        const params = {
            nom: nom,
            description: description,
            prix: prix
        };

        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.post('/item/add', params);

        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'enregistrement de l\objet à vendre :', error);
        throw error;
    }
};

const getALLItemsNonVendus = async () => {
    try {
        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.get('/item/itemsNonVendus');
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'obtention des objets non vendus :', error);
        throw error;
    }
};

const chiffreAffaire = async () => {
    try {
        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.get('/admin/ca');
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'obtention des objets non vendus :', error);
        throw error;
    }
};

const mesItemsAchetes = async () => {
    try {
        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.get('/user/mesItemsAchetes');
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'obtention des objets non vendus :', error);
        throw error;
    }
};

const mesItems = async () => {
    try {
        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.get('/user/mesItems');
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'obtention des objets non vendus :', error);
        throw error;
    }
};

const acheterItem = async (idItem) => {
    try {
        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.post(`/item/buy/${idItem}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'obtention des objets non vendus :', error);
        throw error;
    }
};

const changerEtatItem = async (idItem) => {
    try {
        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.post(`/item/changerEtat/${idItem}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors du changement d\'etat de l\'item :', error);
        throw error;
    }
};

const testAPI = async (pseudo, mdp, villeResidence) => {
    try {
        const params = new URLSearchParams({
            pPseudo: pseudo,
            pMdp: mdp,
            pVilleResidence: villeResidence
        });

        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.post('/test', params, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' // Spécifie le type de contenu
            }
        });

        return response.data; // Retourne la réponse du backend
    } catch (error) {
        console.error('Erreur lors de l\'appel API :', error);
        throw error; // Relance l'erreur pour la gérer dans le composant
    }
};



export default { register, login, vendreItem, getALLItemsNonVendus, chiffreAffaire, logout,
    mesItemsAchetes, mesItems, acheterItem, changerEtatItem, testAPI };