import autoprefixer from 'autoprefixer';
import constants from './constants';
import CopyWebpackPlugin from 'copy-webpack-plugin';
import ExtractTextPlugin from 'extract-text-webpack-plugin';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import path from 'path';
import webpack from 'webpack';

// cheap-module-eval-source-map, because we want original source, but we don't
// care about columns, which makes this devtool faster than eval-source-map.
// http://webpack.github.io/docs/configuration.html#devtool
var devtools = 'cheap-module-eval-source-map';

export default function makeConfig(isDevelopment) {
	// helper function for building the "loader" query
	function stylesLoaders(loaders) {
		return Object.keys(loaders).map(ext => {
			const prefix = 'css-loader!postcss-loader';
			const extLoaders = prefix + loaders[ext];
			const loader = isDevelopment
				? `style-loader!${extLoaders}`
				: ExtractTextPlugin.extract('style-loader', extLoaders);
			return {
				loader,
				test: new RegExp(`\\.(${ext})$`)
			};
		});
	}

	const config = {
		cache: isDevelopment,
		debug: isDevelopment,
		// helps with debugging in browser - generates source maps
		devtool: isDevelopment ? devtools : '',
		stats: {children: false},
		entry: {
			app: isDevelopment ? [
				// in development, we want to include the hot reload extension,
				// that allows for faster development without manual recompiling
				'webpack-hot-middleware/client',
				// the main entry to our app
				path.join(constants.SRC_DIR, 'index.js')
			] : [
				// the main entry to our app
				path.join(constants.SRC_DIR, 'index.js')
			]
		},
		output: {
			// output directory for the compiled application
			path: constants.OUTPUT_DIR,
			filename: 'bundle.js',
			publicPath: '/'
		},
		plugins: (() => {
			const plugins = [
				// defines global variables for the app
				new webpack.DefinePlugin({
					'process.env': {
						IS_BROWSER: true, // Because webpack is used only for browser code.
						IS_SERVERLESS: JSON.stringify(process.env.IS_SERVERLESS || false),
						NODE_ENV: JSON.stringify(isDevelopment ? 'development' : 'production'),
						SERVER_URL: JSON.stringify(process.env.SERVER_URL || '')
					}
				}),
				// uses the src/index.html as a basic template and injects paths to CSS and JS files compiled by webpack
				new HtmlWebpackPlugin({
					filename: 'index.html',
					template: 'src/index.html',
					inject: true,
					hash: true
				}),
				// copy favicon files to dist
				new CopyWebpackPlugin([
					{from: 'src/style/favicon', to: 'favicon'},
				]),
				// Assign the module and chunk ids by occurrence count. Ids that are used often get lower (shorter) ids. This make ids predictable, reduces to total file size and is recommended.
				new webpack.optimize.OccurenceOrderPlugin(),
			];

			if (isDevelopment) {
				plugins.push(
					// Generates Hot Update Chunks of each chunk in the records. It also enables the API and makes __webpack_hash__ available in the bundle.
					new webpack.HotModuleReplacementPlugin(),
					// When there are errors while compiling this plugin skips the emitting phase (and recording phase), so there are no assets emitted that include errors.
					new webpack.NoErrorsPlugin()
				);

			} else {
				plugins.push(
					// Render styles into separate cacheable file to prevent FOUC and optimize for critical rendering path.
					new ExtractTextPlugin('style.css', {
						allChunks: true
					}),
					// Search for equal or similar files and deduplicate them in the output. This comes with some overhead for the entry chunk, but can reduce file size effectively.
					new webpack.optimize.DedupePlugin(),
					// Minimize all JavaScript output of chunks.
					new webpack.optimize.UglifyJsPlugin({
						compress: {
							screw_ie8: true, // eslint-disable-line camelcase
							warnings: false // Because uglify reports irrelevant warnings.
						}
					})
				);
			}

			return plugins;
		})(),
		// fix generated CSS files to include vendor specific prefixes for the last 2 major versions of browsers
		postcss: () => [autoprefixer({browsers: 'last 2 version'})],
		module: {
			loaders: [
				{
					// handles loading of javascript files and preprocessing them with babel.js
					test: /\.jsx?$/,
					loader: 'babel',
					exclude: /node_modules/,
					include: constants.ABSOLUTE_BASE,
					query: {
						cacheDirectory: true,
						plugins: ['transform-runtime', 'add-module-exports'],
						presets: ['es2015', 'react', 'stage-1'],
						env: {
							development: {
								presets: ['react-hmre']
							},
							production: {
								plugins: [
									'transform-react-constant-elements',
									'transform-react-inline-elements'
								]
							}
						}
					}

				},
				{
					// if the file passes the size limit test, it is inlined inside the stylesheet,
					// otherwise it's emitted as a separate file and linked
					loader: 'url-loader?limit=10000',
					test: /\.(gif|jpg|png|svg)$/
				},
				{
					loader: 'url-loader?limit=1',
					test: /favicon\.ico$/
				},
				{
					loader: 'url-loader?limit=100000',
					test: /\.(ttf|eot|woff(2)?)(\?[a-z0-9]+)?$/
				}
			].concat(stylesLoaders({
				// stylesheet preprocessor loader definitions
				css: '',
				scss: '!sass-loader',
				sass: '!sass-loader?indentedSyntax'
			}))
		}
	};

	return config;
};
