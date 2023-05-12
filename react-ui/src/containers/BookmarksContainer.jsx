import React from 'react';
import {useBookmarksContext} from '../contexts/useBookmarksContext';
import {Bookmark} from '../components/Bookmark';

/**
 * A component that renders a list of bookmarked items.
 * It uses the `useBookmarksContext` hook to access
 * the current bookmarks from the Bookmarks Context.
 *
 * @component
 * @return {React.Element} The rendered BookmarksContainer component.
 */
export const BookmarksContainer = () => {
    const {bookmarks} = useBookmarksContext();
    return (
        <div style={
            {
                paddingBlock: 20,
                margin: 'auto',
            }
        }>
            {bookmarks.reverse().map((bookmark) => (
                <Bookmark
                    key={bookmark.id}
                    bookmark={bookmark}
                />
            ))}
        </div>
    );
};
