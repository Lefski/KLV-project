import {AuthService} from '../services/auth.service';
import {BookmarksContainer} from '../containers/BookmarksContainer';
import 'bootstrap/dist/css/bootstrap.min.css';

/**
 * The Profile component displays user profile information and their bookmarks.
 * It retrieves the current user from the AuthService and shows the user's id,
 * username, email, and roles. It also renders the BookmarksContainer component.
 *
 * @component
 * @return {React.Element} The rendered Profile component.
 * @example
 * <Profile />
 */
export const Profile = () => {
    const currentUser = AuthService.getCurrentUser();

    return (
        <>
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        <strong>{currentUser.username}</strong> Profile
                    </h3>
                </header>
                <div>
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
                <BookmarksContainer />
            </div>
        </>
    );
};
