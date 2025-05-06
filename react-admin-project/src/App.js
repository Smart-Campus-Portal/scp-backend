import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Import Pages
import DashboardHome from './Pages/DashboardHome';
import DashboardLayoutAdmin from './Components/DashboardLayoutAdmin';
import GenerateSystemReport from './Pages/GenerateSystemReport';
import ReportanIssues from './Pages/ReportanIssues';
import ViewUserAccounts from './Pages/ViewUserAccounts';
import UpdateAccountDetails from './Pages/UpdateAccountDetails';
import DeleteUserAccounts from './Pages/DeleteUserAccounts';
import CreateUserAccounts from './Pages/CreateUserAccounts';
import NotificationPage from './Pages/NotificationPage';


// Import UserProvider (assuming you have it)
import { UserProvider } from './Context/UserContext'; // <-- Correct the path according to your project

// Import CSS
import './Styles/Main.css';
import './Styles/Nav.css';
import './Styles/NotificationPage.css';
import './Styles/DashboardLayoutAdmin.css';
import './Styles/DashboardHome.css';
import './Styles/footer.css';
import './Styles/Header.css';
import './Styles/ReportanIssues.css'; 
import './Styles/GenerateSystemReport.css';
import './Styles/ViewUserAccounts.css';
import './Styles/UpdateAccountDetails.css';
import './Styles/DeleteUserAccounts.css';
import './Styles/CreateUserAccounts.css';
import './Styles/Logout.css';

function App() {
  return (
    <UserProvider>
      <Router>
        <Routes>
          <Route path="/" element={<DashboardLayoutAdmin/>}>
            <Route index element={<DashboardHome />} />
            <Route path="notifications" element={<NotificationPage />} />
            <Route path="generate-report" element={<GenerateSystemReport />} />
            <Route path="report-issue" element={<ReportanIssues />} />
            <Route path="view-users" element={<ViewUserAccounts />} />
            <Route path="update-account" element={<UpdateAccountDetails />} />
            <Route path="delete-user" element={<DeleteUserAccounts />} />
            <Route path="create-user" element={<CreateUserAccounts />} />
          </Route>
        </Routes>
      </Router>
    </UserProvider>
  );
}

export default App;
