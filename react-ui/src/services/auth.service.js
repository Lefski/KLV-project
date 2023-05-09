import axios from 'axios';
import {BASE_API_URL} from '../data/constants';

const authUrl = `${BASE_API_URL}/auth/`;

const register = (username, userEmail, password) => {
    return axios.post(authUrl + 'register', {
        username,
        userEmail,
        password,
    });
};

const login = (username, password) => {
    return axios.post(authUrl + 'login', {
        username,
        password,
    }).then((response) => {
        if (response.data.username) {
            localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
    });
};

const logout = () => {
    localStorage.removeItem('user');
    return axios.post(authUrl + 'logout')
        .then((response) => response.data);
};

const getCurrentUser = () => JSON.parse(localStorage.getItem('user'));

export const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
};
