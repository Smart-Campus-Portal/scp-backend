import React from 'react';
import { useUserContext } from '../Context/UserContext';

const DashboardHome = () => {
  const { users, activities } = useUserContext();

  return (
    <div className="dashboard-home-container">
      <h1>Welcome to the Admin Dashboard</h1>

      <div className="dashboard-sections">
        <div className="dashboard-card">
          <h2>Recent Created Accounts</h2>
          {users.length > 0 ? (
            <ul>
              {users.map((user, index) => (
                <li key={index}>
                  <strong>Name:</strong> {user.fullName} | <strong>Email:</strong> {user.email}
                </li>
              ))}
            </ul>
          ) : (
            <p>No accounts created yet.</p>
          )}
        </div>

        <div className="dashboard-card">
          <h2>Recent Activities</h2>
          {activities.length > 0 ? (
            <ul>
              {activities.map((activity, index) => (
                <li key={index}>
                  <strong>{activity.type}</strong>: {activity.detail} <em>{activity.time}</em>
                </li>
              ))}
            </ul>
          ) : (
            <p>No activities yet.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default DashboardHome;
