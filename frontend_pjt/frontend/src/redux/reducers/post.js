import { RECENT_POST, GET_POST } from "../actions/types";

const initialState = {};

export default function getPost(post = initialState, action) {
    const { type, payload } = action;
    switch (type) {
        case RECENT_POST:
            return [...post, payload];
        case GET_POST:
            return payload;

        default:
            return post;
    }
};
