import React, { createContext, useContext, useState } from 'react';

// Create the context
const UserContext = createContext();

// Custom hook to use the UserContext
export const useUserContext = () => useContext(UserContext);

// UserProvider component to wrap around your app
export const UserProvider = ({ children }) => {
  const [users, setUsers] = useState([]);
  const [activities, setActivities] = useState([]);

  // Generate a random ID
  const generateUserId = () => Math.floor(Math.random() * 10000);

  // Log activity
  const logActivity = (type, detail) => {
    const activity = {
      type,
      detail,
      time: new Date().toLocaleString(),
    };
    setActivities(prev => [activity, ...prev.slice(0, 19)]);
  };

  // Create user
  const createUser = (user) => {
    const newUser = { ...user, id: generateUserId() };
    setUsers(prev => [...prev, newUser]);
    logActivity('Account Created', `User: ${user.fullName}`);
  };

  // Delete user
  const deleteUser = (userId) => {
    const user = users.find(u => u.id === userId);
    if (user) {
      setUsers(prev => prev.filter(u => u.id !== userId));
      logActivity('Account Deleted', `User: ${user.fullName}`);
    }
  };

  return (
    <UserContext.Provider value={{
      users,
      activities,
      createUser,
      deleteUser
    }}>
      {children}
    </UserContext.Provider>
  );
};
