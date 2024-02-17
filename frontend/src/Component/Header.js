
import Nav from 'react-bootstrap/Nav';
import React, { Component } from 'react';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Button from 'react-bootstrap/Button';
import Image from 'react-bootstrap/Image';
import Modal from 'react-modal';


import logo from '../Images/logo.png'
import ConfirmModal from './ConfirmModal';

export default class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalOpen: false
        }
    }

    openModal = () => {
        this.setState({ isModalOpen: true })
    };

    closeModal = () => {
        this.setState({ isModalOpen: false })
    };

    confirmLogout = () => {
        this.closeModal(); // Close the modal
        this.props.onLogout();
    };

    render() {
        const appTextStyle = {
            marginLeft: 'auto',
            marginRight: 'auto',
            color: '#e7d5d5',
            fontSize: '30px',
            fontWeight: 'bold'
        };

        return (
            <>
                <Navbar bg="red" expand="lg" style={{
                    backgroundColor: "rgb(207 242 210)", padding: "8px 2px", display: 'flex',
                    justifyContent: 'space-between',
                }}>
                    {/* <Image src={logo} height="45px" className='ml-3' /> */}
                    <div style={{ display: 'flex', alignItems: 'center' }}>
                        <Image src={logo} height="45px" className="ml-3" />
                    </div>
                   
                    {/* <Navbar.Brand style={appTextStyle}>APP</Navbar.Brand> */}
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    
                    <Navbar.Collapse id="basic-navbar-nav" style={{ flexGrow: '0' }}>
                        <div className=' px-2 py-1 my-1 userName'> User:{this.props.user}</div>
                        <Nav className="mr-auto">
                            {/* <NavDropdown title="User" id="basic-nav-dropdown" style={{ textAlign: 'center', color: 'white' }}>
                                <NavDropdown.Item href="#profile">Profile</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item onClick={this.openModal}>Log Out</NavDropdown.Item>
                            </NavDropdown> */}
                            <Button className='shadow-none my-1' style={{ backgroundColor: 'rgb(28 114 54)', borderColor: 'rgb(28 114 54)', marginRight: '15px' }} onClick={this.openModal}>Log Out</Button>

                        </Nav>
                    </Navbar.Collapse>
                    <ConfirmModal
                        isOpen={this.state.isModalOpen}
                        onRequestClose={this.closeModal}
                        onConfirm={this.confirmLogout}
                    />

                </Navbar>

            </>
        );
    }
}


{/* <Navbar bg="light" expand="lg">
                    <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto">
                            <Nav.Link href="#home">Home</Nav.Link>
                            <Nav.Link href="#link">Link</Nav.Link>
                            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                                <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
                            </NavDropdown>
                            <p>{this.props.name}</p>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar> */}