import React, { Component } from 'react'
import InsertTeam from './InsertTeam';
import HomePage from './HomePage';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import FindTeamById from './FindTeamById';
// import UpdateTeamForm from './UpdateTeamForm';
import UpdateTeamForm from './UpdateTeamForm';

export default class UpdateTeam extends Component {

  constructor() {
    super();
    this.state = {
        team: {},
        id: "",
        sm:"",
        result: false,
        validated:false,
        getResult: false,
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
    this.setState({ check: true });
    this.setState({ validated: true });
    this.setState({getResult: false});
    if (this.state.id != 0) {
      this.setState({ result: false });
      this.setState({getResult: false});
      fetch(`http://localhost:8080/fetchTeamById?id=${this.state.id}`)
        .then(res => res.json(), (error) => { this.setState({ result: true }); console.log("Error", error);; })
        .then((data) => {
          console.log("Response data:", data);
          this.setState({ team: data.t });
          this.setState({sm:data.successMessage});
          this.setState({getResult:true}).save;
        }, (error) => console.log("Error", error))
        .catch(error => console.log("Error", error))
        .finally(console.log("finally"))
    }

  };

  convertToDate = (dateArray) => {
    const [year, month, day] = dateArray;
    return `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
  };

  render() {
    return (
        <div>
        <Form noValidate validated={this.state.validated} >
          <Form.Group as={Col} md="4" className='mb-3' controlId="validationCustomUsername">
            <Form.Label>Enter the team ID</Form.Label>
            <InputGroup hasValidation>
              <Form.Control
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
            </InputGroup>
          </Form.Group>
          <Button className='ml-3' onClick={this.handleSubmit} >Fetch Team</Button>
        </Form>
        
{
    
        (this.state.getResult) ? ((this.state.team.successMessage) ?
                    <div><UpdateTeamForm
                      id={this.state.team.t.team_id}
                      name={this.state.team.t.teamName}
                      email={this.state.team.t.email}
                      contact={this.state.team.t.contact}
                      formationDate={this.convertToDate(this.state.team.t.teamFormationDate)}
                      location={this.state.team.t.teamLocation}
                      owner={this.state.team.t.teamOwner}
                      
                    ></UpdateTeamForm></div>
                    : <div>{this.state.team.failureMessage}</div>)
                : <div>hello</div>
        }
        </div>
    )
  }
}
