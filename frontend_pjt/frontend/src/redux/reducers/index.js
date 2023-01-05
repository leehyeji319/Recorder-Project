import { combineReducers } from 'redux';
import auth from './auth';
import message from './message';
import category from './category';
import user from './user';
import post from './post';

export default combineReducers({
  auth,
  message,
  category,
  user,
  post,
});
