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

/**
 * A memoized component that fetches and displays a particular story.
 * The component fetches the story data based on
 * the passed storyId and renders the story information.
 * The component also provides an option to bookmark
 * the story if a user is currently authenticated.
 *
 * @component
 * @param {Object} props - Component props.
 * @param {number} props.storyId - The id of the story to fetch and display.
 * @return {React.Element|null} The rendered Story component
 * or null if story data is not yet available.
 * @example
 * <Story storyId={12345} />
 */
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
