import React, {useEffect, useState} from 'react';
import {getStoriesIds} from '../services/hackerNewsAPI';
import {Story} from '../components/Story';
import {GlobalStyle} from '../assets/styles/ResetStyles';
import {StoriesContainerWrapper} from '../assets/styles/StoryContainerStyles';
import {useInfiniteScroll} from '../hooks/useInfiniteScroll';
import {useIntersectionObserver} from '../hooks/useIntersectionObserver';

export const StoriesContainer = () => {
    const {count} = useInfiniteScroll();
    const [storiesIds, setStoriesIds] = useState([]);
    const [loading, setLoading] = useState(false); // Add this line

    useEffect(() => {
        getStoriesIds().then((data) => setStoriesIds(data));
    }, []);

    const loadMoreStories = () => {
        setLoading(true);
    };

    const targetRef = useIntersectionObserver(loadMoreStories);

    return (
        <>
            <GlobalStyle />
            <StoriesContainerWrapper data-test-id="stories-container">
                <h1>The Sun News Stories</h1>
                {storiesIds.slice(0, count).map((storyId) => (
                    <Story key={storyId} storyId={storyId} />
                ))}
                {loading && <div>Loading...</div>}
                <div ref={targetRef} />
            </StoriesContainerWrapper>
        </>
    );
};
