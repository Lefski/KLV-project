// const webpack = require('webpack'); // Webpack itself

const path = require('path'); // Built-in Node library to define paths to files
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

// Indicates in which dev env we are - dev/prod
const production = process.env.NODE_ENV == 'production';

// Webpack parameters
module.exports = {
    entry: {
        // Bundle name: path to entry file:
        app: path.resolve(__dirname, './src/index.js'),
    },
    output: {
         // Directory to export bundle in:
        path: path.resolve(__dirname, './dist'),

        // With each new build hash will be added
        // to filename for better loading speed:
        filename: production ?
            '[name].[contenthash].bundle.js' :
            '[name].bundle.js',
        clean: true, // Clears /dist from old files during re-build
    },
    module: {
        rules: [
            { // Rule for JS
                test: /\.(js|jsx)$/, // Where to search
                exclude: /node_modules/, // Where not to search
                use: ['babel-loader'], // Array of loaders
            },
            { // Rule for styles modules
                test: /\.module\.s(a|c)ss$/,
                exclude: /node_modules/,
                use: [
                    production ? MiniCssExtractPlugin.loader : 'style-loader',
                    {
                        loader: 'css-loader',
                        options: {
                            modules: true,
                            sourceMap: !production,
                        },
                    },
                    {
                        loader: 'sass-loader',
                        options: {
                            sourceMap: !production,
                        },
                    },
                ],
            },
            { // Rule for styles
                test: /\.s(a|c)ss$/,
                exclude: [
                    /\.module.(s(a|c)ss)$/,
                    /node_modules/,
                ],
                use: [
                    production ? MiniCssExtractPlugin.loader : 'style-loader',
                    {
                        loader: 'sass-loader',
                        options: {
                            sourceMap: !production,
                        },
                    },
                ],
            },
        ],
    },
    resolve: {
        // For importing files without writing extensions
        extensions: ['*', '.js', '.jsx', '.scss'],
    },
    plugins: [
        new HtmlWebpackPlugin({ // Connects built JS with HTML file
            title: 'The Sun News',
            template: './src/index.html',
            favicon: './src/assets/icons/sun-favicon-round.png',
        }),
        new MiniCssExtractPlugin({
            filename: production ?
                '[name].[contenthash].bundle.css' :
                '[name].bundle.css',
            chunkFilename: production ?
                '[id].[contenthash].bundle.css' :
                '[id].bundle.css',
        }),
    ],
    devServer: {
        port: 3001,
        hot: true, // Helps to keep state in React and change only edited code
    },
    mode: production ? 'production' : 'development',
};
