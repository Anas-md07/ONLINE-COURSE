import React from 'react';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Footer from './components/Footer';
import './App.css';

function App() {
  return (
    <div className="app-layout">
      <Navbar />
      <div className="app-content">
        <Home />
      </div>
      <Footer />
    </div>
  );
}

export default App;
