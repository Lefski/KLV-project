import {useEffect, useRef} from 'react';

/**
 * A custom hook that uses the Intersection Observer API
 * to execute a callback function when an element becomes visible.
 *
 * @function
 * @param {function} callback - The function to be executed
 * when the observed element is intersecting.
 * @return {Object} A React ref object to be attached
 * to the target element to be observed.
 *
 * @example
 * const targetRef = useIntersectionObserver(
 *      () => console.log("Element is visible")
 * );
 *
 * <div ref={targetRef}></div>
 */
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
