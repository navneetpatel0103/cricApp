import React, { Component } from 'react'
import InsertTeam from './InsertTeam';
import HomePage from './HomePage';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import FindTeamById from './FindTeamById';
import UpdateTeamForm from './UpdateTeamForm';

export default class UpdateTeam extends Component {

  constructor() {
    super();
    this.state = {
      team: [],
      id: "",
      result: false,
      validated: false,
      check: false,
      submit: false
    }
  }

  change = ((e) => {
    this.setState({ [e.target.name]: e.target.value })
  })

  
  convertToDate = (dateArray) => {
    const [year, month, day] = dateArray;
    return `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
  };

  handleSubmit = (event) => {
    this.setState({
      team:[],
      id:this.state.id
    })
    
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    this.setState({ check: true })
    this.setState({ validated: true })

    if (this.state.id.length !== 0) {
      this.setState({ result: false });
      fetch(`http://localhost:8080/fetchTeamById?id=${this.state.id}`)
        .then(res => res.json(), (error) => { this.setState({ result: true }); console.log("Error", error); })
        .then((data) => {
          console.log("data1:: ", data);
          this.setState({ team: data });
          console.log("Team data::", this.state.team.t);
        }, (error) => console.log("Error", error))
        .catch(error => console.log("Error", error))
        .finally(console.log("finally"))
    }

  };

  render() {
    return (
      <>
      <div>
        <Form noValidate validated={this.state.validated} className='findTeamBox'>
            <Form.Group  controlId="validationCustomUsername" style={{display: "contents"}}>
              <Form.Label style={{ color: "white" }}  className='mt-3 mb-3'>Enter the team ID</Form.Label>
              {/* <InputGroup hasValidation> */}
              {/* <Row> */}
                <Form.Control type="number" name="id" placeholder="Team ID" aria-describedby="inputGroupPrepend"
                  required onChange={this.change} />
                  {/* </Row> */}
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                <Form.Control.Feedback type="invalid">
                  Please choose a team id.
                </Form.Control.Feedback>
              {/* </InputGroup> */}
            </Form.Group>
            <Button style={{backgroundColor:"#1c7236", borderColor:"#1c7236"}} className='mt-3 mb-4 shadow-none' onClick={this.handleSubmit} >Fetch Team</Button>
        </Form>
        <div>
        
        {
          this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol'>Server error! Try again</Col> </Row> :
            <div>
              {this.state.team.length!==0 ?

                <div>

                  {(this.state.team.successMessage) ?

                    <div>
                      <UpdateTeamForm
                        team={this.state.team.t}
                        id={this.state.team.t.team_id}
                        name={this.state.team.t.teamName}
                        email={this.state.team.t.email}
                        contact={this.state.team.t.contact}
                        formationDate={this.convertToDate(this.state.team.t.teamFormationDate)}
                        location={this.state.team.t.teamLocation}
                        owner={this.state.team.t.teamOwner}
                      ></UpdateTeamForm>
                    </div>

                    : <Row className='errorBoxRow'><Col className='errorBoxCol' sm={12} md={12} xs={12} lg={12}>{this.state.team.failureMessage}</Col> </Row>
                  }
                </div>
                : ""
              }
              </div>
        }

        </div>
        </div>
        </>
    );
  }
}