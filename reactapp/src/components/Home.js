import React, { useState } from 'react';
import CertificateForm from './CertificateForm';
import CertificatePreview from './CertificatePreview';

const Home = () => {
  const [certificate, setCertificate] = useState(null);

  return (
    <main className="home-container">
      <div className="hero-section">
        <h1>Online Course Certificate Generator</h1>
        <p>
          Generate, preview, and download official course completion certificates.
        </p>
      </div>

      <div className="content-grid">
        <CertificateForm
          onCertificateCreated={setCertificate}
        />

        {certificate && (
          <CertificatePreview
            certificate={certificate}
          />
        )}
      </div>
    </main>
  );
};

export default Home;