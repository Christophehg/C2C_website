import axios from "axios";
import TokenManager from '../LocalStorage/TokenManager';

const apiConfig = axios.create({
    baseURL: "https://localhost:8080/",
    withCredentials: true,
    headers: {
        "Content-Type": "application/json",
    }
})

// Intercepteur pour inclure le token dans chaque requête
apiConfig.interceptors.request.use(
    (config) => {
        if (TokenManager.isExistedToken()) {
            const token = TokenManager.getToken();
            if (token) {
                config.headers.Authorization = `Bearer ${token}`; // Ajouter le token au header
            }
        }

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);


export default apiConfig;