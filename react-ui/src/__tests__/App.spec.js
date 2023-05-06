import React from 'react';
import {render, cleanup, waitForElement} from '@testing-library/react';
import {App} from '../App';
import {storiesIds, singularStory} from '../fixtures';
import {getStory, getStoriesIds} from '../services/hackerNewsAPI';
import {useInfiniteScroll} from '../hooks/useInfiniteScroll';
import {STORY_INCREMENT} from '../data/constants';
import test, {beforeEach} from 'node:test';
import expect from 'expect';
import jest from 'jest';

beforeEach(cleanup);

jest.mock('../hooks/useInfiniteScroll.js');

jest.mock('../services/hackerNewsAPI', () => ({
    getStory: jest.fn(),
    getStoriesIds: jest.fn(),
}));

test('renders the application', async () => {
    useInfiniteScroll.mockImplementation(() => ({
        count: STORY_INCREMENT,
    }));
    getStory.mockImplementation(() => Promise.resolve(singularStory));
    getStoriesIds.mockImplementation(() => Promise.resolve(storiesIds));

    const {getByText, queryByTestId} = render(<App />);
    await waitForElement(() => [
        expect(getByText('The Sun News Stories')).toBeTruthy(),
        expect(getByText(
            'ACM Software System Award Given to seL4 Microkernel',
            )).toBeTruthy(),
        expect(queryByTestId('story-by').textContent)
            .toEqual('By: Nasevich Vladislav'),
    ]);
});
