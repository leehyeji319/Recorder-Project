import React, { useState, useEffect } from 'react';

import axios from 'axios';
import {
  Button,
  IconButton,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Grid,
  Box,
  Typography,
  CardHeader,
  Avatar,
  Pagination,
  Stack,
  CardActionArea,
} from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';
import { ConstructionOutlined } from '@mui/icons-material';

const theme = createTheme();

export default function Main() {
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
      try {
        axios.get('http://localhost:8080/board/users/posts/categories')
        .then(response => {
          setPosts(response.data.data);
        })
      } catch (e) {
        console.log(e);
      }
  }, []);

  function Greeting(props) {
    const isLoggedOut = props.isLoggedOut;
    if (isLoggedOut) {
      return <neighborPost />;
    }
    return <neighborPosts />;
  }

  return (
    <Box
      sx={{
        mt: '20px',
        width: '1600px',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
    <Box
      sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}
    >
      <Box
        sx={{
          bgcolor: 'background.paper',
          pt: 8,
          pb: 6,
        }}
      >
        {/* 게시글 */}
        <Grid container spacing={4}>
          {posts.map(post => (
            <Grid item key = {post.postId} xs={12} sm={6} md={4}>              
              <Card
                sx={{
                  mx: 'auto',
                  width: '500px',
                  height: '100%',
                  display: 'flex',
                  flexDirection: 'column',
                }}
              >
                <CardHeader
                  avatar={
                    <Avatar
                      sx={{}}
                      image={post.profilePhoto}
                      aria-label="recipe"
                    ></Avatar>
                  }
                  title={post.userNickname}
                  subheader={post.created_time}
                />
                <CardActionArea component='a' href={"http://localhost:3000/post/" + `${post.postId}`}>
                  <CardMedia
                    component="img"
                    sx={{
                      width: 'fill',
                      height: '400px',
                      objectFit: 'cover',
                    }}
                    image={post.thumbnailImage}
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      {post.title}
                    </Typography>
                    <Typography>{post.summary}</Typography>
                  </CardContent>
                </CardActionArea>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Box>

      {/* pagination */}
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'center',
          '& > :not(style)': { m: 3 },
        }}
        noValidate
        autoComplete="off"
      >
        <Stack spacing={2}>
          <Pagination count={3} size="small" s hape="rounded" />
        </Stack>
      </Box>
    </Box>
  </Box>
  );
}
