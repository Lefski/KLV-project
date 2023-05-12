import axios from 'axios';
import {selectFields} from '../selectors/selectFields';
import {NEWS_API_URL} from '../data/constants';

export const newStoriesUrl = `${NEWS_API_URL}newstories.json`;
export const storyUrl = `${NEWS_API_URL}item/`;

/**
 * Fetches a specific story by its ID from the news API.
 *
 * @async
 * @function
 * @param {number} storyId - The ID of the story.
 * @return {Promise<object>} A promise that resolves with the story data,
 * or null if no story is found.
 * The story data is transformed by the selectFields function.
 */
export const getStory = async (storyId) => {
    return await axios
        .get(`${storyUrl + storyId}.json`)
        .then(({data}) => data && selectFields(data));
};

/**
 * Fetches the IDs of all new stories from the news API.
 *
 * @async
 * @function
 * @return {Promise<number[]>} A promise that resolves
 * with an array of new stories' IDs.
 */
export const getStoriesIds = async () => {
    return await axios.get(newStoriesUrl).then(({data}) => data);
};
