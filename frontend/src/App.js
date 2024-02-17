import logo from './logo.svg';
import './App.css';
import Header from './Component/Header';
import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Footer from './Component/Footer';
import Menu from './Component/Menu';
import HomePage from './Component/HomePage';
import 'font-awesome/css/font-awesome.min.css';
import LoginPage from './Component/LoginPage';
import { ToastContainer } from 'react-toastify';
<style>
  @import url('https://fonts.googleapis.com/css2?family=Courgette&display=swap');
</style>


export default class App extends Component {
  constructor(){
    super();
    this.state={
      // isLoggedIn:false
      isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',
      username:localStorage.getItem('username') || null
    }
  }
   handleLogin = (user) => {
    localStorage.setItem('isLoggedIn', 'true');
    localStorage.setItem('username', user);
    this.setState({isLoggedIn:true, username:user});
  };

  handleLogout = () => {
    localStorage.setItem('isLoggedIn', 'false');
    this.setState({ isLoggedIn: false });
  };

  render() {
    return (
      <>
     
      {this.state.isLoggedIn ? (
      <div className='main'>
        <Header onLogout={this.handleLogout} user={this.state.username}/>
        <Menu />
        <Footer />
      </div>
    ) : (
      <LoginPage onLogin={this.handleLogin} />
    )}
    <ToastContainer/>
      </>
    )
  }
}

