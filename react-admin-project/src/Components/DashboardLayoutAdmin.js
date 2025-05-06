import React from 'react';
import { Link, useLocation, Outlet } from 'react-router-dom';
import { FaTachometerAlt, FaFileAlt, FaBug, FaUsers, FaUserEdit, FaUserTimes, FaUserPlus, FaSignOutAlt } from 'react-icons/fa';
import Header from './Header';
import Footer from './footer';
import '../Styles/DashboardLayoutAdmin.css'; // Updated path

const navItems = [
  { path: '', label: 'Dashboard', icon: <FaTachometerAlt /> },
  { path: 'generate-report', label: 'Generate System Report', icon: <FaFileAlt /> },
  { path: 'report-issue', label: 'Report an Issue', icon: <FaBug /> },
  { path: 'view-users', label: 'View User Accounts', icon: <FaUsers /> },
  { path: 'update-account', label: 'Update Account Details', icon: <FaUserEdit /> },
  { path: 'delete-user', label: 'Delete User Accounts', icon: <FaUserTimes /> },
  { path: 'create-user', label: 'Create User Accounts', icon: <FaUserPlus /> },
  { path: 'logout', label: 'Logout', icon: <FaSignOutAlt /> },
];

const DashboardLayoutAdmin = () => {
  const location = useLocation();

  // Helper function to check active link
  const isActive = (path) => {
    if (path === '') return location.pathname === '/';
    return location.pathname.includes(path);
  };

  return (
    <div className="dashboard-container">
      <Header />

      <div className="dashboard-main">
        <aside className="dashboard-sidebar">
          <nav className="nav">
            {navItems.map((item) => (
              <Link
                key={item.path}
                to={`/${item.path}`}
                className={`nav-item ${isActive(item.path) ? 'active' : ''}`}
              >
                <span className="nav-icon">{item.icon}</span>
                <span>{item.label}</span>
              </Link>
            ))}
          </nav>
        </aside>

        <div className="dashboard-content">
          {/* This is where nested routes will be rendered */}
          <Outlet />
        </div>
      </div>

      <Footer />
    </div>
  );
};

export default DashboardLayoutAdmin;