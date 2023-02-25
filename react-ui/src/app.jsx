import React from 'react';
import styles from './styles.scss';


const onClickEvent = (e) => {
    e.preventDefault();
    alert('You Clicked The Test Button');
};

const App = () => {
    return (
        <div className={styles.content}>
            <div className={styles.label}>
                React App Template without CRA
            </div>
            <button className={styles.btn} onClick={onClickEvent}>Click</button>
        </div>
    );
};

export default App;
