import axios from 'axios';

import {BASE_API_URL} from '../data/constants';

const api = axios.create({
    baseURL: BASE_API_URL,
    withCredentials: true,
});

export default api;
