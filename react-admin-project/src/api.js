import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8080/api',
});

// Interceptor to attach token to every request EXCEPT /auth/**
API.interceptors.request.use((config) => {
  const isAuthEndpoint = config.url.startsWith('/auth/');
  
  if (!isAuthEndpoint) {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
  }

  return config;
}, (error) => {
  return Promise.reject(error);
});

// Export your API methods
export const signIn = (authRequest) => API.post('/auth/sign-in', authRequest);
export const CreateUserAccount = (userData) => API.post('/users/create', userData);
