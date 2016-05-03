import path from 'path';

export const ABSOLUTE_BASE = path.normalize(path.join(__dirname, '..'));

const constants = Object.freeze({
  ABSOLUTE_BASE,
  NODE_MODULES_DIR: path.join(ABSOLUTE_BASE, 'node_modules'),
  BUILD_DIR: path.join(ABSOLUTE_BASE, 'build'),
  DIST_DIR: path.join(ABSOLUTE_BASE, 'dist'),
  HOT_RELOAD_PORT: process.env.HOT_RELOAD_PORT || 3000,
});

export const NODE_MODULES_DIR = constants.NODE_MODULES_DIR;
export const BUILD_DIR = constants.BUILD_DIR;
export const DIST_DIR = constants.DIST_DIR;
export const HOT_RELOAD_PORT = constants.HOT_RELOAD_PORT;

export default constants;
