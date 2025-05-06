import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { signIn } from '../api';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const navigate = useNavigate();

  useEffect(() => {
    // Clear any previous login state when visiting the login page
    localStorage.removeItem('token');
    localStorage.removeItem('role');
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      // Prepare login data to match Spring Boot's AuthRequest structure
      const authRequest = {
        email: formData.email,
        password: formData.password,
      };
      
      // Call the signIn API with the AuthRequest object
      const response = await signIn(authRequest);
      console.log(response.data);
  
      // Extract the token and role from the response
      const token = response.data.token;
      const role = response.data.user.role;

      // Store the token and role in sessionStorage
      sessionStorage.setItem('token', token);
      sessionStorage.setItem('role', role);

      localStorage.setItem('loggedIn', 'true');
      // fix based on user role
      localStorage.setItem('userRole', 'student');
  
      // Navigate based on role
      switch (role) {
        case 'ROLE_STUDENT':
          navigate('/studentDashboard');
          break;
        case 'ROLE_LECTURER':
          navigate('/lecturerDashboard');
          break;
        case 'ROLE_ADMIN':
          navigate('/adminDashboard');
          break;
        default:
          alert('Unknown role selected!');
      }
      
    } catch (error) {
      console.error('Login failed:', error.response?.data || error.message);
      alert('Login failed. Please check your credentials.');
    }
  };

  return (
    <div className="login-wrapper">
      <div className="login-container">
        <form className="login-form" onSubmit={handleSubmit}>
          <h2 className="login-title">Welcome to SmartCampus</h2>

          <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            required
            className="login-input"
          />

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
            className="login-input"
          />

          <button type="submit" className="login-button">Login</button>

          <p className="forgot-link">
            <Link to="/forgot">Forgot password or email?</Link>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Login;