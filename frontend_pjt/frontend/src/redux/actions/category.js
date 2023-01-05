import { CREATE_CATEGORY, ALL_CATEGORIES, FIND_CATEGORY, UPDATE_CATEGORT, DELETE_CATEGORY } from "./types";
import CategoryService from "../../services/category.service";



export const createCategory = (user_id, categoryName) => async (dispatch) => {
  try {
    const res = await CategoryService.createCategory(user_id, categoryName); //API 호출
    dispatch({
      type: CREATE_CATEGORY,
      payload: res.data,
    });
    return Promise.resolve(res.data); //성공
  } catch (err) {
    return Promise.reject(err); //실패
  }
};

export const allCategories = (user_id) => async (dispatch) => {
  try {
    const res = await CategoryService.allCategories(user_id);
    dispatch({
      type: ALL_CATEGORIES,
      payload: res.data
    });
  } catch (err) {
    console.log(err);
  }
};

export const findCategory = (categoryName) => async (dispatch) => {
  try {
    const res = await CategoryService.findCategory(categoryName);
    dispatch({
      type: FIND_CATEGORY,
      payload: res.data,
    });
  } catch (err) {
    console.log(err);
  }
};

export const updateCategory = (id, data) => async (dispatch) => {
  try {
    const res = await CategoryService.updateCategory(id, data);
    dispatch({
      type: UPDATE_CATEGORT,
      payload: data,
    });
    return Promise.resolve(res.data);
  } catch (err) {
    return Promise.reject(err);
  }
};

export const deleteCategory = (category_id) => async (dispatch) => {
  try {
    await CategoryService.deleteCategory(category_id);
    dispatch({
      type: DELETE_CATEGORY,
      payload: { category_id },
    });
  } catch (err) {
    console.log(err);
  }
};





