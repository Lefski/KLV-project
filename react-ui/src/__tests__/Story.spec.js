import React from 'react';
import {render, cleanup, waitForElement} from '@testing-library/react';
import {Story} from '../components/Story';
import {singularStory} from '../fixtures';
import {getStory} from '../services/news.service';
import * as jest from 'node/test';
import test, {beforeEach} from 'node:test';
import expect from 'expect';

beforeEach(() => {
    cleanup();
    jest.resetAllMocks();
});

jest.mock('../services/hackerNewsAPI', () => ({
    getStory: jest.fn(),
}));

test('renders the story component with content', async () => {
    getStory.mockImplementation(() => Promise.resolve(singularStory));

    const {getByText, getByTestId} = render(<Story storyId="1" />);

    await waitForElement(() => [
        expect(getByTestId('story')).toBeTruthy(),
        expect(getByText('ACM Software System Award Given to seL4 Microkernel'))
            .toBeTruthy(),
        expect(getByTestId('story-by').textContent)
            .toEqual('By: Nasevich Vladislav'),
    ]);
});
