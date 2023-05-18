import React from 'react';
import PropTypes from 'prop-types';
import {
    StoryMeta,
    StoryMetaElement,
    StoryTitle,
    StoryWrapper,
} from '../assets/styles/StoryStyles';
import {BookmarkButton} from './BookmarkButton';

/**
 * A component that displays a bookmarked story.
 * The component renders the bookmarked story information
 * and also provides an option to unbookmark the story.
 *
 * @component
 * @param {Object} props - Component props.
 * @param {Object} props.bookmark - The bookmark data to display.
 * @return {JSX.Element} The rendered Bookmark component.
 * @example
 * <Bookmark bookmark={bookmarkData} />
 */
export const Bookmark = ({bookmark}) => {
    return (
        <StoryWrapper data-testid="story">
            <StoryTitle>
                <a href={bookmark.articleUrl}>{bookmark.articleTitle}</a>
                <BookmarkButton story={Object.create({
                    title: bookmark.articleTitle,
                    url: bookmark.articleUrl,
                })}/>
            </StoryTitle>

            <StoryMeta>
                <span data-test-id="story-time" className="story-time">
                    <StoryMetaElement
                        color="#000"
                        className="story-meta-element"
                    >
                        Added at:
                    </StoryMetaElement>{' '}
                    {bookmark.timestamp}
                </span>
            </StoryMeta>
        </StoryWrapper>
    );
};

Bookmark.propTypes = {
    bookmark: PropTypes.object.isRequired,
};
