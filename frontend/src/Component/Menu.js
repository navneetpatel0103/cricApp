import React, { Component } from 'react'
import { Col, Row } from 'react-bootstrap'
import { Route, Link, BrowserRouter as Router, NavLink, Switch, Routes } from 'react-router-dom'
import HomePage from './HomePage'
import InsertTeam from './InsertTeam'
import FindTeamById from './FindTeamById'
import FindAllTeams from './FindAllTeams'
import UpdateTeam from './UpdateTeam'
import RestApiDemo from './RestApiDemo'
import Container from 'react-bootstrap/Container';
import FindAllPlayers from './FindAllPlayers'
import FindPlayerById from './FindPlayerById'
import InsertPlayer from './InsertPlayer'
import UpdatePlayer from './UpdatePlayer'
import FindPlayerByName from './FindPlayerByName'

export default class Menu extends Component {
  render() {
    return (
      <div className='menu'>
        <Row className="menuRow mx-0">
          <Router>
            <Col xs={6} sm={6} md={4} lg={2} className='menuCol'>
              <div>

              <Row className="menuBox"><NavLink activeclassname="active" className="menuOption" to="/">Home Page</NavLink></Row>
              <Row className='optionHeading'>ACCESS TEAM INFO</Row>
              <Row className="menuBox"><NavLink activeClassName="active" className="menuOption " to="/createTeam">Insert Team</NavLink></Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/updateTeam">Update Team</NavLink></Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/fetchTeamById">Find team by id</NavLink></Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/fetchAllTeams">Find all teams</NavLink></Row>
                <Row className='optionHeading'>ACCESS PLAYER INFO</Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/createPlayer">Insert Player</NavLink></Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/updatePlayer">Update Player</NavLink></Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/fetchPlayerById">Find Player by id</NavLink></Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/fetchAllPlayers">Find all Players</NavLink></Row>
                <Row className="menuBox"><NavLink activeClassName="active" className="menuOption" to="/fetchPlayerByName">Find Players By Name</NavLink></Row>
              </div>
            </Col>
            <Col  xs={8} sm={8} md={10} lg={10} style={{}}>
            {/* <div className="formBox resultContainer"></div> */}
              <div className="formBox">
                <Routes>
                  <Route exact path="/" Component={HomePage} />
                  <Route path="/createTeam" Component={InsertTeam} />
                  <Route path="/fetchTeamById" Component={FindTeamById} />
                  <Route path="/fetchAllTeams" Component={FindAllTeams} />
                  <Route path="/updateTeam" Component={UpdateTeam} />
                  <Route path="/fetchAllPlayers" Component={FindAllPlayers} />
                  <Route path="/fetchPlayerById" Component={FindPlayerById} />
                  <Route path="/createPlayer" Component={InsertPlayer} />
                  <Route path="/updatePlayer" Component={UpdatePlayer} />
                  <Route path="/restApiDemo" Component={RestApiDemo} />
                  <Route path="/fetchPlayerByName" Component={FindPlayerByName} />
                </Routes>
              </div>
            </Col>
          </Router>
        </Row>
      </div>
    )
  }
}

// insert team;
// find team by id;
// find all team;
// find team greater than given id;
// find team with players;
// find team with player customize;
// count total teams;


