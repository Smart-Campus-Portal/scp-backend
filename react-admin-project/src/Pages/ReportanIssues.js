import React, { useState } from 'react';

const ReportanIssue = ({ onReport }) => {
  const [issue, setIssue] = useState('');
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    const newIssue = {
      issue,
      description,
      category,
      date: new Date().toLocaleString(),
    };

    if (typeof onReport === 'function') {
      onReport(newIssue);
    }

    setMessage('Issue reported successfully!');
    setIssue('');
    setDescription('');
    setCategory('');
  };

  return (
    <div className="report-issue-container">
      <h2>Report an Issue</h2>
      <form onSubmit={handleSubmit} className="report-form">
        <div className="form-group">
          <label htmlFor="category">Category:</label>
          <select id="category" value={category} onChange={(e) => setCategory(e.target.value)} required>
            <option value="">Select</option>
            <option value="Furniture">Furniture</option>
            <option value="Equipment">Equipment</option>
            <option value="Building">Building</option>
            <option value="Other">Other</option>
          </select>
        </div>

        <div className="form-group">
          <label>Issue:</label>
          <input type="text" value={issue} onChange={(e) => setIssue(e.target.value)} required />
        </div>

        <div className="form-group">
          <label>Description:</label>
          <textarea value={description} onChange={(e) => setDescription(e.target.value)} required />
        </div>

        <button type="submit" className="submit-button">Submit</button>
        {message && <p className="success-message">{message}</p>}
      </form>
    </div>
  );
};

export default ReportanIssue;
