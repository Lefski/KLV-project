import api from '../utils/api';

const addBookmark = async (story) => {
    const articleUrl = story.url;
    const articleTitle = story.title;
    return api.post('/user/bookmarks/add', {
        articleUrl,
        articleTitle,
    });
};

const deleteBookmark = async (bookmarkId) => {
    return api.delete('/user/bookmarks/delete/' + bookmarkId);
};

const getUserBookmarks = async () => {
    return api.get('/user/bookmarks').then(({data}) => data);
};

export const BookmarkService = {
    addBookmark,
    deleteBookmark,
    getUserBookmarks,
};
