import React, { useState } from 'react';
import { useUserContext } from '../Context/UserContext';
import '../Styles/DeleteUserAccounts.css'; // Ensure the CSS path is correct

const DeleteUserAccounts = () => {
  const { users, deleteUser } = useUserContext();
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredUsers, setFilteredUsers] = useState([]);
  const [selectedUserId, setSelectedUserId] = useState(null);

  // Handle the search button click
  const handleSearchClick = () => {
    const lowercasedSearchTerm = searchTerm.toLowerCase();
    const filtered = users.filter(user =>
      user.fullName.toLowerCase().includes(lowercasedSearchTerm) ||
      user.email.toLowerCase().includes(lowercasedSearchTerm)
    );
    setFilteredUsers(filtered);
  };

  // Handle the deletion confirmation
  const handleDeleteClick = (userId) => {
    setSelectedUserId(userId);
  };

  const confirmDelete = () => {
    deleteUser(selectedUserId);
    setSelectedUserId(null);
    setSearchTerm('');  // Clear the search term after deletion
    setFilteredUsers([]);  // Reset the filtered users list
  };

  const cancelDelete = () => {
    setSelectedUserId(null);
  };

  return (
    <div className="delete-container">
      <div className="delete-header">
        <h2 className="delete-title">Delete User Account</h2>
      </div>

      {/* Search Bar and Search Button */}
      <div className="input-group">
        <label htmlFor="search">Search by Name or Email</label>
        <input
          id="search"
          type="text"
          placeholder="Enter name or email"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      {/* Search Button */}
      <div className="button-group">
        <button className="delete-button" onClick={handleSearchClick}>
          Search
        </button>
      </div>

      {/* Display filtered users */}
      <div className="delete-form">
        {filteredUsers.length > 0 ? (
          filteredUsers.map((user) => (
            <div key={user.id} className="input-group">
              <label>{user.fullName} - {user.email}</label>
              <div className="button-group">
                <button
                  className="delete-button"
                  onClick={() => handleDeleteClick(user.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))
        ) : (
          <p>No users found matching your search criteria.</p>
        )}
      </div>

      {/* Confirmation Dialog */}
      {selectedUserId && (
        <div className="confirmation-dialog">
          <p>Are you sure you want to delete this user?</p>
          <div className="button-group">
            <button className="delete-button" onClick={confirmDelete}>
              Yes, Delete
            </button>
            <button className="cancel-button" onClick={cancelDelete}>
              Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default DeleteUserAccounts;
