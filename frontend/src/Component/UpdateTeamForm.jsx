import React, { Component } from 'react'
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';

export default class UpdateTeamForm extends Component {

    constructor(props) {
        console.log("1");
        super(props);
        this.state = {
             team: {},
            gid:props.id,
            id: props.id, // Fixed ID
            name: props.name,
            email: props.email,
            contact: props.contact,
            formationDate: props.formationDate,
            location: props.location,
            owner: props.owner,
            successMsg:"",
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
        if (this.state.name.length !== 0 && this.state.email.length !== 0 && this.state.contact.length !== 0 && this.state.formationDate.length !== 0 && this.state.location.length !== 0 && this.state.owner !== 0) {
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
        }
    }


    render() {
        const today=new Date().toISOString().split("T")[0];
        return (
           

            <Form noValidate validated={this.state.validated} >
                <Form.Group controlId="formId">
                    <Form.Label>ID</Form.Label>
                    <Form.Control type="text" name="id" value={this.state.id} readOnly/>
                </Form.Group>
                <Form.Group className='mb-3' as={Col} md="4" controlId="validationCustom02">
                    <Form.Label>Enter the team name</Form.Label>
                    <Form.Control required name="name" type="text" value={this.state.name} onChange={this.handleInputChange}/>
                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                    <Form.Control.Feedback type="invalid">
                        Please choose a team name.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className='mb-3' as={Col} md="4" controlId="validationCustom02">
                    <Form.Label>Enter the team email</Form.Label>
                    <Form.Control required name="email" type="email" value={this.state.email} onChange={this.handleInputChange} />
                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                    <Form.Control.Feedback type="invalid">
                        Please choose a team email.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className='mb-3' as={Col} md="4" controlId="validationCustom02">
                    <Form.Label>Enter the team contact</Form.Label>
                    <Form.Control required name="contact" type="number" value={this.state.contact} onChange={this.handleInputChange}/>
                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                    <Form.Control.Feedback type="invalid">
                        Please choose a team contact.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className='mb-3' as={Col} md="4" controlId="validationCustom02">
                    <Form.Label>Established On</Form.Label>
                    <Form.Control required max={today} name="formationDate" type="date" value={this.state.formationDate} onChange={this.handleInputChange}/>
                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                    <Form.Control.Feedback type="invalid">
                        Please choose a team formation date.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className='mb-3' as={Col} md="4" controlId="validationCustom02">
                    <Form.Label>Enter the team location</Form.Label>
                    <Form.Control required name="location" type="text" value={this.state.location} onChange={this.handleInputChange}/>
                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                    <Form.Control.Feedback type="invalid">
                        Please choose a team location.
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className='mb-3' as={Col} md="4" controlId="validationCustom02">
                    <Form.Label>Enter the team owner</Form.Label>
                    <Form.Control required name="owner" type="text" value={this.state.owner} onChange={this.handleInputChange}/>
                    <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                    <Form.Control.Feedback type="invalid">
                        Please choose a team owner.
                    </Form.Control.Feedback>
                </Form.Group>
                <Button className='ml-3' onClick={this.postTeam} >Add Team</Button>
                {
                    this.state.result ? <div>Server errors, try again later</div> :
                        <div>
                            {
                                /* this.state.team ?
                                    <div>
                                        {
                                            this.state.team.successMessage ? <div>
                                                <p>{this.state.team.successMessage}</p>
                                                <p>Team Id: {this.state.team.t.team_id}</p>
                                                <p>Team Name:{this.state.team.t.teamName}</p>
                                                <p>Team Email:{this.state.team.t.email}</p>
                                                <p>Team Contact:{this.state.team.t.contact}</p>
                                                <p>Team Formation Date:{this.state.team.t.teamFormationDate}</p>
                                                <p>Team Location:{this.state.team.t.teamLocation}</p>
                                                <p>Team Owner:{this.state.team.t.teamOwner}</p>
                                            </div> : <div>{this.state.team.failureMessage}</div>
                                        }
                                    </div>
                                    : <div>hello</div>
                            } */
                            }
                            hellooooo
                        </div>
                }
                <div>{this.state.gid}</div>
            </Form>
           
            // <Form noValidate validated={this.state.validated} >
            //     <Form.Group controlId="formId">
            //         <Form.Label>ID</Form.Label>
            //         <Form.Control type="text" name="id" value={this.state.id} readOnly />
            //     </Form.Group>
            //     <Form.Group controlId="formName">
            //         <Form.Label>Name</Form.Label>
            //         <Form.Control type="text" name="name" value={this.state.name} onChange={this.handleInputChange} />
            //         <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            //         <Form.Control.Feedback type="invalid">
            //             Please choose a team name.
            //         </Form.Control.Feedback>
            //     </Form.Group>
            //     <Form.Group controlId="formEmail">
            //         <Form.Label>Email</Form.Label>
            //         <Form.Control type="email" name="email" value={this.state.email} onChange={this.handleInputChange} />
            //         <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            //         <Form.Control.Feedback type="invalid">
            //             Please choose a team email.
            //         </Form.Control.Feedback>
            //     </Form.Group>
            //     <Form.Group controlId="formContact">
            //         <Form.Label>Contact</Form.Label>
            //         <Form.Control type="number" name="contact" value={this.state.contact} onChange={this.handleInputChange} />
            //         <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            //         <Form.Control.Feedback type="invalid">
            //             Please choose a team contact.
            //         </Form.Control.Feedback>
            //     </Form.Group>
            //     <Form.Group controlId="formFormationDate">
            //         <Form.Label>Established on</Form.Label>
            //         <Form.Control type="date" name="formationDate" value={this.state.formationDate} onChange={this.handleInputChange} />
            //         <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            //         <Form.Control.Feedback type="invalid">
            //             Please choose a team formation date.
            //         </Form.Control.Feedback>
            //     </Form.Group>
            //     <Form.Group controlId="formLocation">
            //         <Form.Label>Location</Form.Label>
            //         <Form.Control type="text" name="location" value={this.state.location} onChange={this.handleInputChange} />
            //         <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            //         <Form.Control.Feedback type="invalid">
            //             Please choose a team location.
            //         </Form.Control.Feedback>
            //     </Form.Group>
            //     <Form.Group controlId="formOwner">
            //         <Form.Label>Owner</Form.Label>
            //         <Form.Control type="text" name="owner" value={this.state.owner} onChange={this.handleInputChange} />
            //         <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            //         <Form.Control.Feedback type="invalid">
            //             Please choose a team owner.
            //         </Form.Control.Feedback>
            //     </Form.Group>
            //     <Button onClick={this.postTeam}>Submit</Button>
            //     {
            //         this.state.result ? <div>Server errors, try again later</div> :
            //             <div>
            //                 {
            //                     this.state.team ?
            //                         <div>
            //                             {
            //                                 this.state.team.successMessage ? <div>
            //                                     <p>{this.state.team.successMessage}</p>
            //                                     <p>Team Id: {this.state.team.t.team_id}</p>
            //                                     <p>Team Name:{this.state.team.t.teamName}</p>
            //                                     <p>Team Email:{this.state.team.t.email}</p>
            //                                     <p>Team Contact:{this.state.team.t.contact}</p>
            //                                     <p>Team Formation Date:{this.state.team.t.teamFormationDate}</p>
            //                                     <p>Team Location:{this.state.team.t.teamLocation}</p>
            //                                     <p>Team Owner:{this.state.team.t.teamOwner}</p>
            //                                 </div> : <div>{this.state.team.failureMessage}</div>
            //                             }
            //                         </div>
            //                         : <div>hello</div>
            //                 }
            //             </div>
            //     }
            // </Form>
        )
    }
}
