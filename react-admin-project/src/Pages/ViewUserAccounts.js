import React, { useState } from 'react';
import { useUserContext } from '../Context/UserContext'; // Adjust path if necessary
import '../Styles/ViewUserAccounts.css'; // Optional: your styling

const ViewUserAccount = () => {
  const { users } = useUserContext(); // Get user accounts from context
  const [searchTerm, setSearchTerm] = useState('');

  // Filter users based on search term
  const filteredUsers = users.filter((user) =>
    user.fullName.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="view-user-container">
      <h2>View User Accounts</h2>
      <p>Browse and search through all user accounts.</p>

      <div className="search-container">
        <input
          type="text"
          placeholder="Search by name"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-input"
        />
      </div>

      <div className="user-list">
        {filteredUsers.length > 0 ? (
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {filteredUsers.map((user) => (
                <tr key={user.id}>
                  <td>{user.id}</td>
                  <td>{user.fullName}</td>
                  <td>{user.email}</td>
                  <td>{user.role}</td>
                  <td>{user.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No users found matching your search.</p>
        )}
      </div>
    </div>
  );
};

export default ViewUserAccount;
