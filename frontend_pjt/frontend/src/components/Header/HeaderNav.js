import React from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useParams } from 'react-router-dom';
import { Navigate, useNavigate, Link } from 'react-router-dom';

import { IconButton, MenuItem, Menu, Box, Badge, Typography, Stack } from '@mui/material';

import SearchIcon from '@mui/icons-material/Search';
import NotificationsIcon from '@mui/icons-material/Notifications';
import AccountCircle from '@mui/icons-material/AccountCircle';
import MoreIcon from '@mui/icons-material/MoreVert';

import { logout } from '../../redux/actions/auth';


export default function HeaderNav() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { user: currentUser } = useSelector((state) => state.auth);

    const [anchorEl, setAnchorEl] = React.useState(null);
    const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = React.useState(null);

    const isMenuOpen = Boolean(anchorEl);
    const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);
  
    const handleProfileMenuOpen = (event) => {
      setAnchorEl(event.currentTarget);
    };
  
    const handleMobileMenuClose = () => {
      setMobileMoreAnchorEl(null);
    };
  
    const handleMenuClose = () => {
      setAnchorEl(null);
      handleMobileMenuClose();
    };
  
    const handleMobileMenuOpen = (event) => {
      setMobileMoreAnchorEl(event.currentTarget);
    };
  
    const onLogOut = (e) => {
      e.preventDefault();
      dispatch(logout())
      .then(() => {
        navigate('/login')
      });
    };

    const mobileMenuId = 'primary-search-account-menu-mobile';
    const renderMobileMenu = (
      <Menu
        anchorEl={mobileMoreAnchorEl}
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        id={mobileMenuId}
        keepMounted
        transformOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        open={isMobileMenuOpen}
        onClose={handleMobileMenuClose}
      >
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
      </Menu>
    );

    return(
        <>
            <IconButton
                size="large"
                aria-label="show more"
                aria-controls={mobileMenuId}
                aria-haspopup="true"
                onClick={handleMobileMenuOpen}
                color="inherit"
                >
                <MoreIcon />
            </IconButton>
            {renderMobileMenu}
        </>
    )
}
