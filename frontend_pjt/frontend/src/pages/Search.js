import * as React from 'react';
// import AppBar from '@mui/material/AppBar';
import { IconButton, Card, CardActions, CardContent, CardMedia, Grid, Box, Typography, CardHeader, Avatar, Pagination, Stack, CardActionArea }from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';

const cards = [1, 2, 3, 4, 5, 6, 7, 8, 9];

const theme = createTheme();

export default function Search() {
  return (
    <ThemeProvider theme={theme}>
      <main>
        {/* ~~에 대한 검색결과 글자 출력 */}
        <Box
          sx={{
            bgcolor: 'background.paper',
            pt: 8,
            pb: 6,
            fontWeight: 'bolder',
          }}
        >

          <Box
            sx= {{
              display: 'flex',
              flexDirection: 'row',
              justifyContent: 'center',
              }}
              noValidate
              autoComplete="off"
              >
            <Box
             sx= {{
              '& > :not(style)': { marginLeft: 4 },
              }}
              noValidate
              autoComplete="off"
              >
              '주지운'에 대한 검색결과
            </Box>
          </Box>
        </Box>

        <Box
        sx= {{
        '& > :not(style)': { marginLeft: 1 },
        }}
        noValidate
        autoComplete="off"
        >
          {/* 검색된 게시글 */}
          <Grid container spacing={4}>
            {cards.map((card) => (
              <Grid item key={card} xs={12} sm={6} md={4}>
                <Card
                  sx={{ width: '86%', height: '100%', display: 'flex', flexDirection: 'column' }}
                >
                  <CardHeader
                    avatar={
                      <Avatar sx={{  }} aria-label="recipe">
                        R
                      </Avatar>
                    }
                    title="zu21un"
                    subheader="2월 6일 14:46, 2022"
                  />
                  <CardActionArea component="a" href="#">
                  <CardMedia
                    component="img"
                    sx={{
                      width: 'fill',
                      height: '400px',
                      objectFit: 'cover'
                    }}
                    image="https://source.unsplash.com/random"
                    alt="사진"
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      zu21un 그 사람 있잖아요
                    </Typography>
                    <Typography>
                      본명 주지운인 것 같던데 그 사람은 왜 그런가요?
                    </Typography>
                  </CardContent>
                  </CardActionArea>
                  <CardActions>
                    <IconButton aria-label="like">
                      <FavoriteIcon />
                    </IconButton>
                    <IconButton aria-label="share">
                      <ShareIcon />
                    </IconButton>
                  </CardActions>
                </Card>
              </Grid>
            ))}
          </Grid>
      </Box>

          {/* pagination */}
          <Box
             sx= {{
              display: 'flex',
              justifyContent: 'center',
              '& > :not(style)': { m: 3, marginLeft: 5 },
              
              }}
              noValidate
              autoComplete="off"
              >
              <Stack spacing={2}>
              <Pagination count={6} variant="outlined" color="primary" />
              </Stack>
          </Box>            
      </main>
    </ThemeProvider>
  );
}