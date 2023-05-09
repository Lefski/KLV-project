import React from 'react';
import {withRouter} from 'react-router';
import PropTypes from 'prop-types';

const parseJwt = (token) => {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
};

const AuthVerify = (props) => {
    props.history.listen(() => {
        const user = JSON.parse(localStorage.getItem('user'));

        if (user) {
            const decodedJwt = parseJwt(user.accessToken);

            if (decodedJwt.exp * 1000 < Date.now()) {
                props.logOut();
            }
        }
    });

    return <div></div>;
};

AuthVerify.propTypes = {
    history: PropTypes.shape({
        listen: PropTypes.func.isRequired,
    }).isRequired,
    logOut: PropTypes.func.isRequired,
};

export default withRouter(AuthVerify);
