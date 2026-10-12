import api from './api';
export const getExpenses = async () => (await api.get('/expenses')).data;
export const getSummary = async () => (await api.get('/expenses/summary')).data;
export const createExpense = async (data) => (await api.post('/expenses', data)).data;
export const updateExpense = async (id, data) => (await api.put(`/expenses/${id}`, data)).data;
export const deleteExpense = async (id) => api.delete(`/expenses/${id}`);
