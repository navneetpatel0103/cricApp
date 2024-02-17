import React, { Component } from 'react';

const footerStyle = {
    backgroundColor: 'rgb(207, 242, 210)',
    padding: '10px',
    textAlign: 'center',
    width: '100%',
    marginTop: 'auto'
};
export default class Footer extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <>
            <div className="social-icons">
                <a href="#" title="facebook">
                    <i className="fa fa-facebook-square" aria-hidden="true"></i>
                </a>
                <a href="#" title="twitter">
                    <i className="fa fa-twitter-square" aria-hidden="true"></i>
                </a>
                <a href="#" title="instagram">
                    <i className="fa fa-instagram" aria-hidden="true"></i>
                </a>
                <a href="#" title="youtube">
                    <i className="fa fa-youtube-square" aria-hidden="true"></i>
                </a>
                <a href="#" title="linkedin">
                    <i className="fa fa-linkedin-square" aria-hidden="true"></i>
                </a>
                <a href="#" title="pinterest">
                    <i className="fa fa-pinterest-p" aria-hidden="true"></i>
                </a>
                <a href="#" title="camera">
                    <i className="fa fa-camera-retro" aria-hidden="true"></i>
                </a>
                <div classNameName='copyright'> &copy; {new Date().getFullYear()} CricInfo. All rights reserved.</div>
            </div>
           
            </>
        )
    }
}
