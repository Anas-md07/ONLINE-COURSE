import React, { useState } from 'react';
import { createCertificate } from '../services/certificateService';

const CertificateForm = ({ onCertificateCreated }) => {
  const [studentName, setStudentName] = useState('');
  const [courseName, setCourseName] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');

    if (!studentName.trim() || !courseName.trim()) {
      setError('Both Student Name and Course Name are required.');
      return;
    }

    setLoading(true);
    try {
      const payload = {
        studentName: studentName.trim(),
        courseName: courseName.trim(),
      };
      const result = await createCertificate(payload);
      setSuccess('Certificate generated successfully!');
      if (onCertificateCreated) {
        onCertificateCreated(result);
      }
      setStudentName('');
      setCourseName('');
    } catch (err) {
      setError('Failed to generate certificate. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card form-card">
      <h2>Generate Certificate</h2>
      {error && <div className="alert alert-error" role="alert">{error}</div>}
      {success && <div className="alert alert-success" role="status">{success}</div>}

      <form onSubmit={handleSubmit} noValidate>
        <div className="form-group">
          <label htmlFor="studentName">Student Name *</label>
          <input
            id="studentName"
            type="text"
            className="form-control"
            placeholder="Enter Student Full Name"
            value={studentName}
            onChange={(e) => setStudentName(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="courseName">Course Name *</label>
          <input
            id="courseName"
            type="text"
            className="form-control"
            placeholder="Enter Course Title"
            value={courseName}
            onChange={(e) => setCourseName(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary" disabled={loading}>
          {loading ? 'Generating...' : 'Generate Certificate'}
        </button>
      </form>
    </div>
  );
};

export default CertificateForm;
