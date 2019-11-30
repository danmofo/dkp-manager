const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const path = require('path');
const SCRIPT_DIR = path.resolve(__dirname, 'client/scripts');

module.exports = {
	entry: SCRIPT_DIR + '/index.js',
	output: {
		filename: 'main.js',
		path: path.resolve(__dirname, 'dist')
	},
	module: {
		rules: [
			{ 
				test: /\.css$/, 
				use: [
					{
						loader: MiniCssExtractPlugin.loader,
						options: {
							// should be disabled for prod
							hmr: true
						}
					}, 
					'css-loader'
				]
			}
		]
	},
	plugins: [
		new MiniCssExtractPlugin({
			filename: '[name].css'
		})
	]
}