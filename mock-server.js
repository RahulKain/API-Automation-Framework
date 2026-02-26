// Mock API Server for DemoAPI Testing
// This server provides mock endpoints for the User Management API

const express = require('express');
const app = express();
const PORT = 8080;

// Middleware
app.use(express.json());

// In-memory database
let users = [
  { id: 1, name: 'John Doe', email: 'john@example.com', age: 30, phone: '1234567890', address: '123 Main St', createdAt: new Date().toISOString() },
  { id: 2, name: 'Jane Smith', email: 'jane@example.com', age: 28, phone: '9876543210', address: '456 Oak Ave', createdAt: new Date().toISOString() },
  { id: 3, name: 'Bob Johnson', email: 'bob@example.com', age: 35, phone: '5555555555', address: '789 Pine Rd', createdAt: new Date().toISOString() }
];

let nextUserId = 4;

// Logging middleware
app.use((req, res, next) => {
  console.log(`[${new Date().toISOString()}] ${req.method} ${req.path}`);
  next();
});

// GET /api/v1/users - Get all users
app.get('/api/v1/users', (req, res) => {
  const page = req.query.page ? parseInt(req.query.page) : 1;
  const pageSize = req.query.pageSize ? parseInt(req.query.pageSize) : 10;
  
  const start = (page - 1) * pageSize;
  const end = start + pageSize;
  const paginatedUsers = users.slice(start, end);
  
  res.status(200).json(paginatedUsers);
});

// GET /api/v1/users/search - Search users by name
app.get('/api/v1/users/search', (req, res) => {
  const name = req.query.name;
  
  if (!name) {
    return res.status(400).json({ error: 'BadRequest', message: 'Name parameter is required' });
  }
  
  const filteredUsers = users.filter(user => 
    user.name.toLowerCase().includes(name.toLowerCase())
  );
  
  res.status(200).json(filteredUsers);
});

// GET /api/v1/users/:userId - Get user by ID
app.get('/api/v1/users/:userId', (req, res) => {
  const userId = parseInt(req.params.userId);
  const user = users.find(u => u.id === userId);
  
  if (!user) {
    return res.status(404).json({ error: 'NotFound', message: `User with id ${userId} not found` });
  }
  
  res.status(200).json(user);
});

// POST /api/v1/users - Create new user
app.post('/api/v1/users', (req, res) => {
  const { name, email, age, phone, address } = req.body;
  
  console.log('POST /api/v1/users - Body:', JSON.stringify(req.body));
  
  // Validation
  if (!name || !email || age === undefined) {
    console.log('Validation failed: missing required fields');
    return res.status(400).json({ error: 'BadRequest', message: 'name, email, and age are required' });
  }
  
  // Email format validation
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    console.log('Email validation failed for:', email);
    return res.status(400).json({ error: 'BadRequest', message: 'Invalid email format', details: { field: 'email' } });
  }
  
  // Check if email already exists
  if (users.some(u => u.email === email)) {
    console.log('Email already exists:', email);
    return res.status(409).json({ error: 'Conflict', message: 'Email already exists' });
  }
  
  const newUser = {
    id: nextUserId++,
    name,
    email,
    age: parseInt(age),
    phone: phone || null,
    address: address || null,
    createdAt: new Date().toISOString(),
    updatedAt: new Date().toISOString()
  };
  
  console.log('User created:', newUser.id);
  users.push(newUser);
  res.status(201).json(newUser);
});

// PUT /api/v1/users/:userId - Update user
app.put('/api/v1/users/:userId', (req, res) => {
  const userId = parseInt(req.params.userId);
  const user = users.find(u => u.id === userId);
  
  if (!user) {
    return res.status(404).json({ error: 'NotFound', message: `User with id ${userId} not found` });
  }
  
  const { name, email, age, phone, address } = req.body;
  
  // Email format validation if email is provided
  if (email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      return res.status(400).json({ error: 'BadRequest', message: 'Invalid email format', details: { field: 'email' } });
    }
  }
  
  // Update fields
  if (name) user.name = name;
  if (email) user.email = email;
  if (age) user.age = age;
  if (phone) user.phone = phone;
  if (address) user.address = address;
  user.updatedAt = new Date().toISOString();
  
  res.status(200).json(user);
});

// DELETE /api/v1/users/:userId - Delete user
app.delete('/api/v1/users/:userId', (req, res) => {
  const userId = parseInt(req.params.userId);
  const index = users.findIndex(u => u.id === userId);
  
  if (index === -1) {
    return res.status(404).json({ error: 'NotFound', message: `User with id ${userId} not found` });
  }
  
  users.splice(index, 1);
  res.status(204).send();
});

// Health check endpoint
app.get('/health', (req, res) => {
  res.status(200).json({ status: 'OK', message: 'Mock API Server is running' });
});

// Root endpoint
app.get('/', (req, res) => {
  res.status(200).json({
    message: 'Mock API Server for DemoAPI Testing',
    version: '1.0.0',
    endpoints: {
      users: '/api/v1/users',
      userById: '/api/v1/users/:userId',
      searchUsers: '/api/v1/users/search?name=',
      health: '/health'
    }
  });
});

// Error handling middleware
app.use((err, req, res, next) => {
  console.error('Error:', err);
  res.status(500).json({ error: 'InternalServerError', message: err.message });
});

// Start server
app.listen(PORT, () => {
  console.log(`\n╔═══════════════════════════════════════════║`);
  console.log(`║ Mock API Server Running                   ║`);
  console.log(`║ Port: ${PORT}                             ║`);
  console.log(`║ Base URL: http://localhost:${PORT} ║`);
  console.log(`║ API Base: http://localhost:${PORT}/api/v1 ║`);
  console.log(`╚═══════════════════════════════════════════╝\n`);
  console.log('Endpoints:');
  console.log('  GET    /api/v1/users');
  console.log('  GET    /api/v1/users/:userId');
  console.log('  POST   /api/v1/users');
  console.log('  PUT    /api/v1/users/:userId');
  console.log('  DELETE /api/v1/users/:userId');
  console.log('  GET    /api/v1/users/search?name=');
  console.log('  GET    /health');
  console.log('\nPress Ctrl+C to stop server\n');
});

module.exports = app;
