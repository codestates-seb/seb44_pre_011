import React from 'react';
import { NavLink } from 'react-router-dom';
import styled from 'styled-components';

export const Menu = styled.div`
  position: absolute;
  top: 100%;
  right: 450px;
  width: 150px;
  height: 150px;
  background-color: rgb(255, 255, 255); 
  box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
  border: 1px solid rgb(205, 205, 205);
  border-radius: 3px;
`;

export const MenuItem = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 150px;
  height: 50px;
  font-size: 18px;
`;

const deactiveStyle = {
    textDecoration: 'none',
    color: 'black',
}


export default function DropMenu () {

    return (
      <>
        <Menu>
            <MenuItem>
                <NavLink to='/mypage' style={deactiveStyle} >My page</NavLink>
            </MenuItem>
            <MenuItem>
                <NavLink to='/editprofile' style={deactiveStyle}>Edit Profile</NavLink>
            </MenuItem>
            <MenuItem>
                <NavLink to='/' style={deactiveStyle}>Logout</NavLink>
            </MenuItem>
        </Menu>
      </>
    );
  }