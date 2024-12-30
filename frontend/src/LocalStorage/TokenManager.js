class TokenManager {
    static setToken(token, role, pseudo) {
        localStorage.setItem("authToken", token); // Stocker dans localStorage
        localStorage.setItem("role", role);
        localStorage.setItem("pseudo", pseudo);

    }

    static getPseudo() {
        return localStorage.getItem("pseudo");
    }

    static getRole() {
        return localStorage.getItem("role");
    }

    static isExistedToken() {
        return localStorage.getItem("authToken") ? true : false;
    }

    static getToken() {
        return localStorage.getItem("authToken"); // Récupérer depuis localStorage
    }

    static removeToken() {
        localStorage.removeItem("authToken");
        localStorage.removeItem("role");
        localStorage.removeItem("pseudo");
    }
}

export default TokenManager;
