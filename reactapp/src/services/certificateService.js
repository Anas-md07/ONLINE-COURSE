import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/certificates';

export const createCertificate = async (certificateData) => {
  try {
    const response = await axios.post(
      API_BASE_URL,
      certificateData
    );

    return response.data;
  } catch (error) {
    console.error(
      'Error creating certificate:',
      error
    );

    throw error;
  }
};

export const downloadPdf = async (token) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/${token}/download`,
      {
        responseType: 'blob',
      }
    );

    const blob = new Blob(
      [response.data],
      {
        type: 'application/pdf',
      }
    );

    const url =
      window.URL.createObjectURL(blob);

    const link =
      document.createElement('a');

    link.href = url;
    link.download = 'certificate.pdf';

    document.body.appendChild(link);

    link.click();

    link.remove();

    window.URL.revokeObjectURL(url);

  } catch (error) {

    console.error(
      'Error downloading certificate PDF:',
      error
    );

    throw error;
  }
};

const certificateService = {
  createCertificate,
  downloadPdf,
};

export default certificateService;