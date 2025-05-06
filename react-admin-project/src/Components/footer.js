import React from 'react';
import '../Styles/footer.css'; // Corrected the path

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">
        <p>&copy; 2025 Admin Dashboard. All rights reserved.</p>
        <div className="footer-links">
          <a href="/help-center" className="footer-link">Help Center</a>
          <a href="/terms" className="footer-link">Terms & Conditions</a>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
