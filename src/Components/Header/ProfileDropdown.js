import React from 'react';
import { NavLink } from 'react-router-dom';

const deactiveStyle = {
    textDecoration: 'none',
    color: 'black',
}

export default function DropMenu () {

    return (
      <>
        <div>
            <div>
                <NavLink to='/mypage' style={deactiveStyle} >My page</NavLink>
            </div>
            <div>
                <NavLink to='/editprofile' style={deactiveStyle}>Edit Profile</NavLink>
            </div>
            <div>
                <NavLink to='/' style={deactiveStyle}>Logout</NavLink>
            </div>
        </div>
      </>
    );
  }