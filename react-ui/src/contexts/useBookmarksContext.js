import {createContext, useContext} from 'react';

export const UseBookmarksContext = createContext();

/**
 *
 * @return {unknown}
 */
export function useBookmarksContext() {
    return useContext(UseBookmarksContext);
}
