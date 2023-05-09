import isEmail from 'validator/es/lib/isEmail';

/**
 * Validates a given value as an email.
 *
 * @function
 * @param {string} value - The value to be checked.
 * @return {JSX.Element|null} A JSX div element containing an
 * error message if the value is not a valid email.
 * Otherwise, returns null.
 */
export const validateEmail = (value) => {
    if (!isEmail(value)) {
        return (
            <div className="invalid-feedback d-block">
                This is not a valid email.
            </div>
        );
    }
};

/**
 * Validates a given value as a username.
 *
 * @function
 * @param {string} value - The value to be checked.
 * @return {JSX.Element|null} A JSX div element containing an
 * error message if the value's length is not between 3 and 20.
 * Otherwise, returns null.
 */
export const validateUsername = (value) => {
    if (value.length < 3 || value.length > 20) {
        return (
            <div className="invalid-feedback d-block">
                The username must be between 3 and 20 characters.
            </div>
        );
    }
};

/**
 * Validates a given value as a password.
 *
 * @function
 * @param {string} value - The value to be checked.
 * @return {JSX.Element|null} A JSX div element containing an
 * error message if the value's length is not between 6 and 40.
 * Otherwise, returns null.
 */
export const validatePassword = (value) => {
    if (value.length < 6 || value.length > 40) {
        return (
            <div className="invalid-feedback d-block">
                The password must be between 6 and 40 characters.
            </div>
        );
    }
};
