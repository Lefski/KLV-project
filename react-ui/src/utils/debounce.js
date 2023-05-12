/**
 * Debounces a function, delaying its execution until
 * after a specified wait time has elapsed since
 * the last time this debounced function was invoked.
 * The function will be called after it
 * stops being called for the specified wait time.
 * If `immediate` is passed, trigger the function
 * on the leading edge, instead of the trailing.
 *
 * @function
 * @param {Function} func - The function to debounce.
 * @param {number} wait - The number of milliseconds
 * to delay before invoking the `func`.
 * @param {boolean} immediate - If `true`, the `func` will be invoked
 * on the leading edge instead of the trailing.
 * @param {Array} args - The arguments to apply to the `func`.
 * @return {Function} A new debounced version of the passed function.
 */
export const debounce = (func, wait, immediate, args) => {
    let timeout;

    return () => {
        // eslint-disable-next-line no-invalid-this
        const context = this;
        const callNow = immediate && !timeout;
        const later = () => {
            timeout = null;
            if (!immediate) func.apply(context, args);
        };

        clearTimeout(timeout);
        timeout = setTimeout(later, wait);

        if (callNow) func.apply(context, args);
    };
};
