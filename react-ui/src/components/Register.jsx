import React, {useState, useRef} from 'react';
import Form from 'react-validation/build/form';
import Input from 'react-validation/build/input';
import CheckButton from 'react-validation/build/button';
import {AuthService} from '../services/auth.service';
import {
    validateEmail,
    validatePassword,
    validateUsername,
} from '../utils/validate';
import {required} from '../utils/required';

/**
 * A component that provides a registration form for users to create an account.
 * The form collects user input for username, email, and password.
 * Upon form submission, the component attempts to register the user
 * via the AuthService and provides feedback to the user.
 *
 * @component
 * @param {Object} props - Component props.
 * @return {React.Element} The rendered Register component.
 * @example
 * <Register />
 */
export const Register = (props) => {
    const form = useRef(null);
    const checkBtn = useRef(null);

    const [username, setUsername] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [password, setPassword] = useState('');
    const [successful, setSuccessful] = useState(false);
    const [message, setMessage] = useState('');

    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    };

    const onChangeUserEmail = (e) => {
        const userEmail = e.target.value;
        setUserEmail(userEmail);
    };

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };

    const handleRegister = (e) => {
        e.preventDefault();

        setMessage('');
        setSuccessful(false);

        form.current.validateAll();

        if (checkBtn.current.context._errors.length === 0) {
            AuthService.register(username, userEmail, password).then(
                (response) => {
                    setMessage(response.data.message);
                    setSuccessful(true);
                },
                (error) => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setMessage(resMessage);
                    setSuccessful(false);
                },
            );
        }
    };

    return (
        <div className="col-md-12">
            <div className="card card-container">
                <img
                    src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                    alt="profile-img"
                    className="profile-img-card"
                />

                <Form onSubmit={handleRegister} ref={form}>
                    {!successful && (
                        <div>
                            <div className="form-group">
                                <label htmlFor="username">Username</label>
                                <Input
                                    type="text"
                                    className="form-control"
                                    name="username"
                                    value={username}
                                    onChange={onChangeUsername}
                                    validations={[required, validateUsername]}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="email">Email</label>
                                <Input
                                    type="text"
                                    className="form-control"
                                    name="email"
                                    value={userEmail}
                                    onChange={onChangeUserEmail}
                                    validations={[required, validateEmail]}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="password">Password</label>
                                <Input
                                    type="password"
                                    className="form-control"
                                    name="password"
                                    value={password}
                                    onChange={onChangePassword}
                                    validations={[required, validatePassword]}
                                />
                            </div>

                            <div className="form-group">
                                <button
                                    className="btn btn-primary btn-block"
                                >Sign Up</button>
                            </div>
                        </div>
                    )}

                    {message && (
                        <div className="form-group">
                            <div
                                className={
                                    successful ?
                                        'alert alert-success' :
                                        'alert alert-danger'
                                }
                                role="alert"
                            >
                                {message}
                            </div>
                        </div>
                    )}
                    <CheckButton style={{display: 'none'}} ref={checkBtn} />
                </Form>
            </div>
        </div>
    );
};
