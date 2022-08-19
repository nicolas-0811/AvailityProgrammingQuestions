import React, { Component } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./App.css";
import AuthService from "C:/Users/Nicolas/Desktop/Nicolas/College/ICS 499/NewProject/Interview/frontend/src/services/auth.service.js";

 class App extends Component {
  constructor(props) {
    super(props);
    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeFirstName = this.onChangeFirstName.bind(this);
    this.onChangeLastName = this.onChangeLastName.bind(this);
    this.onChangeNpi = this.onChangeNpi.bind(this);
    this.onChangeAddress = this.onChangeAddress.bind(this);
    this.onChangePhone = this.onChangePhone.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.register = this.register.bind(this);

    this.state = {
      firstName: "",
      lastName: "",
      npi: "",
      address: "",
      phone: "",
      email: "",
      successful: false,
      message: ""
    };
  }

  onChangeFirstName(e) {
    this.setState({
      firstName: e.target.value
    });
  }

  onChangeLastName(e) {
    this.setState({
      lastName: e.target.value
    });
  }

  onChangeNpi(e) {
    this.setState({
      npi: e.target.value
    });
  }

  onChangeAddress(e) {
    this.setState({
      address: e.target.value
    });
  }

  onChangePhone(e) {
    this.setState({
      phone: e.target.value
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  register() {
    AuthService.register(
      this.state.firstName,
      this.state.lastName,
      this.state.npi,
      this.state.address,
      this.state.phone,
      this.state.email
    ).then(
      response => {
        this.setState({
          message: response.data.message,
          successful: true
        });
      },
      error => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        this.setState({
          successful: false,
          message: resMessage
        });
      }

    )
  }

  handleRegister(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      AuthService.register(
        this.state.firstName,
        this.state.lastName,
        this.state.npi,
        this.state.address,
        this.state.phone,
        this.state.email
      ).then(
        response => {
          this.setState({
            message: response.data.message,
            successful: true
          });
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            successful: false,
            message: resMessage
          });
        }
      );
    }
  }

  render() {
    const windowWidth = document.documentElement.clientWidth;
    return (
      <div className="container" style={{"width": windowWidth}}>
        <div className="card card-container">
          <img
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            alt="profile-img"
            className="profile-img-card"
          />
          <div className="col-md-18">
                <h2><strong>Registration Form</strong></h2>
                <label htmlFor="firstname">First Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="firstname"
                  required
                  value={this.state.firstName}
                  onChange={this.onChangeFirstName}
                  name="firstname"
                />
                <label htmlFor="firstname">Last Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="lastname"
                  required
                  value={this.state.lastName}
                  onChange={this.onChangeLastName}
                  name="lastname"
                />
                <label htmlFor="npi">NPI Number</label>
                <input
                  type="text"
                  className="form-control"
                  id="npi"
                  required
                  value={this.state.npi}
                  onChange={this.onChangeNpi}
                  name="npi"
                />
                <label htmlFor="address">Address</label>
                <input
                  type="text"
                  className="form-control"
                  id="address"
                  required
                  value={this.state.address}
                  onChange={this.onChangeAddress}
                  name="address"
                />
                <label htmlFor="phone">Telephone Number</label>
                <input
                  type="text"
                  className="form-control"
                  id="phone"
                  required
                  value={this.state.phone}
                  onChange={this.onChangePhone}
                  name="phone"
              />
              <label htmlFor="email">Email</label>
                <input
                  type="text"
                  className="form-control"
                  id="email"
                  required
                  value={this.state.email}
                  onChange={this.onChangeEmail}
                  name="email"
                />

              <div className="col-md-4" style={{padding: 0, left: 0, top: 15}}>
              <button onClick={this.register} className="btn btn-success">
                Submit
              </button>
              <p>{this.state.message}</p>
              </div>
              </div>
        </div>
      </div>
    );
  }
}

export default App;