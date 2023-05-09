/**
 * Retrieves the value of a specific cookie from the document's cookie string.
 *
 * @function
 * @param {string} name - The name of the cookie to retrieve.
 * @return {string|null} The value of the cookie if it exists.
 * Otherwise, returns null.
 */
export const getCookie = (name) => {
    let end;
    const dc = document.cookie;
    const prefix = name + '=';
    let begin = dc.indexOf('; ' + prefix);
    if (begin === -1) {
        begin = dc.indexOf(prefix);
        if (begin !== 0) return null;
    } else {
        begin += 2;
        end = document.cookie.indexOf(';', begin);
        if (end === -1) {
            end = dc.length;
        }
    }

    return decodeURI(dc.substring(begin + prefix.length, end));
};
