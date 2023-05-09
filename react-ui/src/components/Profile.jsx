import {AuthService} from '../services/auth.service';

export const Profile = () => {
    const currentUser = AuthService.getCurrentUser();

    return (
        <div className="container">
            <header className="jumbotron">
                <h3>
                    <strong>{currentUser.username}</strong> Profile
                </h3>
            </header>
            <p>
                <strong>Id:</strong> {currentUser.id}
            </p>
            <p>
                <strong>Email:</strong> {currentUser.userEmail}
            </p>
            <strong>Authorities:</strong>
            <ul>
                {
                    currentUser.roles &&
                    currentUser.roles.map(
                        (role, index) => <li key={index}>{role}</li>,
                    )
                }
            </ul>
        </div>
    );
};
