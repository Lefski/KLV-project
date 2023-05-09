import api from '../utils/api';

const getPublicContent = () => {
    return api.get('/test');
};

const getUserBoard = () => {
    return api.get('/test/user');
};

const getModeratorBoard = () => {
    return api.get('/test/moderator');
};

const getAdminBoard = () => {
    return api.get('/test/admin');
};

export const BoardsService = {
    getPublicContent,
    getUserBoard,
    getModeratorBoard,
    getAdminBoard,
};
