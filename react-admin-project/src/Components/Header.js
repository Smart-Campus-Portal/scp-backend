import React from 'react';
import { Link } from 'react-router-dom'; // Import Link for navigation

const Header = () => {
  return (
    <header className="header-container">
      <div className="logosec">
        <Link to="/" className="logo">Admin</Link>
      </div>

      <div className="message">
        <div className="circle"></div>
        {/* Notification button */}
        <Link to="/notifications" className="notification-btn">
          <img
            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210183322/8.png"
            className="icn"
            alt="notification-icon"
          />
        </Link>
        
        <div className="dp">
          <img
            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210180014/profile-removebg-preview.png"
            className="dpicn"
            alt="profile"
          />
        </div>
      </div>
    </header>
  );
};

export default Header;
