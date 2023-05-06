import React, {useState, useEffect, memo} from 'react';
import PropTypes from 'prop-types';
import {getStory} from '../services/hackerNewsAPI';
import {
    StoryMeta,
    StoryMetaElement,
    StoryTitle,
    StoryWrapper,
} from '../assets/styles/StoryStyles';
import {mapTime} from '../mappers/mapTime';

export const Story = memo(function Story({storyId}) {
    const [story, setStory] = useState({});

    useEffect(() => {
        getStory(storyId).then((data) => data && data.url && setStory(data));
    }, []);

    return story && story.url ? (
        <StoryWrapper data-testid="story">
            <StoryTitle>
                <a href={story.url}>{story.title}</a>
            </StoryTitle>
            <StoryMeta>
                <span data-test-id="story-by">
                    <StoryMetaElement color="#000">
                        By:
                    </StoryMetaElement> {story.by}
                </span>
                <span data-test-id="story-time">
                    <StoryMetaElement color="#000">
                        Posted:
                    </StoryMetaElement> {` `}
                    {mapTime(story.time) + ' ago'}
                </span>
            </StoryMeta>
        </StoryWrapper>
    ) : null;
});

Story.propTypes = {
    storyId: PropTypes.number.isRequired,
};
