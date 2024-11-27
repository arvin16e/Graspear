// src/App.js
import React from 'react';
import './App.css';  // Global CSS (optional)
import FileUpload from './FileUpload';  // Import the FileUpload component directly from src

function App() {
  return (
    <div className="App">
      <div className="container">
        <h1 className="app-title">Excel File Upload Form</h1>
        <FileUpload />  {/* Render the FileUpload component */}
      </div>
    </div>
  );
}

export default App;
