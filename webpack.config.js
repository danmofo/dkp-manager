const path = require('path');
const SCRIPT_DIR = path.resolve(__dirname, 'client/scripts');

module.exports = {
	entry: SCRIPT_DIR + '/index.js',
	output: {
		filename: 'main.js',
		path: path.resolve(__dirname, 'dist')
	}
}