import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from './pages/HomePage'
import MonthEntryPage from './pages/MonthEntryPage'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/month/:year/:month" element={<MonthEntryPage />} />
      </Routes>
    </Router>
  );
}

export default App;
