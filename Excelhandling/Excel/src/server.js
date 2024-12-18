const express = require('express');
const multer = require('multer');
const { Pool } = require('pg');
const fs = require('fs');
const xlsx = require('xlsx');
const csvParser = require('csv-parser');

// Set up PostgreSQL connection pool
const pool = new Pool({
  user: 'postgres',     
  host: 'localhost',
  database: 'excelhandling',
  password: '1234',
  port: 5432,                
});

// Set up Multer to handle file uploads
const storage = multer.memoryStorage(); // Use memoryStorage to keep the file in memory (for binary data)
const upload = multer({ storage });

const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));


//  validate data types
const validateDataType = (value, expectedType) => {
  switch (expectedType) {
    case 'string':
      return typeof value === 'string';
    case 'integer':
      return Number.isInteger(parseInt(value, 10));
    case 'double':
      return !isNaN(value) && parseFloat(value) !== parseInt(value, 10);
    case 'float':
      return !isNaN(value);
    case 'date':
      return !isNaN(Date.parse(value));
    case 'boolean':
      return value === 'true' || value === 'false' || typeof value === 'boolean';
    case 'object':
      return typeof value === 'object' && value !== null && !Array.isArray(value);
    default:
      return false;
  }
};

// Column validation mapping
const columnValidation = {
  0: 'string',   // Column A: String
  1: 'integer',  // Column B: Integer
  2: 'double',   // Column C: Double
  3: 'float',    // Column D: Float
  4: 'date',     // Column E: Date
  5: 'boolean',  // Column F: Boolean
  6: 'object',   // Column G: Object
};

// Endpoint to handle file upload
app.post('/upload', upload.single('file'), async (req, res) => {
  const { name, email, phone } = req.body;
  const file = req.file;

  if (!file) {
    return res.status(400).json({ errors: ['Please upload an Excel file'] });
  }

  const fileName = file.originalname.toLowerCase();
  const isCSV = fileName.endsWith('.csv');
  const isExcel = fileName.endsWith('.xlsx') || fileName.endsWith('.xls');

  if (!isCSV && !isExcel) {
    return res.status(400).json({ errors: ['Only Excel (.xlsx, .xls) or CSV files are supported'] });
  }

  const fileData = file.buffer;  // File content in binary format

  try {
    let data = [];
    if (isExcel) {
      // Parse Excel file
      const workbook = xlsx.read(file.buffer, { type: 'buffer' });
      const sheetName = workbook.SheetNames[0];
      data = xlsx.utils.sheet_to_json(workbook.Sheets[sheetName], { header: 1 }); // Raw data as an array
    } else if (isCSV) {
      // Parse CSV file
      const csvData = [];
      const csvStream = csvParser({ headers: false });
      csvStream.write(file.buffer);
      csvStream.on('data', (row) => csvData.push(Object.values(row)));
      await new Promise((resolve) => csvStream.on('end', resolve));
      data = csvData;
    }


      // Skip the first row if it contains headers
      const errors = [];
      for (let rowIndex = 1; rowIndex < data.length; rowIndex++) {
        const row = data[rowIndex];
  
        for (let colIndex = 0; colIndex < row.length; colIndex++) {
          const cellValue = row[colIndex];
          const expectedType = columnValidation[colIndex];
  
          if (expectedType && !validateDataType(cellValue, expectedType)) {
            errors.push(
              `Row ${rowIndex + 1}, Column ${String.fromCharCode(65 + colIndex)}: Expected ${expectedType}, found ${typeof cellValue}`
            );
          }
        }
      }

    // If errors exist, return them
    if (errors.length > 0) {
      return res.status(400).json({ errors });
    }

    // Save form data and file data (binary) to PostgreSQL
    const result = await pool.query(
      'INSERT INTO file_uploads (name, email, phone, file_data) VALUES ($1, $2, $3, $4) RETURNING id',
      [name, email, phone, fileData]
    );

    const uploadedId = result.rows[0].id;
    res.status(200).json({ message: `File uploaded successfully! ID: ${uploadedId}` });

  } catch (error) {
    console.error(error);
    res.status(500).json({ errors: ['An unexpected error occurred'] });
  }
});

