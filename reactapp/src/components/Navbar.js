import React from 'react';

const Navbar = () => {
  return (
    <header className="navbar">
      <div className="navbar-container">
        <a href="/" className="navbar-brand">
          <span className="brand-icon">🎓</span>
          <span className="brand-title">Certificate Generator</span>
        </a>
        <nav className="navbar-nav">
          <a href="/" className="nav-link active">Home</a>
        </nav>
      </div>
    </header>
  );
};

export default Navbar;
