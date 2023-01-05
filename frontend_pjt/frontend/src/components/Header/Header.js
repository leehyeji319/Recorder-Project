import React from 'react';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from "react-redux";
import { Navigate, useNavigate, Link } from 'react-router-dom';
import styled from "styled-components";
import logo from '../../img/logo-removebg.png'

import { IconButton, MenuItem, Menu, Box, Badge, Typography, Stack } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import NotificationsIcon from '@mui/icons-material/Notifications';
import AccountCircle from '@mui/icons-material/AccountCircle';
import MoreIcon from '@mui/icons-material/MoreVert';

import { logout } from '../../redux/actions/auth';
import HeaderNav from './HeaderNav.js';

const Img = styled.img`
  width: 175px;
  height: 35px;
`;

export default function Header() {
  const { isLoggedIn } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { user: currentUser } = useSelector((state) => state.auth);

  const onLogOut = (e) => {
    e.preventDefault();
    dispatch(logout())
    .then(() => {
      navigate('/login')
      console.log('isLogin', isLoggedIn);
    });
  };

  const Logo = () => {
    return (
      <Box>
        <Link to="/main">
          <Img src={logo} />
        </Link>        
      </Box>
    )
  }

  return (
    <Box flexDirection = 'row' alignItems = 'center' 
      sx={{
        display: 'flex',
        justifyContent: 'center',
        px: '1rem',
        my: '1rem',
    }}>
      
      <Logo />
      {isLoggedIn ? 
        <>
          <Box sx={{ flexGrow: 1 }} />
            <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
            <MenuItem component={Link} to={'/Editor'}>
              <Typography textAlign="center">새 글 작성</Typography>
            </MenuItem>
            <MenuItem component={Link} to={'/Userhome/' + `${currentUser.userId}`}>
              <Typography textAlign="center">내 블로그</Typography>
            </MenuItem>
            <MenuItem component={Link} to={'/User'}>
              <Typography textAlign="center">계정 설정</Typography>
            </MenuItem>
            <MenuItem onClick={onLogOut}>
              <Typography textAlign="center">로그아웃</Typography>
            </MenuItem>
          </Box>
          <Box sx={{ display: { xs: 'flex', md: 'none' } }}>
            <HeaderNav />
          </Box>
        </> :
        <>
          <Box
            sx={{
              display: 'flex',
              justifyContent: 'flex-end',
              width: '90%',
          }}>
          {/* 검색
            <IconButton size="large" aria-label="search" color="inherit">
              <SearchIcon />
            </IconButton> */}
            <Box spacing={1}>
                <Stack direction="row">
                  <MenuItem component={Link} to={'/login'}>
                    <Typography textAlign="center">로그인</Typography>
                  </MenuItem>
                  <MenuItem component={Link} to={'/signup'}>
                    <Typography textAlign="center">회원가입</Typography>
                  </MenuItem>
                </Stack>
            </Box>
          </Box>
        </> 
    }
    

    </Box>
  );
}
