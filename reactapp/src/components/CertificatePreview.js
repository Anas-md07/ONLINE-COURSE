import React, { useState } from 'react';
import { downloadPdf } from '../services/certificateService';

const CertificatePreview = ({ certificate }) => {
  const [downloading, setDownloading] = useState(false);
  const [downloadError, setDownloadError] = useState('');

  if (!certificate) {
    return null;
  }

  const token = certificate.certificateNumber;

  const handleDownload = async () => {
    if (!token) {
      setDownloadError('Certificate token is missing.');
      return;
    }

    setDownloadError('');
    setDownloading(true);

    try {
      await downloadPdf(token);
    } catch (error) {
      console.error(error);
      setDownloadError('Failed to download PDF certificate.');
    } finally {
      setDownloading(false);
    }
  };

  return (
    <div className="card preview-card" id="certificate-preview">

      <div className="certificate-frame">

        <div className="certificate-header">
          <div className="badge-icon">📜</div>

          <h3>Certificate of Completion</h3>

          <p className="subtitle">
            This is proudly presented to
          </p>
        </div>

        <div className="certificate-body">

          <h1 className="student-name">
            {certificate.studentName}
          </h1>

          <p className="completion-text">
            for successfully completing the course
          </p>

          <h2 className="course-name">
            {certificate.courseName}
          </h2>

        </div>

        <div className="certificate-footer">

          <div className="cert-detail">
            <span className="detail-label">
              Issue Date:
            </span>

            <span className="detail-value">
              {certificate.issueDate || 'N/A'}
            </span>
          </div>

          <div className="cert-detail">
            <span className="detail-label">
              Access Token:
            </span>

            <span className="detail-value token-code">
              {token || 'N/A'}
            </span>
          </div>

        </div>

      </div>

      {downloadError && (
        <div
          className="alert alert-error"
          style={{ marginTop: '15px' }}
        >
          {downloadError}
        </div>
      )}

      <div className="action-row">

        <button
          className="btn btn-secondary download-btn"
          onClick={handleDownload}
          disabled={downloading}
        >
          {downloading
            ? 'Downloading...'
            : 'Download PDF'}
        </button>

      </div>

    </div>
  );
};

export default CertificatePreview;