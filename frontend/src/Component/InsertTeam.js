import React, { Component } from 'react'
import HomePage from './HomePage';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import { BsPersonFillAdd,BsFillTelephoneFill,BsCalendar2Fill,BsFillPersonFill } from 'react-icons/bs';
import {HiOutlineMail} from 'react-icons/hi';
import {BiSolidHome} from 'react-icons/bi';


export default class InsertTeam extends Component {
  constructor() {
    super();
    this.state = {
      team: [],
      name: "",
      email:"",
      contact:"",
      formationDate:"",
      location:"",
      owner:"",
      result: false,
      validated: false,
    }
  }
  change = (e) => {
    this.setState({ [e.target.name]:e.target.value })
  }

  postTeam = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    this.setState({ validated: true })
    if(this.state.name.length!==0 && this.state.email.length!==0 && this.state.contact.length!==0 && this.state.formationDate.length!==0 && this.state.location.length!==0 && this.state.owner.length!==0){
    this.setState({result:false});
    fetch('http://localhost:8080/createTeam',
      {
        method: 'POST',
        body: JSON.stringify({
          teamName: this.state.name,
          email:this.state.email,
          contact:this.state.contact,
          teamFormationDate:this.state.formationDate,
          teamLocation:this.state.location,
          teamOwner:this.state.owner
        }),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      }
    )
      .then(res => res.json(), (error) => { this.setState({ result: true}); console.log("Error", error); })
      .then((data) => {
        console.log("Response data:", data);
        this.setState({ team: data });
      }, (error) => console.log("Error", error))
      .catch(error => console.log("Error", error))
      .finally(console.log("finally"))
    }else{
      this.setState({team:[]})
    }
  }
  render() {
    const today=new Date().toISOString().split("T")[0];
    return (
      <> 
      <Form noValidate validated={this.state.validated} className='formBorder' style={{height:"fit-content"}}>
      <Row className='formName mt-3 mb-3'>TEAM FORM</Row>
      <Row className='formRows'>
      <Col xs={11} sm={11} md={10} lg={6}>
        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
          <Form.Label>Enter the team name</Form.Label>
          <InputGroup >
            <InputGroup.Text style={{ borderRadius:"0", borderTopLeftRadius:"3px"}}>
            <BsPersonFillAdd />
            </InputGroup.Text>
          <Form.Control required name="name" type="text" placeholder="Team Name" onChange={this.change}/>
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            Please choose a team name.
          </Form.Control.Feedback>
          </InputGroup>
        </Form.Group>
        </Col>
        <Col xs={11} sm={11} md={10} lg={6}>
        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
          <Form.Label>Enter the team email</Form.Label>
          <InputGroup >
            <InputGroup.Text style={{ borderRadius:"0", borderTopLeftRadius:"3px"}}>
            <HiOutlineMail />
            </InputGroup.Text>
          
            <Form.Control required name="email" type="text" placeholder="Team Email" onChange={this.change}/>
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            Please choose a team email.
          </Form.Control.Feedback>
          </InputGroup>

        </Form.Group>
        </Col>
        </Row>
        <Row className='formRows'> 
        <Col xs={11} sm={11} md={10} lg={6}>
        <Form.Group className='mb-3 formOptionHeading' as={Col}  controlId="validationCustom02">
          <Form.Label>Enter the team contact</Form.Label>
          <InputGroup >
            <InputGroup.Text style={{ borderRadius:"0", borderTopLeftRadius:"3px"}}>
            <BsFillTelephoneFill />
            </InputGroup.Text>
          
            <Form.Control required name="contact"  type="number" placeholder="Team Contact"  onChange={this.change} />


         
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            Please choose a team contact.
          </Form.Control.Feedback>
          </InputGroup>
        </Form.Group>
        </Col>
        <Col xs={11} sm={11} md={10} lg={6}>
        <Form.Group className='mb-3 formOptionHeading' as={Col}  controlId="validationCustom02">
          <Form.Label>Established On</Form.Label>
          <InputGroup >
            <InputGroup.Text style={{ borderRadius:"0", borderTopLeftRadius:"3px"}}>
            <BsCalendar2Fill />
            </InputGroup.Text>
          
            <Form.Control required max={today}  name="formationDate" type="date" placeholder="Established On" onChange={this.change}/>

          
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            Please choose a team formation date.
          </Form.Control.Feedback>
          </InputGroup>
        </Form.Group>
        </Col>
        </Row>
        <Row className='formRows'>
        <Col xs={11} sm={11} md={10} lg={6}>
        <Form.Group className='mb-3 formOptionHeading' as={Col}  controlId="validationCustom02">
          <Form.Label>Enter the team location</Form.Label>
          <InputGroup >
            <InputGroup.Text style={{ borderRadius:"0", borderTopLeftRadius:"3px"}}>
            <BiSolidHome />
            </InputGroup.Text>
          
            <Form.Control required name="location" type="text" placeholder="Team Location" onChange={this.change} />

          
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            Please choose a team location.
          </Form.Control.Feedback>
          </InputGroup>
        </Form.Group>
        </Col>
        <Col xs={11} sm={11} md={10} lg={6}>
        <Form.Group className='mb-3 formOptionHeading' as={Col}  controlId="validationCustom02">
          <Form.Label>Enter the team owner</Form.Label>
          <InputGroup >
            <InputGroup.Text style={{ borderRadius:"0", borderTopLeftRadius:"3px"}}>
            <BsFillPersonFill />
            </InputGroup.Text>
          
            <Form.Control required  name="owner" type="text" placeholder="Team Owner" onChange={this.change}/>


         
          <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
          <Form.Control.Feedback type="invalid">
            Please choose a team owner.
          </Form.Control.Feedback>
          </InputGroup>
        </Form.Group>
        </Col>
        </Row>
        <Row className='menuRow mt-2 mb-2'><Button style={{backgroundColor:"#1c7236", borderColor:"#1c7236"}} className='ml-3 shadow-none' onClick={this.postTeam} >Add Team</Button></Row>
       
        {
          this.state.result ?<Row className='errorBoxRow'><Col className='errorBoxCol' sm={8} md={8} xs={8} lg={4}>Server error! Try again</Col> </Row> :
            <div>
              {
                this.state.team.length!==0 ?
                  <div>
                    {
                      this.state.team.successMessage ? <Row className='errorBoxRow'><Col className='successBoxCol' sm={8} md={8} xs={8} lg={8}>{this.state.team.successMessage}</Col> </Row> 
                       : <Row className='errorBoxRow'><Col className='errorBoxCol' sm={8} md={8} xs={8} lg={8}>{this.state.team.failureMessage}</Col> </Row> 
                    }
                  </div>
                  :""
              }
            </div>
        }
      </Form>
      </>
    )
  }
}

// <div>
      //   <h6>Enter the team name</h6>
      //   <input type="text" placeholder='teamName' onChange={this.change} /><br /><br />
      //   <button type="button" className="btn btn-primary m-2 p-2" onClick={this.postTeam}>Post Team</button>

      //   {
      //     this.state.result ? <div>Server errors, try again later</div> :
      //       <div>
      //         {
      //           this.state.team?
      //             <div>
      //               {
      //                 this.state.team.successMessage ? <div>
      //                   <p>{this.state.team.successMessage}</p>
      //                   <p>Team Id: {this.state.team.t.team_id}</p>
      //                   <p>Team Name:{this.state.team.t.teamName}</p>
      //                 </div> : <div>{this.state.team.failureMessage}</div>
      //               }
      //             </div>
      //             : ""
      //         }
      //       </div>
      //   }
      // </div>