import React, { Component } from 'react'
import logo from "../Images/logo.png"
import { Button } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import {checkCredentials} from "../Component/Credentials";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { MDBContainer, MDBRow, MDBCol} from 'mdb-react-ui-kit';

 export default class LoginPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            loginError: false 
        }

    }

    change = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    };

    check = (e) => {
    e.preventDefault();
    if (checkCredentials(this.state.username, this.state.password)) {
        this.props.onLogin(this.state.username);
        toast.success('Login successful', {
            position: 'top-right',
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
          });
    } else {
        this.setState({ loginError: true });
        toast.error('Invalid credentials', {
            position: 'top-right',
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
          });
    }
  };


    render() {
        return (
            <MDBContainer className="my-5 gradient-form">
                <MDBRow>
                    <MDBCol col='6' className="mb-5 d-flex justify-content-center" style={{ backgroundColor: "white" }}>
                        <div className="d-flex flex-column ms-5">
                            <div className="text-center">
                                <img className='mt-5 py-3' src={logo}
                                    style={{ width: '185px' }} alt="logo" />
                                <h4 className="mt-1 mb-4 pb-1">The CricInfo Team</h4>
                            </div>
                            <p>Please login to your account</p>
                            <Form.Control required className='mb-4' id='form1' type='text' placeholder='Username' name="username" onChange={this.change} />
                            <Form.Control required className='mb-4' id='form2' type='password' placeholder='Password' name="password" onChange={this.change} />
                            {this.state.loginError && <p className="text-danger">Invalid credentials!</p>}
                            <div className="text-center pt-1 mb-5 pb-1">
                                <Button className="mb-4 w-100 gradient-custom-2" onClick={this.check}>Log in </Button>
                                <a className="text-muted" href="#!">Forgot password?</a>
                            </div>
                            <div className="d-flex flex-row align-items-center justify-content-center pb-4 mb-4">
                                <p className="mb-0">Don't have an account?</p>
                                <Button outline className='mx-2' variant="danger">
                                    Create Account
                                </Button>
                            </div>
                        </div>
                    </MDBCol>                   
                <MDBCol col='6' className="mb-5">
                        <div className="d-flex flex-column  justify-content-center gradient-custom-2 h-100 mb-4">
                            <div className="text-white px-3 py-4 p-md-5 mx-md-4">
                                <h4 class="mb-4">Game on! Teams, players, and excitement.</h4>
                                <p class=" mb-0">Dive into a comprehensive sports experience with our app. Discover in-depth player profiles, real-time team updates, and captivating game analyses. Stay ahead with stats, and profile of your favorite teams and players. Elevate your passion for sports with every tap and stay connected to the heart of the game.
                                </p>
                            </div>
                        </div>

                    </MDBCol>

                </MDBRow>

            </MDBContainer>
        )
    }
}
