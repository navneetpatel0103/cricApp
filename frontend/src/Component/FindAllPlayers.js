import React, { Component } from 'react'
import Col from 'react-bootstrap/Col';
import { Button, Row } from 'react-bootstrap'
import Table from 'react-bootstrap/Table';

export default class FindAllPlayers extends Component {

    constructor() {
        super();
        this.state = {
            playerAll: [],
            result: false,
        }
    }

    getAllPlayer = () => {
        this.setState({ result: false });
        console.log("clicked")
        fetch('http://localhost:8080/fetchAllPlayers')
            .then(res => res.json(), (error) => { console.log("Error", error); this.setState({ result: true }); })
            .then((data) => {
                console.log("Response", data);
                this.setState({ playerAll: data });
            }, (error) => console.log("Error", error))
            .catch(error => console.log("Error", error))
            .finally(console.log("finally"))

    };


    render() {
        return (
            <div>
                <Row className='mb-2 d-flex justify-content-center align-items-center'>
                    <Button style={{ backgroundColor: "#1c7236", borderColor: "#1c7236" }} className="m-2 p-2 shadow-none" onClick={this.getAllPlayer}>Get All Players</Button>
                </Row>

                <Row >
                    {
                        this.state.result ? <Row className='errorBoxRow'><Col className='errorBoxCol'>Server error! Try again</Col> </Row> :
                            <div>
                                {
                                    this.state.playerAll.length !== 0 ?
                                        <div>
                                            {this.state.playerAll.successMessage ?
                                                <div style={{ overflowX: "auto" }}>
                                                    <Table striped bordered hover variant="dark" style={{ textAlign: "center" }}>
                                                        <thead>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>Name</th>
                                                                <th>Role</th>
                                                                <th>Country</th>
                                                                <th>Gender</th>
                                                                <th>Team</th>
                                                                <th>Jersey Number</th>
                                                                <th>Matches</th>
                                                                <th>Runs</th>
                                                                <th>Wickets</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            {
                                                                this.state.playerAll.listP.map((player, i) =>
                                                                    <tr key={i} className='test'>
                                                                        <td>{player.player_id}</td>
                                                                        <td>{player.playerName}</td>
                                                                        <td>{player.playerRole}</td>
                                                                        <td>{player.playerCountry}</td>
                                                                        <td>{player.gender}</td>
                                                                        <td>{player.team_ID}</td>
                                                                        <td>{player.jerseyNumber}</td>
                                                                        <td>{player.matches}</td>
                                                                        <td>{player.runs}</td>
                                                                        <td>{player.wickets}</td>
                                                                    </tr>
                                                                )
                                                            }
                                                        </tbody>

                                                    </Table> </div> : <Row className='errorBoxRow'><Col className='errorBoxCol' sm={12} md={12} xs={12} lg={12}>{this.state.playerAll.failureMessage}</Col> </Row>
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
