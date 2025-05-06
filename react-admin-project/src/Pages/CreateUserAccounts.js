import React, { useState } from 'react';
import { useUserContext } from '../Context/UserContext';
import '../Styles/CreateUserAccounts.css'; // Make sure the path is correct

const defaultProfilePicture = 'https://media.geeksforgeeks.org/wp-content/uploads/20230817162109/user.png';

const CreateUserAccounts = () => {
  const { createUser } = useUserContext();

  const [form, setForm] = useState({
    userId: '',
    fullName: '',
    email: '',
    password: '',
    confirmPassword: '',
    role: 'Student',
    phone: '',
    status: 'Active',
    profilePic: null,
  });

  const [previewUrl, setPreviewUrl] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setForm(prev => ({ ...prev, profilePic: file }));
      setPreviewUrl(URL.createObjectURL(file));
    } else {
      setForm(prev => ({ ...prev, profilePic: null }));
      setPreviewUrl('');
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (form.password !== form.confirmPassword) {
      setError('Passwords do not match');
      setSuccess('');
      return;
    }

    if (!/\S+@\S+\.\S+/.test(form.email)) {
      setError('Invalid email format');
      setSuccess('');
      return;
    }

    const userToCreate = {
      id: form.userId,
      fullName: form.fullName,
      email: form.email,
      password: form.password,
      role: form.role,
      phone: form.phone,
      status: form.status,
      profilePic: form.profilePic ? previewUrl : defaultProfilePicture,
      createdAt: new Date().toLocaleString(),
    };

    createUser(userToCreate);

    setSuccess('User account created successfully!');
    setError('');
    setForm({
      userId: '',
      fullName: '',
      email: '',
      password: '',
      confirmPassword: '',
      role: 'Student',
      phone: '',
      status: 'Active',
      profilePic: null,
    });
    setPreviewUrl('');
  };

  const handleReset = () => {
    setForm({
      userId: '',
      fullName: '',
      email: '',
      password: '',
      confirmPassword: '',
      role: 'Student',
      phone: '',
      status: 'Active',
      profilePic: null,
    });
    setPreviewUrl('');
    setError('');
    setSuccess('');
  };

  return (
    <div className="dashboard-content">
      <h2 className="page-title">Create User Account</h2>

      {error && <p style={{ color: 'red' }}>{error}</p>}
      {success && <p style={{ color: 'green' }}>{success}</p>}

      <form className="form-section" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>User ID</label>
          <input type="text" name="userId" className="form-input" value={form.userId} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Full Name</label>
          <input type="text" name="fullName" className="form-input" value={form.fullName} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Email Address</label>
          <input type="email" name="email" className="form-input" value={form.email} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Phone Number</label>
          <input type="tel" name="phone" className="form-input" value={form.phone} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Role</label>
          <select name="role" className="form-input" value={form.role} onChange={handleChange}>
            <option value="Admin">Admin</option>
            <option value="Student">Student</option>
            <option value="Staff">Staff</option>
          </select>
        </div>

        <div className="form-group">
          <label>Account Status</label>
          <select name="status" className="form-input" value={form.status} onChange={handleChange}>
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select>
        </div>

        <div className="form-group">
          <label>Password</label>
          <input type="password" name="password" className="form-input" value={form.password} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Confirm Password</label>
          <input type="password" name="confirmPassword" className="form-input" value={form.confirmPassword} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Profile Picture (optional)</label>
          <input type="file" accept="image/*" onChange={handleFileChange} />
          <div className="profile-preview">
            <img
              src={previewUrl || defaultProfilePicture}
              alt="Preview"
            />
          </div>
        </div>

        <div className="form-group">
          <button type="submit" className="create-account-btn">Create Account</button>
          <button type="button" onClick={handleReset} className="create-account-btn" style={{ backgroundColor: '#6b7280', marginLeft: '10px' }}>
            Reset
          </button>
        </div>
      </form>
    </div>
  );
};

export default CreateUserAccounts;
