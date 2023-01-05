import { CREATE_CATEGORY, ALL_CATEGORIES, FIND_CATEGORY, UPDATE_CATEGORT, DELETE_CATEGORY } from '../actions/types';

const initialState = [];

export default function categoryReducer(category = initialState, action) {
  const { type, payload } = action;
  switch (type) {
    case CREATE_CATEGORY:
      return [...category, payload];

    case ALL_CATEGORIES:
      return payload;
    
    case FIND_CATEGORY:
      return payload;

    case UPDATE_CATEGORT:
      return category.map((category) => {
        if (category.id === payload.id) {
          return {
            ...category,
            ...payload,
          };
        } else {
          return category;
        }
      });

    case DELETE_CATEGORY:
      return category.filter(({ category_id }) => category_id !== payload.category_id);
    default:
      return category;
  }
};