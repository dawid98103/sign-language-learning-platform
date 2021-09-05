import axios from 'axios'
import history from '../history'
import { toast } from 'react-toastify';

const instance = axios.create({
    baseURL: "http://localhost:4090/signlearning"
});

instance.interceptors.response.use((response) => {
    return response;
}, error => {
    if (error.response.status === 500) {
        toast.warn("Błąd połączenia z serwerem!", {
            position: "bottom-right"
        })
    }
    if (error.response.status === 404 || error.response.status === 400) {
        toast.warn(error.response.data.error.localizedMessage, {
            position: "bottom-right"
        })
    }
    if (error.response.status === 401) {
        localStorage.clear();
        toast.error("Brak uprawnień do zasobu. Nastąpi wylogowanie", {
            position: "bottom-right"
        })
        history.push("/")
    }
    console.log(error)
    console.log(error.response)
    return Promise.reject(error);
})

instance.interceptors.request.use((config) => {
    const token = localStorage.getItem("token")
    config.headers.Authorization = `Bearer ${token}`;
    return config;
});

export default instance;