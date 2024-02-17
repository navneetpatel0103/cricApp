import React, { Component } from 'react'
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import { Container, Row } from 'react-bootstrap'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';

export default class FindTeamById extends Component {
  constructor() {
    super();
    this.state = {
      team: [],
      id: "",
      result: false
    }
  }
  change = ((e) => {
    this.setState({ [e.target.name]: e.target.value })
  })
  handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    this.setState({ check: true })
    this.setState({ validated: true })

    if (this.state.id.length !== 0) {
      this.setState({ getResult: false });
      fetch(`http://localhost:8080/fetchTeamById?id=${this.state.id}`)
        .then(res => res.json(), (error) => { this.setState({ result: true }); console.log("Error", error); })
        .then((data) => {
          console.log("Response data:", data);
          this.setState({ team: data });
        }, (error) => console.log("Error", error))
        .catch(error => console.log("Error", error))
        .finally(console.log("finally"))
    } else {
      this.setState({ team: [], id: "", result: false });
    }

  };

  render() {
    return (
      <Form noValidate validated={this.state.validated} className='findTeamBox'>
        <Form.Group controlId="validationCustomUsername" style={{display: "contents"}}>
          <Form.Label style={{ color: "white" }} >Enter the team ID</Form.Label>
          {/* <InputGroup hasValidation> */}
            <Form.Control
              // className='mb-2'
              type="number"
              name="id"
              placeholder="Team ID"
              aria-describedby="inputGroupPrepend"
              required
              onChange={this.change}
            />
            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">
              Please choose a team id.
            </Form.Control.Feedback>
          {/* </InputGroup> */}
        </Form.Group>
        <Button style={{backgroundColor:"#1c7236", borderColor:"#1c7236"}} className='mt-3 mb-4 shadow-none' onClick={this.handleSubmit} >Submit</Button>

        {
          this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol'>Server error! Try again</Col> </Row> :
            <div>
              {
                this.state.team.length!==0 ?
                  <div>
                    {
                      this.state.team.successMessage ? <div>
                        <p style={{ color:"white"}} >{this.state.team.successMessage}</p>
                        <Container>
                        <Row className='tableHeading'>Team ID</Row>
                        <Row className='tableValue'>{this.state.team.t.team_id}</Row>
                        <Row className='tableHeading'>Team Name</Row>
                        <Row className='tableValue'>{this.state.team.t.teamName}</Row>
                        <Row className='tableHeading'>Team Email</Row>
                        <Row className='tableValue'>{this.state.team.t.email}</Row>
                        <Row className='tableHeading'>Team Contact</Row>
                        <Row className='tableValue'>{this.state.team.t.contact}</Row>
                        <Row className='tableHeading'>Team Formation Date</Row>
                        <Row className='tableValue'>{new Date(this.state.team.t.teamFormationDate).toLocaleDateString()}</Row>
                        <Row className='tableHeading'>Team Location</Row>
                        <Row className='tableValue'>{this.state.team.t.teamLocation}</Row>
                        <Row className='tableHeading'>Team Owner</Row>
                        <Row className='tableValue'>{this.state.team.t.teamOwner}</Row>
                        </Container>
                      </div> :<Row className='errorBoxRow'><Col className='errorBoxCol' sm={12} md={12} xs={12} lg={12}>{this.state.team.failureMessage}</Col> </Row>
                    }
                  </div>
                  : ""
              }
            </div>
        }


      </Form>

    )
  }
}
