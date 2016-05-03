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
		devtool: isDevelopment ? devtools : '',
		stats: {children: false},
		entry: {
			app: isDevelopment ? [
				'webpack-hot-middleware/client',
				path.join(constants.SRC_DIR, 'index.js')
			] : [
				path.join(constants.SRC_DIR, 'index.js')
			]
		},
		output: {
			path: constants.OUTPUT_DIR,
			filename: 'static/bundle.js',
			publicPath: '/'
		},
		plugins: (() => {
			const plugins = [
				new webpack.DefinePlugin({
					'process.env': {
						IS_BROWSER: true, // Because webpack is used only for browser code.
						IS_SERVERLESS: JSON.stringify(process.env.IS_SERVERLESS || false),
						NODE_ENV: JSON.stringify(isDevelopment ? 'development' : 'production'),
						SERVER_URL: JSON.stringify(process.env.SERVER_URL || '')
					}
				}),
				new HtmlWebpackPlugin({
					filename: 'index.html',
					template: 'src/index.html',
					inject: true,
					hash: true
				}),
				new CopyWebpackPlugin([
					{from: 'src/style/favicon', to: 'favicon'},
				]),
			];

			if (isDevelopment) {
				plugins.push(
					new webpack.optimize.OccurenceOrderPlugin(),
					new webpack.HotModuleReplacementPlugin(),
					new webpack.NoErrorsPlugin()
				);

			} else {
				plugins.push(
					// Render styles into separate cacheable file to prevent FOUC and
					// optimize for critical rendering path.
					new ExtractTextPlugin('static/style.css', {
						allChunks: true
					}),
					new webpack.optimize.DedupePlugin(),
					new webpack.optimize.OccurenceOrderPlugin(),
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
		postcss: () => [autoprefixer({browsers: 'last 2 version'})],
		module: {
			loaders: [
				{
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
				css: '',
				scss: '!sass-loader',
				sass: '!sass-loader?indentedSyntax'
			}))
		}
	};

	return config;
};
