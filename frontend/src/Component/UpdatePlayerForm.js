import React, { Component } from 'react'
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Row } from 'react-bootstrap';
import { HiLocationMarker } from 'react-icons/hi';
import { PiGenderNeuterBold } from 'react-icons/pi';
import { MdOutlineNumbers } from 'react-icons/md';
import { TbCricket } from 'react-icons/tb';
import { GiCricketBat } from 'react-icons/gi';
import { TbUserPin } from 'react-icons/tb';
import { BiSolidUser, BiSolidHand, BiSolidMapPin, BiSolidCricketBall } from 'react-icons/bi';


export default class UpdatePlayerForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            player: [],
            playerId: props.playerId,
            name: props.name,
            country: props.country,
            role: props.role,
            teamId: props.teamId,
            gender: props.gender,
            jerseyNumber: props.jerseyNumber,
            matches: props.matches,
            runs: props.runs,
            wickets: props.wickets,
            result: false,
            validated: false,
        }
    }
    change = (e) => {
        this.setState({ [e.target.name]: e.target.value })
    }

    postTeam = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }
        this.setState({ check: true })
        this.setState({ validated: true })
        if (this.state.name.length !== 0 && this.state.country.length !== 0 && this.state.role.length !== 0 && this.state.teamId.length !== 0 && this.state.jerseyNumber.length !== 0 && this.state.gender.length !== 0 && this.state.matches.length !== 0 && this.state.runs.length !== 0 && this.state.wickets.length !== 0) {
            this.setState({ result: false });
            fetch('http://localhost:8080/updatePlayer',
                {
                    method: 'POST',
                    body: JSON.stringify({
                        player_id: this.state.playerId,
                        playerName: this.state.name,
                        playerCountry: this.state.country,
                        playerRole: this.state.role,
                        team: {
                            team_id: this.state.teamId
                        },
                        gender: this.state.gender,
                        jerseyNumber: this.state.jerseyNumber,
                        matches: this.state.matches,
                        runs: this.state.runs,
                        wickets: this.state.wickets

                    }),
                    headers: {
                        "Content-type": "application/json; charset=UTF-8"
                    }
                }
            )
                .then(res => res.json(), (error) => { this.setState({ result: true }); console.log("Error", error); })
                .then((data) => {
                    console.log("Response data:", data);
                    this.setState({ player: data });
                }, (error) => console.log("Error", error))
                .catch(error => console.log("Error", error))
                .finally(console.log("finally"))
        } else {
            this.setState({ player: [] })
        }
    }
    render() {
        return (
            <Form noValidate validated={this.state.validated} className='updateTeamForm'>
                <Row className='formName mt-3 mb-3'>PLAYER FORM</Row>
                <Row className='formRows'>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Player ID</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <TbUserPin />
                                </InputGroup.Text>
                                <Form.Control type="text" name="id" value={this.state.playerId} readOnly />
                            </InputGroup>
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='formRows'>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the player name</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <BiSolidUser />
                                </InputGroup.Text>
                                <Form.Control required name="name" type="text" placeholder="Team Name" defaultValue={this.state.name} onChange={this.change} />
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose a player name.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the player country</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <HiLocationMarker />
                                </InputGroup.Text>
                                <Form.Control required name="country" type="text" placeholder="Player Country" defaultValue={this.state.country} onChange={this.change} />
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose a player country.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='formRows'>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the player role</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <BiSolidHand />
                                </InputGroup.Text>
                                <Form.Control required name="role" as="select" defaultValue={this.state.role} onChange={this.change}>
                                    {/* <option value="" selected disabled hidden>Player Role</option> */}
                                    <option >Batsman</option>
                                    <option>Bowler</option>
                                    <option>All-Rounder</option>
                                </Form.Control>
                            {/* <Form.Control required name="role" type="text" placeholder="Player Role" defaultValue={this.state.role} onChange={this.change} /> */}
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose a player role.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Gender</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <PiGenderNeuterBold />
                                </InputGroup.Text>
                                <Form.Control required name="gender" type="text" as="select" defaultValue={this.state.gender} onChange={this.change}>
                                    <option >Male</option>
                                    <option>Female</option>
                                </Form.Control>
                            {/* <Form.Control required name="gender" type="text" placeholder="Gender" defaultValue={this.state.gender} onChange={this.change} /> */}
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose a gender.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='formRows'>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the player's team id</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <BiSolidMapPin />
                                </InputGroup.Text>
                                <Form.Control required name="teamId" type="number" placeholder="Team Id" defaultValue={this.state.teamId} onChange={this.change} />
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose a player's team id.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the jersey number</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <MdOutlineNumbers />
                                </InputGroup.Text>
                                <Form.Control required name="jerseyNumber" type="number" placeholder="Jersey Number" defaultValue={this.state.jerseyNumber} onChange={this.change} />
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose a jersey number.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='formRows'>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the player matches</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <TbCricket />
                                </InputGroup.Text>
                                <Form.Control required name="matches" type="number" placeholder="Player matches" defaultValue={this.state.matches} onChange={this.change} />
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose player matches.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the player runs</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <GiCricketBat />
                                </InputGroup.Text>
                                <Form.Control required name="runs" type="number" placeholder="Player runs" defaultValue={this.state.runs} onChange={this.change} />
                            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">
                                Please choose player runs.
                            </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                    <Col xs={11} sm={11} md={10} lg={6}>
                        <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                            <Form.Label>Enter the player wickets</Form.Label>
                            <InputGroup >
                                <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                    <BiSolidCricketBall />
                                </InputGroup.Text>
                                <Form.Control required name="wickets" type="number" placeholder="Player wickets" defaultValue={this.state.wickets} onChange={this.change} />
                                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                <Form.Control.Feedback type="invalid">
                                    Please choose player wickets.
                                </Form.Control.Feedback>
                            </InputGroup>
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='menuRow mt-2 mb-2'><Button style={{ backgroundColor: "#1c7236", borderColor: "#1c7236" }} className='ml-3 shadow-none' onClick={this.postTeam} >Update Player</Button></Row>

                {
                    this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol' sm={8} md={8} xs={8}>Server error! Try again</Col> </Row> :
                        <div>
                            {
                                this.state.player.length !== 0 ?
                                    <div>
                                        {
                                            this.state.player.successMessage ? <Row className='errorBoxRow'><Col className='successBoxCol' sm={8} md={8} xs={8} lg={8}>{this.state.player.successMessage}</Col> </Row>
                                                : <Row className='errorBoxRow'><Col className='errorBoxCol' sm={8} md={8} xs={8} lg={8}>{this.state.player.failureMessage}</Col> </Row>
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
