import React, { Component } from 'react'
import {
  MDBCarousel,
  MDBCarouselItem,
} from 'mdb-react-ui-kit';
import Image from 'react-bootstrap/Image';

import Carousel from 'react-bootstrap/Carousel';
import ExampleCarouselImage from '../../src/Images/bg_image.png';
import caraousel1 from '../Images/carausel1.jpg';
import caraousel2 from '../Images/carousel2.jpg';
import caraousel3 from '../Images/carausel3.jpg';
import { Container, Row } from 'react-bootstrap';

export default class HomePage extends Component {

  
  render() {
    return (
      <div>
        <Container >
          <Row className='homeHeading'>CRICINFO</Row>
          <Row className='homeSupportHeading mb-3'>Your cricket universe ! All in one place</Row>
        </Container>
        <MDBCarousel showControls showIndicators  >
          <div>
            <MDBCarouselItem
              className=''
              style={{ width: "66vw", marginBottom:"20px" }}
              itemId={1}
              src={caraousel1}
              alt='...'
            />

            {/* <div className='imageText'> Discover, Connect, and Explore: Your Ultimate Resource for Finding Teams, Players, and Their Remarkable Stats!</div> */}
          </div>
          <div>
            <MDBCarouselItem
              className=''
              style={{ width: "66vw", marginBottom:"20px" }}
              itemId={2}
              src={caraousel2}
              alt='...'
            />
            {/* <div className='imageText'>Discover, Connect, and Explore: Your Ultimate Resource for Finding Teams, Players, and Their Remarkable Stats!</div> */}
          </div>
          <div>
            <MDBCarouselItem
              className=''
              style={{ width: "66vw", marginBottom:"20px" }}
              itemId={3}
              src={caraousel3}
              alt='...'
            />
            {/* <div className='imageText'>Discover, Connect, and Explore: Your Ultimate Resource for Finding Teams, Players, and Their Remarkable Stats!</div> */}
          </div>
        </MDBCarousel>
      </div>

    )
  }
}
