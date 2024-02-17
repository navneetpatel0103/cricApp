import React, { Component } from 'react'
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import UpdatePlayerForm from './UpdatePlayerForm';

export default class UpdatePlayer extends Component {
    constructor() {
        super();
        this.state = {
          player: [],
          id: "",
          result: false
        }
      }
      change = ((e) => {
        this.setState({ [e.target.name]: e.target.value })
      })
      handleSubmit = (event) => {
        this.setState({
            player:[],
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
          this.setState({ getResult: false });
          fetch(`http://localhost:8080/fetchPlayerById?id=${this.state.id}`)
            .then(res => res.json(), (error) => { this.setState({ result: true }); console.log("Error", error); })
            .then((data) => {
              console.log("Response data:", data);
              this.setState({ player: data });
            }, (error) => console.log("Error", error))
            .catch(error => console.log("Error", error))
            .finally(console.log("finally"))
        } else {
          this.setState({ player: [], id: "", result: false });
        }
    
      };
  render() {
    return (
        <Form noValidate validated={this.state.validated} className='findTeamBox'>
        <Form.Group controlId="validationCustomUsername" style={{ display: "contents" }}>
            <Form.Label style={{ color: "white" }} >Enter the player ID</Form.Label>
            <InputGroup hasValidation>
                <Form.Control
                    className='mb-2'
                    type="number"
                    name="id"
                    placeholder="Player ID"
                    aria-describedby="inputGroupPrepend"
                    required
                    onChange={this.change}
                />
                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                <Form.Control.Feedback type="invalid">
                    Please choose a player id.
                </Form.Control.Feedback>
            </InputGroup>
        </Form.Group>
        <Button style={{backgroundColor:"#1c7236", borderColor:"#1c7236"}} className='mt-2 mb-3 shadow-none' onClick={this.handleSubmit} >Submit</Button>

        {
            this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol'>Server error! Try again</Col> </Row> :
                <div>
                    {
                      this.state.player.length !== 0 ?
                            <div>
                                {
                                    this.state.player.successMessage ? <div>
                                        <UpdatePlayerForm
                                            playerId={this.state.player.p.player_id}
                                            name={this.state.player.p.playerName}
                                            country={this.state.player.p.playerCountry}
                                            role={this.state.player.p.playerRole}
                                            teamId={this.state.player.p.team_ID}
                                            gender={this.state.player.p.gender}
                                            jerseyNumber={this.state.player.p.jerseyNumber}
                                            matches={this.state.player.p.matches}
                                            runs={this.state.player.p.runs}
                                            wickets={this.state.player.p.wickets}
                                        />
                                    </div> : <Row className='errorBoxRow'><Col className='errorBoxCol' sm={12} md={12} xs={12} lg={12}>{this.state.player.failureMessage}</Col> </Row>
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
