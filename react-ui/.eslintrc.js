module.exports = {
    'env': {
        'browser': true,
        'es2021': true,
    },
    'extends': [
        'plugin:react/recommended',
        'google',
    ],
    'overrides': [],
    'parserOptions': {
        'ecmaVersion': 'latest',
        'sourceType': 'module',
    },
    'plugins': [
        'react',
    ],
    'rules': {
        'indent': 'off',
        'react/react-in-jsx-scope': 'off',
        'linebreak-style':
            ['error', process.platform === 'win32' ? 'windows' : 'unix'],
    },
};
