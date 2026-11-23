import React from 'react';
import { render, screen, fireEvent, waitFor, act } from '@testing-library/react';
import App from '../App';
import CertificateForm from '../components/CertificateForm';
import CertificatePreview from '../components/CertificatePreview';
import '@testing-library/jest-dom';
import * as certificateService from '../services/certificateService';

jest.mock('../services/certificateService');

beforeEach(() => {
  jest.clearAllMocks();
});

test('renders Navbar title and branding', () => {
  render(<App />);
  expect(screen.getByText('Certificate Generator')).toBeInTheDocument();
});

test('renders CertificateForm inputs and submit button', () => {
  render(<App />);
  expect(screen.getByLabelText(/Student Name/i)).toBeInTheDocument();
  expect(screen.getByLabelText(/Course Name/i)).toBeInTheDocument();
  expect(screen.getByRole('button', { name: /Generate Certificate/i })).toBeInTheDocument();
});

test('validates empty inputs on CertificateForm submission', async () => {
  render(<CertificateForm onCertificateCreated={jest.fn()} />);
  fireEvent.click(screen.getByRole('button', { name: /Generate Certificate/i }));
  expect(await screen.findByText(/Both Student Name and Course Name are required/i)).toBeInTheDocument();
});

test('submits certificate creation successfully and invokes callback', async () => {
  const mockCertificate = {
    id: 1,
    certificateNumber: 'CERT-12345',
    studentName: 'Alice Smith',
    courseName: 'Java Spring Boot',
    issueDate: '2026-08-25',
  };
  certificateService.createCertificate.mockResolvedValueOnce(mockCertificate);

  const mockCallback = jest.fn();
  render(<CertificateForm onCertificateCreated={mockCallback} />);

  fireEvent.change(screen.getByLabelText(/Student Name/i), {
    target: { value: 'Alice Smith' },
  });
  fireEvent.change(screen.getByLabelText(/Course Name/i), {
    target: { value: 'Java Spring Boot' },
  });

  await act(async () => {
    fireEvent.click(screen.getByRole('button', { name: /Generate Certificate/i }));
  });

  await waitFor(() => {
    expect(certificateService.createCertificate).toHaveBeenCalledWith({
      studentName: 'Alice Smith',
      courseName: 'Java Spring Boot',
    });
    expect(mockCallback).toHaveBeenCalledWith(mockCertificate);
  });
});

test('renders CertificatePreview with certificate details and handles PDF download', async () => {
  const mockCertificate = {
    id: 1,
    certificateNumber: 'CERT-9999',
    studentName: 'Bob Jones',
    courseName: 'React Fundamentals',
    issueDate: '2026-08-25',
  };
  certificateService.downloadPdf.mockResolvedValueOnce(true);

  render(<CertificatePreview certificate={mockCertificate} />);

  expect(screen.getByText('Bob Jones')).toBeInTheDocument();
  expect(screen.getByText('React Fundamentals')).toBeInTheDocument();
  expect(screen.getByText('CERT-9999')).toBeInTheDocument();

  const downloadBtn = screen.getByRole('button', { name: /Download PDF/i });

  await act(async () => {
    fireEvent.click(downloadBtn);
  });

  await waitFor(() => {
    expect(certificateService.downloadPdf).toHaveBeenCalledWith('CERT-9999');
  });
});
