import React from 'react';
import {createRoot} from 'react-dom/client';
import {BrowserRouter} from 'react-router-dom';

import {App} from './App';

createRoot(document.getElementById('root')).render(
    <BrowserRouter>
        <App/>
    </BrowserRouter>,
);

const devMode = process.env.NODE_ENV === 'development';

// Code for Webpack to allow changes acception through hot
if (devMode && module && module.hot) {
    module.hot.accept();
}
