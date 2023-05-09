import axios from 'axios';
import {selectFields} from '../selectors/selectFields';
import {NEWS_API_URL} from '../data/constants';

export const newStoriesUrl = `${NEWS_API_URL}newstories.json`;
export const storyUrl = `${NEWS_API_URL}item/`;

export const getStory = async (storyId) => {
    return await axios
        .get(`${storyUrl + storyId}.json`)
        .then(({data}) => data && selectFields(data));
};

export const getStoriesIds = async () => {
    return await axios.get(newStoriesUrl).then(({data}) => data);
};
