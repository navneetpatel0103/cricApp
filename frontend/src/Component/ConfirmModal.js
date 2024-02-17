import React, { Component } from 'react'
import { Row, Col, Button } from 'react-bootstrap';
import Modal from 'react-modal';

export default class ConfirmModal extends Component {
    constructor(props){
        super(props);
    }
  render() {
    return (
        <Modal isOpen={this.props.isOpen} onRequestClose={this.props.onRequestClose}  style={{ overlay: { backgroundColor: 'rgba(0, 0, 0, 0.5)' }, content: { border: 'rgba(0, 0, 0, 0.5);', backgroundColor: 'transparent', display:"flex", alignItems:"center", justifyContent:"center" }  }}>
        <div className='modalBox'>
          <Row className='mb-4' >Are you sure you want to log out?</Row>
          <Row className='' style={{ display:"flex", justifyContent:"center", alignItems:"center"}}> 
          <button className="yes-button mb-3 mx-4" onClick={this.props.onConfirm}>Yes</button>
          <button className="no-button mb-3 mx-4" onClick={this.props.onRequestClose}>No</button>
          </Row>
         
        </div>
      </Modal>
    )
  }
}

