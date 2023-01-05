import axios from "axios";
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/board/';

const getRecentPost = (postId, userId, categoryId, title, content, summary, hits, exposure, thumbnailImage) => {
    return axios
        .get(API_URL + 'posts/' + 'v1', {
            postId,
            userId,
            categoryId,
            title,
            content,
            summary,
            hits,
            exposure,
            thumbnailImage
        })
        .catch((error) => {
            console.error(error);
        });
}

const getPost = (post_id) => {
    return axios
    .get(API_URL + 'posts/' + `${post_id}`) 
    .catch((error) => {
        console.error(error);
    });
}


const PostService = {
    getRecentPost,
    getPost
}

export default PostService;