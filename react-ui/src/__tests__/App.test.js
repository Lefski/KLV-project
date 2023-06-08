import React from 'react';
import renderer from 'react-test-renderer';
import expect from 'expect';
import {describe, it} from 'node:test';
import {App} from '/src/App';

describe('Jest Snapshot testing suite', () => {
    it('Matches DOM Snapshot', () => {
        const tree = renderer
            .create(<App />)
            .toJSON();
        expect(tree).toMatchSnapshot();
    });
});