// Route to retrieve the file from the database by ID
app.get('/file/:id', async (req, res) => {
    const { id } = req.params;
  
    try {
      // Query to retrieve file data from the database
      const result = await pool.query('SELECT file_data FROM file_uploads WHERE id = $1', [id]);
  
      // Check if the file exists in the database
      if (result.rows.length > 0) {
        const fileData = result.rows[0].file_data;
  
        // Set the correct content type for the file, e.g., Excel files
        res.setHeader('Content-Type', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');  // For .xlsx files
  
        // Send the file as a response
        res.send(fileData);
      } else {
        // If file is not found
        res.status(404).send('File not found');
      }
  
    } catch (error) {
      console.error(error);
      res.status(500).send('Error retrieving file');
    }
  });
  
  
// Start server
const port = 5000;
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});


/* 

const express = require('express');
const multer = require('multer');
const { Pool } = require('pg');
const xlsx = require('xlsx');
const csvParser = require('csv-parser');

// PostgreSQL connection
const pool = new Pool({
  user: 'postgres',
  host: 'localhost',
  database: 'filehandling',
  password: '1234',
  port: 5432,
});

// Set up Multer for file uploads
const storage = multer.memoryStorage(); // Store files in memory
const upload = multer({ storage });

const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Function to validate data types
const validateDataType = (value, expectedType) => {
  switch (expectedType) {
    case 'string':
      return typeof value === 'string';
    case 'integer':
      return Number.isInteger(parseInt(value, 10));
    case 'double':
      return !isNaN(value) && parseFloat(value) !== parseInt(value, 10);
    case 'float':
      return !isNaN(value);
    case 'date':
      return !isNaN(Date.parse(value));
    case 'boolean':
      return value === 'true' || value === 'false' || typeof value === 'boolean';
    case 'object':
      return typeof value === 'object' && value !== null && !Array.isArray(value);
    default:
      return false;
  }
};

// Column validation mapping
const columnValidation = {
  0: 'string',   // Column A: String
  1: 'integer',  // Column B: Integer
  2: 'double',   // Column C: Double
  3: 'float',    // Column D: Float
  4: 'date',     // Column E: Date
  5: 'boolean',  // Column F: Boolean
  6: 'object',   // Column G: Object
};

// Endpoint to handle Excel and CSV uploads
app.post('/upload', upload.single('file'), async (req, res) => {
  const file = req.file;

  if (!file) {
    return res.status(400).json({ errors: ['Please upload a file'] });
  }

  const fileName = file.originalname.toLowerCase();
  const isCSV = fileName.endsWith('.csv');
  const isExcel = fileName.endsWith('.xlsx') || fileName.endsWith('.xls');

  if (!isCSV && !isExcel) {
    return res.status(400).json({ errors: ['Only Excel (.xlsx, .xls) or CSV files are supported'] });
  }

  try {
    let data = [];
    if (isExcel) {
      // Parse Excel file
      const workbook = xlsx.read(file.buffer, { type: 'buffer' });
      const sheetName = workbook.SheetNames[0];
      data = xlsx.utils.sheet_to_json(workbook.Sheets[sheetName], { header: 1 }); // Raw data as an array
    } else if (isCSV) {
      // Parse CSV file
      const csvData = [];
      const csvStream = csvParser({ headers: false });
      csvStream.write(file.buffer);
      csvStream.on('data', (row) => csvData.push(Object.values(row)));
      await new Promise((resolve) => csvStream.on('end', resolve));
      data = csvData;
    }

    // Skip the first row if it contains headers
    const errors = [];
    for (let rowIndex = 1; rowIndex < data.length; rowIndex++) {
      const row = data[rowIndex];

      for (let colIndex = 0; colIndex < row.length; colIndex++) {
        const cellValue = row[colIndex];
        const expectedType = columnValidation[colIndex];

        if (expectedType && !validateDataType(cellValue, expectedType)) {
          errors.push(
            `Row ${rowIndex + 1}, Column ${String.fromCharCode(65 + colIndex)}: Expected ${expectedType}, found ${typeof cellValue}`
          );
        }
      }
    }

    // If errors exist, return them
    if (errors.length > 0) {
      return res.status(400).json({ errors });
    }

    // Save the file to the database
    const result = await pool.query(
      'INSERT INTO file_uploads (file_name, file_data) VALUES ($1, $2) RETURNING id',
      [file.originalname, file.buffer]
    );

    const uploadedId = result.rows[0].id;
    res.status(200).json({ message: `File validated and uploaded successfully! ID: ${uploadedId}` });
  } catch (error) {
    console.error('Error processing file:', error);
    res.status(500).json({ errors: ['An unexpected error occurred'] });
  }
});

// Start the server
const port = 5000;
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});





*/