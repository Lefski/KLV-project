import React from 'react';
import {render, cleanup, waitForElement} from '@testing-library/react';
import {StoriesContainer} from '../containers/StoriesContainer';
import {storiesIds, singularStory} from '../fixtures';
import {getStory, getStoriesIds} from '../services/news.service';
import {useInfiniteScroll} from '../hooks/useInfiniteScroll';
import {STORY_INCREMENT} from '../data/constants';
import jest from 'jest';
import test, {beforeEach} from 'node:test';
import expect from 'expect';

beforeEach(cleanup);

jest.mock('../hooks/useInfiniteScroll.js');

jest.mock('../services/hnApi', () => ({
    getStory: jest.fn(),
    getStoryIds: jest.fn(),
}));

test('renders the story container with a story', async () => {
    useInfiniteScroll.mockImplementation(() => ({
        count: STORY_INCREMENT,
    }));
    getStory.mockImplementation(() => Promise.resolve(singularStory));
    getStoriesIds.mockImplementation(() => Promise.resolve(storiesIds));

    const {getByText, queryByTestId} = render(<StoriesContainer />);
    await waitForElement(() => [
        expect(getByText('The Sun News Stories')).toBeTruthy(),
        expect(getByText('ACM Software System Award Given to seL4 Microkernel'))
            .toBeTruthy(),
        expect(queryByTestId('story-by').textContent)
            .toEqual('By: Nasevich Vladislav'),
    ]);
});
