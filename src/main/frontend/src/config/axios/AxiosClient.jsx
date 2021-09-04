import axios from 'axios'

const instance = axios.create({
    baseURL: "http://localhost:4090/signlearning"
});

instance.interceptors.response.use((response) => {
    return response;
}, error => {
    if (error.response.status === 401) {
        localStorage.clear();
        alert("Brak uprawnień do zasobu. Nastąpi wylogowanie");
    }
    return error;
})

instance.interceptors.request.use((config) => {
    const token = localStorage.getItem("token")
    config.headers.Authorization = `Bearer ${token}`;
    return config;
});

export default instance;