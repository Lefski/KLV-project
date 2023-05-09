import React, {useState, useEffect} from 'react';

import {BoardsService} from '../services/boards.service';

export const BoardUser = () => {
    const [content, setContent] = useState('');

    useEffect(() => {
        BoardsService.getUserBoard().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);
            },
        );
    }, []);

    return (
        <div className="container">
            <header className="jumbotron">
                <h3>{content}</h3>
            </header>
        </div>
    );
};
