import axios, { AxiosInstance } from "axios";

const apiClient: AxiosInstance = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
     Accept: "application/json",
    // "content-type": "multipart/form-data",
    "Content-Type": "application/json",
  },
});
export default apiClient;
