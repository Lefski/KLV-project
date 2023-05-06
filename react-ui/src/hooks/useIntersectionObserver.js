import {useEffect, useRef} from 'react';

export const useIntersectionObserver = (callback) => {
    const observerRef = useRef(null);
    const targetRef = useRef(null);

    useEffect(() => {
        observerRef.current = new IntersectionObserver(
            (entries) => {
                const firstEntry = entries[0];
                if (firstEntry.isIntersecting) {
                    callback();
                }
            },
            {threshold: 1.0},
        );

        const {current: currentObserver} = observerRef;
        const {current: currentTarget} = targetRef;

        if (currentTarget) {
            currentObserver.observe(currentTarget);
        }

        return () => {
            if (currentTarget) {
                currentObserver.unobserve(currentTarget);
            }
        };
    }, [callback]);

    return targetRef;
};
