import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {

  register(firstName, lastName, npi, address, phone, email) {
    return axios.post(API_URL + "signup", {
      firstName,
      lastName,
      npi,
      address,
      phone,
      email
    });
  }
}

export default new AuthService();
