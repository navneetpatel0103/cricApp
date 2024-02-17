import React, { Component } from 'react'
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import { Container, Row } from 'react-bootstrap'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Table from 'react-bootstrap/Table';

export default class FindPlayerByName extends Component {

    constructor() {
        super();
        this.state = {
            player: [],
            playerList: [],
            name: "",
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

        if (this.state.name.length !== 0) {
            this.setState({ getResult: false });
            fetch(`http://localhost:8080/findByName?name=${this.state.name}`)
                .then(res => res.json(), (error) => { this.setState({ result: true }); console.log("Error", error); })
                .then((data) => {
                    console.log("Response data:", data);
                    this.setState({ player: data });
                    this.setState({ playerList: data.listP })
                }, (error) => console.log("Error", error))
                .catch(error => console.log("Error", error))
                .finally(console.log("finally"))
        } else {
            this.setState({ player: [], id: "", result: false });
        }

    };


    render() {
        return (
            <div>
                <Form noValidate validated={this.state.validated} className='findTeamBox'>
                    <Form.Group controlId="validationCustomUsername" style={{ display: "contents" }}>
                        <Form.Label style={{ color: "white" }} >Enter the Player Name</Form.Label>
                        <InputGroup hasValidation>
                            <Form.Control
                                className='mb-2'
                                type="text"
                                name="name"
                                placeholder="Player Name"
                                aria-describedby="inputGroupPrepend"
                                required
                                onChange={this.change}
                            />
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose a player name.
                            </Form.Control.Feedback>
                        </InputGroup>
                    </Form.Group>
                    <Button style={{ backgroundColor: "#1c7236", borderColor: "#1c7236" }} className='mt-2 mb-3 shadow-none' onClick={this.handleSubmit} >Submit</Button>

                    {
                        this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol'>Server error! Try again</Col> </Row> :
                            <div>
                                {
                                    this.state.player.length !== 0 ?
                                        <div>
                                            {
                                                this.state.player.successMessage ?
                                                    <div>
                                                        <div style={{ overflowX: "auto" }}>
                                                            <Table striped bordered hover variant="dark" style={{ textAlign: "center" }}>
                                                                <thead>
                                                                    <tr>
                                                                        <th>ID</th>
                                                                        <th>Name</th>
                                                                        <th>Role</th>
                                                                        <th>Country</th>
                                                                        <th>Team</th>
                                                                        <th>Jersey Number</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    {
                                                                        this.state.playerList.map((player, i) =>
                                                                            <tr key={i} className='test'>
                                                                                <td>{player.player_id}</td>
                                                                                <td>{player.playerName}</td>
                                                                                <td>{player.playerRole}</td>
                                                                                <td>{player.playerCountry}</td>
                                                                                <td>{player.team_ID}</td>
                                                                                <td>{player.jerseyNumber}</td>
                                                                            </tr>
                                                                        )
                                                                    }
                                                                </tbody>
                                                            </Table> </div>
                                                    </div> :
                                                    <Row className='errorBoxRow'><Col className='errorBoxCol' sm={12} md={12} xs={12} lg={12}>{this.state.player.failureMessage}</Col> </Row>
                                            }





                                        </div> : ""
                                }

                            </div>
                    }


                </Form>
            </div>
        )
    }
}
