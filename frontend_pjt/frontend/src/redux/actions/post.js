import { RECENT_POST, GET_POST } from "./types";
import PostService from "../../services/post.service";

export const getRecentPost = (postId, userId, categoryId, title, content, summary, hits, exposure, thumbnailImage) => async (dispatch) => {
    try {
        const res = await PostService.getRecentPost({ postId, userId, categoryId, title, content, summary, hits, exposure, thumbnailImage });
        dispatch({
            type: RECENT_POST,
            payload: res.data,
        });
        return Promise.resolve(res.data);
    } catch(err) {
        return Promise.reject(err);
    }
};

export const getPost = (post_id) => async (dispatch) => {
    try {
        const res = await PostService.getPost(post_id);
        dispatch({
            type: GET_POST,
            payload: res.data
        })
    } catch (error) {
        console.error(error.message);
    }
}