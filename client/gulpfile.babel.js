/* eslint-disable no-undef, no-console */
import bg from 'gulp-bg';
import del from 'del';
import gulp from 'gulp';
import webpackBuild from './src/webpack/build';

gulp.task('clean', () => del('static/*'));

gulp.task('build-webpack', ['clean'], webpackBuild);

gulp.task('server-hot', bg('node', './src/webpack/server'));

// Default task to start development. Just type gulp.
gulp.task('default', ['server-hot']);
