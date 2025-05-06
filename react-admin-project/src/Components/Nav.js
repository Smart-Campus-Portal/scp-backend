import React from 'react';
import { FaTachometerAlt, FaFileAlt, FaBug, FaUsers, FaUserEdit, FaUserTimes, FaUserPlus, FaSignOutAlt } from 'react-icons/fa';

const Nav = () => {
    return (
        <div className="navcontainer">
            <nav className="nav">
                <div className="nav-upper-options">

                    <div className="nav-option option1">
                        <FaTachometerAlt className="nav-icon" />
                        <h3 href="DashboardHome.js">Dashboard</h3>
                    </div>

                    <div className="option2 nav-option">
                        <FaFileAlt className="nav-icon" />
                        <h3>Generate System Report</h3>
                    </div>

                    <div className="nav-option option3">
                        <FaBug className="nav-icon" />
                        <h3>Report an issue</h3>
                    </div>

                    <div className="nav-option option4">
                        <FaUsers className="nav-icon" />
                        <h3>View User Accounts</h3>
                    </div>

                    <div className="nav-option option5">
                        <FaUserEdit className="nav-icon" />
                        <h3>Update Account Details</h3>
                    </div>

                    <div className="nav-option option6">
                        <FaUserTimes className="nav-icon" />
                        <h3>Delete User Accounts</h3>
                    </div>

                    <div className="nav-option option7">
                        <FaUserPlus className="nav-icon" />
                        <h3>Create User Accounts</h3>
                    </div>

                    <div className="nav-option logout">
                        <FaSignOutAlt className="nav-icon" />
                        <h3>Logout</h3>
                    </div>

                </div>
            </nav>
        </div>
    );
};

export default Nav;
