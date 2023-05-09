import {useState, useEffect} from 'react';
import {STORY_INCREMENT, MAX_STORIES} from '../data/constants';
import {debounce} from '../utils/debounce';

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
    }, 500);

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
