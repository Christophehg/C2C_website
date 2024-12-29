import axios from "axios";

const apiConfig = axios.create({
    baseURL: "https://localhost:8080/",
    headers: {
        "Content-Type": "application/json",
    }
})

export default apiConfig;