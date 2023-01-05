import {
  REGISTER_SUCCESS,
  REGISTER_FAIL,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
  SET_MESSAGE,
  REGISTER_POST,
  CATEGORY_GET
} from './types';

import AuthService from '../../services/auth.service';

export const register =
  (email, password, nickname, domain, introduce) => (dispatch) => {
    return AuthService.register(
      email,
      password,
      nickname,
      domain,
      introduce,
    ).then(
      (response) => {
        dispatch({
          type: REGISTER_SUCCESS,
        });

        dispatch({
          type: SET_MESSAGE,
          payload: response.data.message,
        });

        return Promise.resolve();
      },
      (error) => {
        const message =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        dispatch({
          type: REGISTER_FAIL,
        });

        dispatch({
          type: SET_MESSAGE,
          payload: message,
        });

        return Promise.reject();
      },
    );
  };

export const login = (email, password) => (dispatch) => {
  return AuthService.login(email, password)
    .then((data) => {
      if (data){
        dispatch({
          type: LOGIN_SUCCESS,
          payload: { user: data },
        });
  
        return Promise.resolve();

      } 
      else {
        dispatch({
          type: LOGIN_FAIL,
        });

        // dispatch({
        //   type: SET_MESSAGE,
        //   payload: message,
        // });

        return Promise.reject();
      }
    }
  )
};

export const logout = () => (dispatch) => {
  AuthService.logout();

  dispatch({
    type: LOGOUT,
  });

  return Promise.resolve();
};

export const registerPost = (user_id, categoryId, title, content) => (dispatch) => {
  return AuthService.registerPost(
    user_id,
    categoryId,
    title,
    content,
  ).then(
    (response) => {
      dispatch({
        type: REGISTER_POST
      });

      dispatch({
        type: SET_MESSAGE,
        payload: response.data.message,
      });

    return Promise.resolve();
  },
  (error) => {
    const message =
      (error.response &&
        error.response.data &&
        error.response.data.message) ||
      error.message ||
      error.toString();

    dispatch({
      type: REGISTER_FAIL,
    });

    dispatch({
      type: SET_MESSAGE,
      payload: message,
    });

    return Promise.reject();
  },
);
};

export const getCategory = (categoryId, categoryName) => (dispatch) => {
    return AuthService.getCategory(
      categoryId,
      categoryName,
    ).then(
      (response) => {
        dispatch({
          type: CATEGORY_GET
        })
      
        dispatch({
          type: SET_MESSAGE,
          payload: response.data.message,
        });
    
        return Promise.resolve();
      },
      (error) => {
        const message =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
    
        dispatch({
          type: REGISTER_FAIL,
        });
    
        dispatch({
          type: SET_MESSAGE,
          payload: message,
        });
    
        return Promise.reject();
      },
    );
    };