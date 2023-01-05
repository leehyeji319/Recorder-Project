import { USER_GET, USER_UPDATE, IMAGE_UPDATE } from "./types";
import UserService from "../../services/user.service";

export const getUser = (user_id) => async (dispatch) => {
    try {
        const res = await UserService.getUser(user_id);
        dispatch({
            type: USER_GET,
            payload: res.data
        })
    } catch (error) {
        console.error(error.message);
    }
}

export const updateUser = (user_id, email, nickname, domain, introduce) => async (dispatch) => {
    try {
        const res = await UserService.updateUser(user_id, email, nickname, domain, introduce);
        dispatch({
            type: USER_UPDATE,
            payload: res.data,
        })
        return Promise.resolve(res.data);
    } catch (error) {
        return Promise.reject(error);
    }
};

export const updateImage = (user_id, url) => async (dispatch) => {
    try {
        const res = await UserService.updateImage(user_id, url);
        dispatch({
            type: IMAGE_UPDATE,
            paylaod: res.data,
        })
        console.log('data', res.data);
        return Promise.resolve(res.data);
    } catch(error) {
        return Promise.reject(error);
    }
};