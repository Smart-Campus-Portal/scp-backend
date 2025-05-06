import React from 'react';
import './Logout.css';  // Don't forget to import the CSS

const Logout = () => {
  return (
    <div className="logout-container">
      <h2>Logout</h2>
      <p>Click below to logout from the admin panel.</p>
      <button className="logout-button">Logout</button>
    </div>
  );
};

export default Logout;
