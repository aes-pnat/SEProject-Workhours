import axios from "axios";
import User from './User';
//promijeniti u environment variablu API_URL
const API_URL = process.env.REACT_APP_BACKEND_URL;

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "/login", {
        "username":username,
        "password":password
      })
      .then(response => {
        if (response.data.accessToken) {
          User.saveToken(token);

        }
        return response.data;
      });
  }

  logout() {
    User.removeToken(token);
  }

  register(username, email, password) {
    return axios.post(API_URL + "/register", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();