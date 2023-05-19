import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {StoriesContainer} from './containers/StoriesContainer';
import {eventBus} from './common/EventBus';
import {AuthService} from './services/auth.service';
import {Route, Routes} from 'react-router';
import {Profile} from './components/Profile';
import {Register} from './components/Register';
import {Link} from 'react-router-dom';
import {Login} from './components/Login';
import {GlobalStyle} from './assets/styles/ResetStyles';
import {BoardModerator} from './components/BoardModerator';
import {BoardUser} from './components/BoardUser';
import {BoardAdmin} from './components/BoardAdmin';
import {BookmarkService} from './services/bookmark.service';
import {BookmarksContext} from './contexts/useBookmarksContext';

export const App = () => {
    const [showModeratorBoard, setShowModeratorBoard] = useState(false);
    const [showAdminBoard, setShowAdminBoard] = useState(false);
    const [currentUser, setCurrentUser] = useState(undefined);
    const [bookmarks, setBookmarks] = useState([]);

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
            setShowModeratorBoard(user.roles.includes('ROLE_MODERATOR'));
            setShowAdminBoard(user.roles.includes('ROLE_ADMIN'));

            BookmarkService
                .getUserBookmarks()
                .then((data) => setBookmarks(data));
        }

        eventBus.on('logout', () => {
            logout();
        });

        return () => {
            eventBus.remove('logout');
        };
    }, []);

    const logout = () => {
        AuthService.logout().then((res) => console.log(res.message));
        setShowModeratorBoard(false);
        setShowAdminBoard(false);
        setCurrentUser(undefined);
    };

    return (
        <>
            <BookmarksContext.Provider
                value={{bookmarks, setBookmarks}}>
                <GlobalStyle />
                <div>
                    <nav className="navbar navbar-expand navbar-dark bg-dark">
                        <img
                            src="../src/assets/icons/sun-favicon-round.png"
                            alt="Sun Icon"
                            className="mr-2"
                            style={{height: '30px', paddingRight: '10px'}}
                        />
                        <Link to={'/'} className="navbar-brand">
                            The Sun News Stories
                        </Link>
                        <div className="navbar-nav mr-auto">
                            {showModeratorBoard && (
                                <li className="nav-item">
                                    <Link to={'/test/moderator'}
                                          className="nav-link">
                                        Moderator Board
                                    </Link>
                                </li>
                            )}

                            {showAdminBoard && (
                                <li className="nav-item">
                                    <Link to={'/test/admin'}
                                          className="nav-link">
                                        Admin Board
                                    </Link>
                                </li>
                            )}

                            {currentUser && (
                                <li className="nav-item">
                                    <Link to={'/test/user'}
                                          className="nav-link">
                                        User Board
                                    </Link>
                                </li>
                            )}
                        </div>

                        {currentUser ? (
                            <div className="navbar-nav ml-auto">
                                <li className="nav-item">
                                    <Link to={'/profile'}
                                          className="nav-link">
                                        {currentUser.username}
                                    </Link>
                                </li>
                                <li className="nav-item">
                                    <a
                                        href="/"
                                        className="nav-link"
                                        onClick={logout}
                                    >
                                        Sign-out
                                    </a>
                                </li>
                            </div>
                        ) : (
                            <div className="navbar-nav ml-auto">
                                <li className="nav-item">
                                    <Link to={'/login'}
                                          className="nav-link">
                                        Login
                                    </Link>
                                </li>

                                <li className="nav-item">
                                    <Link to={'/register'}
                                          className="nav-link">
                                        Register
                                    </Link>
                                </li>
                            </div>
                        )}
                    </nav>


                    <div className="container mt-3">
                        <Routes>
                            <Route
                                exact path={'/'}
                                element={<StoriesContainer />}
                            />
                            <Route
                                exact path="/login"
                                element={<Login />} />
                            <Route
                                exact path="/register"
                                element={<Register />} />
                            <Route
                                exact path="/profile"
                                element={<Profile />} />
                            <Route exact path="/test/user"
                                   element={<BoardUser />} />
                            <Route exact path="/test/moderator"
                                   element={<BoardModerator />} />
                            <Route exact path="/test/admin"
                                   element={<BoardAdmin />} />
                        </Routes>
                    </div>

                    {/* <AuthVerify logOut={logout}/> */}
                </div>
            </BookmarksContext.Provider>
        </>
    );
};
