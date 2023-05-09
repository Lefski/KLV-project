import React from 'react';
import PropTypes from 'prop-types';
import bookmarkIconOutline from '../assets/icons/bookmark_icon_outline.png';
import bookmarkIconFilled from '../assets/icons/bookmark_icon_filled.png';
import {BookmarkService} from '../services/bookmark.service';
import {useBookmarksContext} from '../contexts/useBookmarksContext';

export const BookmarkButton = ({story}) => {
    const {bookmarks, setBookmarks} = useBookmarksContext();

    const isBookmarked = () => bookmarks.some(
        (bookmark) => bookmark.articleTitle === story.title,
    );

    const getIcon =
        () => isBookmarked() ? bookmarkIconFilled : bookmarkIconOutline;

    const handleBookmarkClick = () => {
        isBookmarked() ? deleteBookmark(story) : addBookmark(story);
    };

    const addBookmark = async (story) => {
        try {
            await BookmarkService.addBookmark(story).then(async () => {
                setBookmarks(await BookmarkService.getUserBookmarks());
            });
        } catch (error) {
            console.error('Error adding bookmark: ', error);
        }
    };

    const deleteBookmark = async (story) => {
        const currentStoryBookmarks = bookmarks
            .filter((bookmark) => bookmark.articleTitle === story.title);

        for (const currentStoryBookmark of currentStoryBookmarks) {
            try {
                await BookmarkService
                    .deleteBookmark(currentStoryBookmark.id)
                    .then(() => {
                        setBookmarks(
                            bookmarks.filter(
                                (bookmark) =>
                                    bookmark.id !== currentStoryBookmark.id),
                        );
                    });
            } catch (error) {
                console.error('Error deleting bookmark: ', error);
            }
        }
    };

    return (
        <button onClick={handleBookmarkClick}
                style={{border: 'none', background: 'none'}}>
            <img
                src={getIcon()}
                alt="Bookmark"
                style={{width: '24px', aspectRatio: '1 / 1'}}
            />
        </button>
    );
};

BookmarkButton.propTypes = {
    story: PropTypes.object.isRequired,
};
