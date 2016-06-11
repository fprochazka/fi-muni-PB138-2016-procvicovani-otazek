import path from 'path';

export const ABSOLUTE_BASE = path.normalize(path.join(__dirname, '..', '..'));

const constants = Object.freeze({
  ABSOLUTE_BASE,
  NODE_MODULES_DIR: path.join(ABSOLUTE_BASE, 'node_modules'),
  SRC_DIR: path.join(ABSOLUTE_BASE, 'src'),
  OUTPUT_DIR: path.join(ABSOLUTE_BASE, 'dist'),
  HOT_RELOAD_PORT: process.env.HOT_RELOAD_PORT || 3000,
});

export const NODE_MODULES_DIR = constants.NODE_MODULES_DIR;
export const SRC_DIR = constants.SRC_DIR;
export const OUTPUT_DIR = constants.OUTPUT_DIR;
export const HOT_RELOAD_PORT = constants.HOT_RELOAD_PORT;

export default constants;
