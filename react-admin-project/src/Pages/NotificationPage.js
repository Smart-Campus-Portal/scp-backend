import React from 'react';
import { useUserContext } from '../Context/UserContext';

const NotificationPage = () => {
  const { activities } = useUserContext();

  return (
    <div className="notifications-container">
      <h1>Notifications</h1>
      {activities.length > 0 ? (
        <ul className="notifications-list">
          {[...activities].reverse().map((notif, index) => (
            <li key={index} className="notification-item">
              <p>{notif.type}: {notif.detail}</p>
              <span>{notif.time}</span>
            </li>
          ))}
        </ul>
      ) : (
        <p>No notifications yet.</p>
      )}
    </div>
  );
};

export default NotificationPage;
