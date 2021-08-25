import axios from 'axios'

const instance = axios.create({
    baseURL: "http://localhost:4090/signlearning"
});

instance.interceptors.request.use(function (config) {
    const token = localStorage.getItem("token")
    config.headers.Authorization = token;
    return config;
});

export default instance;