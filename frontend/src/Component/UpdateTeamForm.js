import React, { Component } from 'react'
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import { Row } from 'react-bootstrap'
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { BsPersonFillAdd, BsFillTelephoneFill, BsCalendar2Fill, BsFillPersonFill } from 'react-icons/bs';
import { HiOutlineMail } from 'react-icons/hi';
import { BiSolidHome, BiSolidMapPin } from 'react-icons/bi';

export default class UpdateTeamForm extends Component {

    constructor(props) {
        console.log("1");
        super(props);
        this.state = {
            team: [],
            id: props.id,
            name: props.name,
            email: props.email,
            contact: props.contact,
            formationDate: props.formationDate,
            location: props.location,
            owner: props.owner,
            result: false,
            validated: false,
        }
    }

    handleInputChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    };

    postTeam = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }
        this.setState({ check: true })
        this.setState({ validated: true })
        if (this.state.name.length !== 0 && this.state.email.length !== 0 && this.state.contact.length !== 0 && this.state.formationDate.length !== 0 && this.state.location.length !== 0 && this.state.owner.length !== 0) {
            this.setState({ result: false });
            fetch('http://localhost:8080/updateTeam',
                {
                    method: 'POST',
                    body: JSON.stringify({
                        teamId: this.state.id,
                        teamName: this.state.name,
                        email: this.state.email,
                        contact: this.state.contact,
                        teamFormationDate: this.state.formationDate,
                        teamLocation: this.state.location,
                        teamOwner: this.state.owner
                    }),
                    headers: {
                        "Content-type": "application/json; charset=UTF-8"
                    }
                }
            )
                .then(res => res.json(), (error) => { this.setState({ result: true }); console.log("Error", error); })
                .then((data) => {
                    console.log("Response data:", data);
                    this.setState({ team: data });
                }, (error) => console.log("Error", error))
                .catch(error => console.log("Error", error))
                .finally(console.log("finally"))
        } else {
            this.setState({ team: [] })
        }
    }


    render() {
        const today = new Date().toISOString().split("T")[0];
        return (
            <>
                <Form noValidate validated={this.state.validated} className='updateTeamForm'>
                    <Row className='formName mt-3 mb-3'>TEAM FORM</Row>
                    <Row className='formRows'>
                        <Col xs={11} sm={11} md={10} lg={6}>
                            <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                                <Form.Label>Team ID</Form.Label>
                                <InputGroup >
                                    <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                        <BiSolidMapPin />
                                    </InputGroup.Text>
                                    <Form.Control type="text" name="id" value={this.state.id} readOnly />
                                </InputGroup>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row className='formRows'>
                        <Col xs={11} sm={11} md={10} lg={6}>
                            <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                                <Form.Label>Enter the team name</Form.Label>
                                <InputGroup >
                                    <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                        <BsPersonFillAdd />
                                    </InputGroup.Text>
                                    <Form.Control required name="name" type="text" defaultValue={this.state.name} onChange={this.handleInputChange} />
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
                                    <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                        <HiOutlineMail />
                                    </InputGroup.Text>
                                    <Form.Control required name="email" type="email" value={this.state.email} onChange={this.handleInputChange} />
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
                            <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                                <Form.Label>Enter the team contact</Form.Label>
                                <InputGroup >
                                    <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                        <BsFillTelephoneFill />
                                    </InputGroup.Text>
                                    <Form.Control required name="contact" type="number" value={this.state.contact} onChange={this.handleInputChange} />
                                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                <Form.Control.Feedback type="invalid">
                                    Please choose a team contact.
                                </Form.Control.Feedback>
                                </InputGroup>
                            </Form.Group>
                        </Col>
                        <Col xs={11} sm={11} md={10} lg={6}>
                            <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                                <Form.Label>Established On</Form.Label>
                                <InputGroup >
                                    <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                        <BsCalendar2Fill />
                                    </InputGroup.Text>
                                    <Form.Control required max={today} name="formationDate" type="date" value={this.state.formationDate} onChange={this.handleInputChange} />
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
                            <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                                <Form.Label>Enter the team location</Form.Label>
                                <InputGroup >
                                    <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                        <BiSolidHome />
                                    </InputGroup.Text>
                                    <Form.Control required name="location" type="text" value={this.state.location} onChange={this.handleInputChange} />
                                <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                <Form.Control.Feedback type="invalid">
                                    Please choose a team location.
                                </Form.Control.Feedback>
                                </InputGroup>
                            </Form.Group>
                        </Col>
                        <Col xs={11} sm={11} md={10} lg={6}>
                            <Form.Group className='mb-3 formOptionHeading' as={Col} controlId="validationCustom02">
                                <Form.Label>Enter the team owner</Form.Label>
                                <InputGroup >
                                    <InputGroup.Text style={{ borderRadius: "0", borderTopLeftRadius: "3px" }}>
                                        <BsFillPersonFill />
                                    </InputGroup.Text>
                                    <Form.Control required name="owner" type="text" value={this.state.owner} onChange={this.handleInputChange} />
                                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                                    <Form.Control.Feedback type="invalid">
                                        Please choose a team owner.
                                    </Form.Control.Feedback>
                                </InputGroup>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row className='menuRow mt-2 mb-2 '><Button style={{ backgroundColor: "#1c7236", borderColor: "#1c7236" }} className='ml-3 shadow-none' onClick={this.postTeam} >Update Team</Button></Row>
                    {
                        this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol' sm={8} md={8} xs={8} lg={4}>Server error! Try again</Col> </Row> :
                            <div>
                                {
                                    this.state.team.length !== 0 ?
                                        <div>
                                            {
                                                this.state.team.successMessage ? <Row className='errorBoxRow'><Col className='successBoxCol' sm={8} md={8} xs={8} lg={8}>{this.state.team.successMessage}</Col> </Row>
                                                    : <Row className='errorBoxRow'><Col className='errorBoxCol' sm={8} md={8} xs={8} lg={8}>{this.state.team.failureMessage}</Col> </Row>
                                            }
                                        </div>
                                        : ""
                                }
                            </div>
                    }
                </Form>
            </>
        )
    }
}
