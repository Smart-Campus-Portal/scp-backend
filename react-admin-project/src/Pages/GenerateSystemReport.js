import React, { useState } from 'react';

const GeneralSystemReport = () => {
  const [submittedReports, setSubmittedReports] = useState([
    {
      issue: 'Broken Chair',
      description: 'Chair in Lab 2 is broken and unstable.',
      category: 'Furniture',
      date: '2025-04-28 10:23 AM'
    },
    {
      issue: 'Projector not working',
      description: 'The projector in Room A1 won’t power on.',
      category: 'Equipment',
      date: '2025-04-29 09:45 AM'
    }
    // You can manually add more mock reports here
  ]);

  const downloadReport = () => {
    const blob = new Blob([JSON.stringify(submittedReports, null, 2)], { type: 'application/json' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'submitted_reports.json';
    link.click();
  };

  return (
    <div className="system-report-container">
      <div className="report-header">
        <h2 className="report-title">General System Reports</h2>
        <p className="report-description">View or download overall system reports here.</p>
      </div>

      <div className="report-content">
        <button className="download-button" onClick={downloadReport}>
          Download Submitted Reports
        </button>

        <div className="report-list">
          {submittedReports.length === 0 ? (
            <p>No submitted reports yet.</p>
          ) : (
            submittedReports.map((r, index) => (
              <div key={index} className="report-item">
                <strong>{r.issue}</strong> - {r.category}<br />
                <em>{r.description}</em><br />
                <small>{r.date}</small>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
};

export default GeneralSystemReport;
