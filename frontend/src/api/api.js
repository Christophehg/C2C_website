import apiConfig from './config';

const creationUtilisateur = async (pseudo, mdp, villeResidence) => {
    try {

        const params = new URLSearchParams({
            pPseudo: pseudo,
            pMdp: mdp,
            pVilleResidence: villeResidence
        });

        // Envoi de la requête POST avec les paramètres encodés dans le corps de la requête
        const response = await apiConfig.post('/CreationUtilisateur', params, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' // Spécifie le type de contenu
            }
        });

        return response.data; // Retourne la réponse du backend
    } catch (error) {
        console.error('Erreur lors de la création de l\'utilisateur :', error);
        throw error; // Relance l'erreur pour la gérer dans le composant
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

export default { creationUtilisateur, testAPI };