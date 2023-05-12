import {useState, useEffect} from 'react';
import {STORY_INCREMENT, MAX_STORIES} from '../data/constants';
import {debounce} from '../utils/debounce';

/**
 * A custom hook to implement infinite scrolling feature in a React component.
 * It calculates the position of the scroll and increments the story count.
 *
 * @function
 * @return {Object} Object that contains a count property that holds
 * the number of stories to be shown at a time.
 *
 * @example
 * const { count } = useInfiniteScroll();
 */
export const useInfiniteScroll = () => {
    const [loading, setLoading] = useState(false);
    const [count, setCount] = useState(STORY_INCREMENT);

    const handleScroll = debounce(() => {
        if (
            window.innerHeight + document.documentElement.scrollTop + 200 >=
            document.documentElement.offsetHeight ||
            !loading
        ) {
            setLoading(true);
        }
    }, 500, true, []);

    useEffect(() => {
        if (!loading) return;

        if (count + STORY_INCREMENT >= MAX_STORIES) {
            setCount(MAX_STORIES);
        } else {
            setCount(count + STORY_INCREMENT);
        }

        setLoading(false);
    }, [loading]);

    useEffect(() => {
        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    return {count};
};
