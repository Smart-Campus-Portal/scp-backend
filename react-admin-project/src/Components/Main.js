// Main.js

import React from 'react';

const Main = () => {
  return (
    <div className="main">
      <div className="searchbar2">
        <input type="text" placeholder="Search accounts" />
        <div className="searchbtn">
          <img
            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210180758/Untitled-design-(28).png"
            className="icn srchicn"
            alt="search-button"
          />
        </div>
      </div>

      <div className="box-container">
        <div className="box box1">
          <div className="text">
            <h2 className="topic-heading">60.5k</h2>
            <h2 className="topic">Total Users</h2>
          </div>
          <img
            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210184645/Untitled-design-(31).png"
            alt="Users"
          />
        </div>

        <div className="box box2">
          <div className="text">
            <h2 className="topic-heading">150</h2>
            <h2 className="topic">Active Accounts</h2>
          </div>
          <img
            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210185030/14.png"
            alt="Active"
          />
        </div>

        <div className="box box3">
          <div className="text">
            <h2 className="topic-heading">320</h2>
            <h2 className="topic">Pending Verifications</h2>
          </div>
          <img
            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210184645/Untitled-design-(32).png"
            alt="Pending"
          />
        </div>

        <div className="box box4">
          <div className="text">
            <h2 className="topic-heading">70</h2>
            <h2 className="topic">Suspended Accounts</h2>
          </div>
          <img
            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210185029/13.png"
            alt="Suspended"
          />
        </div>
      </div>

      <div className="report-container">
        <div className="report-header">
          <h1 className="recent-Articles">Recent Account Activities</h1>
          <button className="view">View All</button>
        </div>

        <div className="report-body">
          <div className="report-topic-heading">
            <h3 className="t-op">Username</h3>
            <h3 className="t-op">Login Count</h3>
            <h3 className="t-op">Actions</h3>
            <h3 className="t-op">Status</h3>
          </div>

          <div className="items">
            {[
              ['john_doe', '2.9k', 'Updated Profile', 'Active'],
              ['jane_admin', '1.5k', 'Changed Password', 'Active'],
              ['mike_west', '1.1k', 'Verified Email', 'Active'],
              ['susan_tech', '1.2k', 'Deactivated', 'Suspended'],
              ['mark_view', '2.6k', 'Updated Photo', 'Active'],
              ['lucy_dev', '1.9k', 'Reset Password', 'Active'],
              ['kevin_star', '1.2k', 'Changed Username', 'Active'],
              ['laura_admin', '3.6k', 'Reactivated', 'Active'],
              ['peter_user', '1.3k', 'Created Account', 'Active'],
            ].map(([username, logins, action, status], i) => (
              <div className="item1" key={i}>
                <h3 className="t-op-nextlvl">{username}</h3>
                <h3 className="t-op-nextlvl">{logins}</h3>
                <h3 className="t-op-nextlvl">{action}</h3>
                <h3 className={`t-op-nextlvl label-tag ${status === 'Suspended' ? 'suspended' : 'active'}`}>
                  {status}
                </h3>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
