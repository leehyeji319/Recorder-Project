import React, { useState, useEffect } from 'react';

import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import { Icon } from '@mui/material';

const LikeButton = ({ like, onClick }) => {
    return (
        <Icon src={like?FavoriteIcon:FavoriteBorderIcon} onClick={onClick} />
    );
};

export default LikeButton;