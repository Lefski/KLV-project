/**
 * Checks whether a value is provided or not.
 *
 * @function
 * @param {string} value - The value to be checked.
 * @return {JSX.Element|null} A JSX div element containing an
 * error message if no value is provided. Otherwise, returns null.
 */
export const required = (value) => {
    if (!value) {
        return (
            <div className='invalid-feedback d-block'>
                This field is required!
            </div>
        );
    }
};
