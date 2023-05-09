import React, {useState, useEffect, memo} from 'react';
import PropTypes from 'prop-types';
import {getStory} from '../services/news.service';
import {
    StoryMeta,
    StoryMetaElement,
    StoryTitle,
    StoryWrapper,
} from '../assets/styles/StoryStyles';
import {mapTime} from '../mappers/mapTime';
import {BookmarkButton} from './BookmarkButton';
import {AuthService} from '../services/auth.service';

export const Story = memo(function Story({storyId}) {
    const [story, setStory] = useState({});

    useEffect(() => {
        getStory(storyId).then((data) => data && data.url && setStory(data));
    }, []);

    return story && story.url ? (
        <StoryWrapper data-testid="story">
            <StoryTitle>
                <a href={story.url}>{story.title}</a>
                {AuthService.getCurrentUser() &&
                    <BookmarkButton story={story}/>
                }
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
