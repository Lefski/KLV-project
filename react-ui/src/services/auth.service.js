import axios from 'axios';
import {BASE_API_URL} from '../data/constants';

const authUrl = `${BASE_API_URL}/auth/`;

/**
 * Registers a new user by sending a POST request to the authentication API.
 *
 * @function
 * @param {string} username - The username of the user.
 * @param {string} userEmail - The email of the user.
 * @param {string} password - The password of the user.
 * @return {Promise<AxiosResponse<any>>} Axios promise
 * that returns the server's response.
 */
const register = (username, userEmail, password) => {
    return axios.post(authUrl + 'register', {
        username,
        userEmail,
        password,
    });
};

/**
 * Authenticates a user by sending a POST request to the authentication API.
 * Stores the user data in local storage if the response contains a user.
 *
 * @function
 * @param {string} username - The username of the user.
 * @param {string} password - The password of the user.
 * @return {Promise<object>} A promise that resolves
 * with the authenticated user's data.
 */
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

/**
 * Logs out a user by sending a POST request to
 * the authentication API and removing the user data from local storage.
 *
 * @function
 * @return {Promise<object>} A promise that resolves
 * with the server's response data.
 */
const logout = () => {
    localStorage.removeItem('user');
    return axios.post(authUrl + 'logout')
        .then((response) => response.data);
};

/**
 * Gets the current user data from local storage.
 *
 * @function
 * @return {object|null} The user data or null
 * if no user data exists in local storage.
 */
const getCurrentUser = () => JSON.parse(localStorage.getItem('user'));

export const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
};
