// src/FileUpload.js
import React, { useState } from 'react';
import { useDropzone } from 'react-dropzone';  // Drag-and-drop feature
import axios from 'axios';
import './FileUpload.css'; // Component-specific CSS
    
const FileUpload = () => {
  const [file, setFile] = useState(null);
  const [formData, setFormData] = useState({ name: '', email: '', phone: '' });
  const [errorMessages,setErrorMessages] = useState([]); 
  const [successMessage,setSuccessMessage] = useState('');

   // Handle file drop (drag-and-drop functionality)
   const onDrop = (acceptedFiles) => {
    const selectedFile = acceptedFiles[0];
    if (!selectedFile) {
      setErrorMessages(['No file selected']);
      return;
    }

   // Validate file type
  const allowedTypes = ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'application/vnd.ms-excel', 'text/csv'];
    if (!allowedTypes.includes(selectedFile.type)) {
      setErrorMessages(['Only Excel (.xlsx, .xls) or CSV files are allowed']);
      return;
    }

    setFile(selectedFile);
    setErrorMessages([]); // Clear previous errors
  };

  // Initialize react-dropzone
  const { getRootProps, getInputProps } = useDropzone({
    onDrop,
    accept: '.xlsx,.xls,.csv', // Accept only Excel and csv files
  });

  // Handle input change for form fields
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Handle form submission (you can send the data to a backend if needed)
  const handleSubmit = async(e) => {
    e.preventDefault();

    // Validate form fields
    if (!formData.name || !formData.email || !formData.phone) {
      setErrorMessages(['All fields are required']);
      return;
    }

    if(!file){
      setErrorMessages(['Please upload an Excel or CSV file']);
      return;
    }

    const formDataToSend = new FormData();
    formDataToSend.append('name',formData.name);
    formDataToSend.append('email',formData.email);
    formDataToSend.append('phone',formData.phone);
    formDataToSend.append('file',file);
    
    try{
      const response = await axios.post('http://localhost:5000/upload', formDataToSend, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });

      setSuccessMessage(response.data.message);
      setErrorMessages([]); // Clear previous errors if the upload is successful
      setFile(null);
    } catch (error) {
      if (error.response && error.response.data.errors) {
        setErrorMessages(error.response.data.errors);
      } else {
        setErrorMessages(['An unexpected error occurred']);
      }
      setSuccessMessage('');
    }
  };



  //  console.log('Form Data:', formData);
  //  console.log('Uploaded File:', file);
 //};
    
 

  return (
    <form onSubmit={handleSubmit} className="file-upload-form">
      <div className="form-group">
        <label htmlFor="name">Name</label>
        <input
          type="text"
          id="name"
          name="name"
          value={formData.name}
          onChange={handleInputChange}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="email">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleInputChange}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="phone">Phone Number</label>
        <input
          type="tel"
          id="phone"
          name="phone"
          value={formData.phone}
          onChange={handleInputChange}
          required
        />
      </div>
      
      {/* File Upload  */}
      <div className="form-group">
        <label>Upload Excel File/CSV File</label>
        <div {...getRootProps()} className="dropzone">
          <input {...getInputProps()} />
          <p>{file ? file.name : 'Drag & drop an Excel/CSV file here, or click to select one'}</p>
        </div>
        {/* Fallback for normal upload */}
        <input type="file" onChange={(e) => setFile(e.target.files[0])} accept=".xlsx,.xls,.csv" />
      </div>

      {/* Feedback Message */}
      {errorMessages.length > 0 && (
        <div className="error-messages">
          {errorMessages.map((msg, index) => (
            <p key={index} className="error">{msg}</p>
          ))}
        </div>
      )}

      {successMessage && (
        <div className="success-message">
          <p>{successMessage}</p>
        </div>
      )}

      {/* Submit Button */}
      <button type="submit" className="submit-btn">Submit</button>
    </form>
  );
};

export default FileUpload;
