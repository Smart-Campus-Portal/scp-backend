import React, { useState, useEffect } from 'react';
import { useUserContext } from '../Context/UserContext'; // Assuming this path is correct

const UpdateAccountDetails = () => {
  const { users, createUser, deleteUser } = useUserContext();
  
  // Track the currently selected user
  const [selectedUser, setSelectedUser] = useState(null);

  // Track form data
  const [title, setTitle] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [race, setRace] = useState('');
  const [dob, setDob] = useState('');
  const [nationality, setNationality] = useState('');
  const [disability, setDisability] = useState('');
  const [message, setMessage] = useState('');

  // Set the form data when a user is selected
  useEffect(() => {
    if (selectedUser) {
      setTitle(selectedUser.title || '');
      setName(selectedUser.fullName || '');
      setEmail(selectedUser.email || '');
      setPhone(selectedUser.phone || '');
      setAddress(selectedUser.address || '');
      setRace(selectedUser.race || '');
      setDob(selectedUser.dob || '');
      setNationality(selectedUser.nationality || '');
      setDisability(selectedUser.disability || '');
    }
  }, [selectedUser]);

  const handleSubmit = (e) => {
    e.preventDefault();

    // Update user information
    const updatedUser = {
      ...selectedUser,
      title,
      fullName: name,
      email,
      phone,
      address,
      race,
      dob,
      nationality,
      disability,
    };

    // Update the user in the context (e.g., replace the user with updated info)
    createUser(updatedUser); // Assuming createUser can be used for updating

    setMessage('Account details updated successfully!');
  };

  // Handle selecting a user to update
  const handleUserSelection = (userId) => {
    const user = users.find((u) => u.id === userId);
    setSelectedUser(user);
  };

  return (
    <div className="update-account-container">
      <h2>Update Account Details</h2>
      <p>Modify your account information below. Make sure to provide the most up-to-date details.</p>

      {/* Dropdown to select a user */}
      <div className="select-user-container">
        <label>Select User to Update:</label>
        <select onChange={(e) => handleUserSelection(Number(e.target.value))} required>
          <option value="">Select User</option>
          {users.map((user) => (
            <option key={user.id} value={user.id}>
              {user.fullName} ({user.email})
            </option>
          ))}
        </select>
      </div>

      {selectedUser && (
        <form onSubmit={handleSubmit} className="update-form">
          <div className="form-group">
            <label htmlFor="title">Title:</label>
            <select
              id="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
            >
              <option value="">Select Title</option>
              <option value="Mr">Mr</option>
              <option value="Mrs">Mrs</option>
              <option value="Miss">Miss</option>
              <option value="Dr">Dr</option>
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="name">Full Name:</label>
            <input
              type="text"
              id="name"
              placeholder="Enter your full name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="email">Email Address:</label>
            <input
              type="email"
              id="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="phone">Phone Number:</label>
            <input
              type="tel"
              id="phone"
              placeholder="Enter your phone number"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="address">Address:</label>
            <textarea
              id="address"
              placeholder="Enter your address"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              required
            ></textarea>
          </div>

          <div className="form-group">
            <label htmlFor="race">Race:</label>
            <input
              type="text"
              id="race"
              placeholder="Enter your race"
              value={race}
              onChange={(e) => setRace(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="dob">Date of Birth:</label>
            <input
              type="date"
              id="dob"
              value={dob}
              onChange={(e) => setDob(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="nationality">Nationality:</label>
            <input
              type="text"
              id="nationality"
              placeholder="Enter your nationality"
              value={nationality}
              onChange={(e) => setNationality(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="disability">Disability Status:</label>
            <select
              id="disability"
              value={disability}
              onChange={(e) => setDisability(e.target.value)}
              required
            >
              <option value="">Select Disability Status</option>
              <option value="None">None</option>
              <option value="Physical">Physical</option>
              <option value="Mental">Mental</option>
              <option value="Other">Other</option>
            </select>
          </div>

          <button type="submit" className="submit-button">Update Details</button>

          {message && <p className="success-message">{message}</p>}
        </form>
      )}
    </div>
  );
};

export default UpdateAccountDetails;
