import React, { Component } from 'react'
import Col from 'react-bootstrap/Col';
import { Row } from 'react-bootstrap'
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';


export default class FindAllTeams extends Component {
  constructor() {
    super();
    this.state = {
      teamAll: [],
      result: false,
      getResult: false
    }
  }

  getAllTeam = () => {
    this.setState({ result: false });
    console.log("clicked")
    fetch('http://localhost:8080/fetchAllTeams')
      .then(res => res.json(), (error) => { console.log("Error", error); this.setState({ result: true }); })
      .then((data) => {
        console.log("Response", data);
        this.setState({ teamAll: data });
      }, (error) => console.log("Error", error))
      .catch(error => console.log("Error", error))
      .finally(console.log("finally"))

  };


  render() {
    return (
      <div>
        <Row className='mb-2 d-flex justify-content-center align-items-center'>
          <Button style={{ backgroundColor: "#1c7236", borderColor: "#1c7236" }} className="m-2 p-2 shadow-none" onClick={this.getAllTeam}>Get All Teams</Button>
        </Row>

        <Row >
          {
            this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol'>Server error! Try again</Col> </Row> :
              <div>
                {
                  this.state.teamAll.length !== 0 ?
                    <div>
                      {
                        this.state.teamAll.successMessage ?
                          <div>
                            <Table striped bordered hover variant="dark" style={{ textAlign: "center" }}>
                              <thead>
                                <tr>
                                  <td>Team ID</td>
                                  <td > Team Name</td>
                                  <td >Team Email</td>
                                  <td > Team Contact</td>
                                  <td >Team Formation Date</td>
                                  <td > Team Location</td>
                                  <td>Team Owner</td>
                                </tr>
                              </thead>
                              <tbody>
                                {
                                  this.state.teamAll.listT.map((team, i) =>
                                    <tr key={i}>
                                      <td>{team.team_id}</td>
                                      <td>{team.teamName}</td>
                                      <td>{team.email}</td>
                                      <td>{team.contact}</td>
                                      <td>{new Date(team.teamFormationDate).toLocaleDateString()}</td>
                                      <td>{team.teamLocation}</td>
                                      <td>{team.teamOwner}</td>
                                    </tr>
                                  )
                                }
                              </tbody>
                            </Table></div> : <Row className='errorBoxRow'><Col className='errorBoxCol' sm={12} md={12} xs={12} lg={12}>{this.state.teamAll.failureMessage}</Col> </Row>
                      }
                    </div> : ""
                }
              </div>
          }
        </Row>
      </div>
    )
  }
}
