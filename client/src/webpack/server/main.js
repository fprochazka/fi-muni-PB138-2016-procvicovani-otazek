import webpack from 'webpack';
import webpackDevMiddleware from 'webpack-dev-middleware';
import webpackHotMiddleware from 'webpack-hot-middleware';
import makeWebpackConfig from '../makeConfig';
import constants from '../constants';
import express from 'express';

var port = 3000;

var webpackConfig = makeWebpackConfig(true);
var compiler = webpack(webpackConfig);

var app = express();
app.use(webpackDevMiddleware(compiler, {noInfo: true, publicPath: webpackConfig.output.publicPath}));
app.use(webpackHotMiddleware(compiler));

app.use(function (req, res) {
	res.sendFile(constants.OUTPUT_DIR + '/index.html')
});

app.listen(port, function (error) {
	if (error) {
		console.error(error)
	} else {
		console.info("==> 🌎  Listening on port %s. Open up http://localhost:%s/ in your browser.", port, port)
	}
});
