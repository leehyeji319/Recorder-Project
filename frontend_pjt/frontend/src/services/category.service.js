import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080';

const createCategory = (userId, categoryName ) => {
  return axios.post(API_URL + '/board/categories/', {
    userId,
    categoryName
  }, { headers: authHeader() });
};

const allCategories = (user_id) => {
  return axios.get(API_URL + `/board/categories/users/${user_id}`, { headers: authHeader() });
};

const findCategory = (category_id) => {
  return axios.get(API_URL + `/board/categories/${category_id}`, { headers: authHeader() });
};

const updateCategory = (category_id, data) => {
  return axios.put(API_URL + `/board/categories/${category_id}`, data, {
    headers: authHeader(),
  });
};
const deleteCategory = (category_id) => {
  return axios.delete(API_URL + `/board/categories/${category_id}`, {
    headers: authHeader(),
  });
};

const getCurrentCategory = () => {
  return JSON.parse(localStorage.getItem('category'));
};

const CategoryService = {
  createCategory,
  allCategories,
  findCategory,
  updateCategory,
  deleteCategory,
  getCurrentCategory,
};

export default CategoryService;
