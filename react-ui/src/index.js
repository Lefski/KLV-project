import {createRoot} from 'react-dom/client';

import {App} from './App';

// Entry point
createRoot(document.getElementById('root')).render(<App />);

const devMode = process.env.NODE_ENV === 'development';

// Code for Webpack to allow changes acception through hot
if (devMode && module && module.hot) {
  module.hot.accept();
}
