/**
 * Converts a timestamp into a human-readable time difference string.
 *
 * @function
 * @param {number} timestamp - The timestamp to be converted, in seconds.
 * @return {string} A string representing the time difference between
 * now and the provided timestamp.
 * The string will express the time difference in terms of the largest
 * applicable unit (e.g., years, months, days, hours, minutes, or seconds).
 */
export const mapTime = (timestamp) => {
    const seconds = Math.floor((new Date() - timestamp * 1000) / 1000);

    let interval = Math.floor(seconds / 31536000);

    if (interval > 1) {
        return `${interval} years`;
    }
    interval = Math.floor(seconds / 2592000);

    if (interval > 1) {
        return `${interval} months`;
    }
    interval = Math.floor(seconds / 86400);

    if (interval > 1) {
        return `${interval} days`;
    }
    interval = Math.floor(seconds / 3600);

    if (interval > 1) {
        return `${interval} hours`;
    }
    interval = Math.floor(seconds / 60);

    if (interval > 1) {
        return `${interval} minutes`;
    }

    return `${Math.floor(seconds)} seconds`;
};
