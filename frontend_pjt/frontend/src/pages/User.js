import React, { useState, useEffect} from "react";
import { useNavigate } from 'react-router-dom';
import styled from "styled-components";
import { Stack, Paper, Grid, Avatar, Button, Box, IconButton, Typography, Switch } from "@mui/material";
import { DeleteIcon, PhotoCamera, Preview } from "@mui/icons-material";
import { useSelector, useDispatch } from "react-redux";
import { getUser, updateUser, updateImage } from "../redux/actions/user";
import { TextField } from "@mui/material";
import axios from 'axios';
import { logout } from "../redux/actions/auth";

export default function User() {
  const { user: currentUser } = useSelector((state) => state.auth);
  const data = useSelector((state) => state.user);
  const [ userData, setUserData ] = useState([]);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const Input = styled("input")({
    display: "none",
  });

  const EtcButton = styled(Button)`
  background: white;
  color: #ff5f70;
  `;

  // 유저 정보 가져오기
  useEffect(() => {
    getUsers();
  }, []);

  function getUsers() {
    dispatch(getUser(currentUser.userId))
    .then((data) => {
      setUserData(data);
    })
    .catch((error) => {
      console.error(error);
    })
  }

  // 유저 정보 변경하기
  const [update, setUpdate] = useState(true);
  const [email, setEmail] = useState('');
  const [nickname, setNickname] = useState('');
  const [domain, setDomain] = useState(''); 
  const [introduce, setIntroduce] = useState('');
  let updateNickname, updateIntroduce, updateDomain, updateButton, cancleButton = null;
  
  const onClickUpdate = () => {
      setUpdate(!update);
  }

  const onClickRegister = () => {
      
      dispatch(updateUser(currentUser.userId, email, nickname, domain, introduce))
      .then(() => {
      })
      .catch(() => {  
      })
      setUpdate(!update);
      
      window.location.reload();
  }

  const onClickCancle = () => {
      navigate(0);
  }

  const onNicknameHandler = (e) => {
    setNickname(e.target.value);
  };

  const onIntroduceHandler = (e) => {
    setIntroduce(e.target.value);
  };

  const onDomainHandler = (e) => {
    setDomain(e.target.value);
  };


  if (update) {
    updateNickname = <Typography sx={{ marginLeft: 10.8, p: 0.5 }}>{data.nickname}</Typography>
    updateIntroduce = <Typography sx={{ marginLeft: 8.3, p: 0.5 }}>{data.introduce}</Typography>
    updateDomain = <Typography sx={{ marginLeft: 5, p: 0.5 }}>{data.domain}</Typography>
    updateButton = <EtcButton size="small" onClick={onClickUpdate}>수정하기</EtcButton>
  } else {
    updateNickname = <TextField sx={{ marginLeft: 10, p: 0.5}} id="outlined-basic" onChange={onNicknameHandler} defaultValue={data.nickname} variant="outlined" />
    updateIntroduce = <TextField sx={{ marginLeft: 7.4, p: 0.5}} id="outlined-basic" onChange={onIntroduceHandler} defaultValue={data.introduce} variant="outlined" />
    updateDomain = <TextField sx={{ marginLeft: 4.2, p: 0.5}} id="outlined-basic" onChange={onDomainHandler} defaultValue={data.domain} variant="outlined" />
    updateButton = <EtcButton size="small" onClick={onClickRegister}>저장하기</EtcButton>
    cancleButton = <EtcButton size="small" onClick={onClickCancle}>취소</EtcButton>
  }
  
  // 이미지 변경하기
  const [files, setFiles] = useState('');

  const onLoadImage = (e) => {
    const file = e.target.files;
    console.log(file);
    
    const formdata = new FormData();
    formdata.append('profilePhoto', file[0]);

    const config = {
      Headers: {
        'content-type': 'multipart/form-data',
      },
    }

    axios.post('http://localhost:8080/users/' + `${currentUser.userId}` + '/profilePhoto', formdata, config)
    .then(() => {
      window.location.reload();
    });
  }
  
  const useConfirm = (message=null, onConfirm, onCancel) => {

    const confirmAction = () => {
      if (window.confirm(message)) {
        onConfirm();
      } else {
        onCancel();
      }
    };

    return confirmAction;
  }

  const deleteConfirm = () => {
    axios.delete('http://localhost:8080/users/' + `${currentUser.userId}`)
    .then(() => {
      alert('회원탈퇴를 성공적으로 완료했습니다!')
    })
    .catch(() => {
      alert('회원탈퇴에 실패했습니다!')
    })

    dispatch(logout())
    .then(() => {
      navigate('/login')
    });

  }

  const cancelConfirm = () => {
    navigate('/User');
  }

  const onClickDelete = useConfirm(
    "회원탈퇴를 하시겠습니까?",
    deleteConfirm,
    cancelConfirm
  );

  return (
    <Box
      sx={{
        mt: '20px',
        width: '800px',
        border: '1px solid pink',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
      
      {/* MY PAGE, 이미지 관리 */}
      <Paper elevation={0} sx={{ maxWidth: 800, pt: 4, pl: 5, pr:4 }}>
        <Box sx={{ display: "flex", flexDirection: 'column', justifyContent: "center", alignItems: 'center', pb: 5 }}>
          <Typography variant='h4'>MY PAGE</Typography>
          <Avatar
            sx={{
              display: "flex",
              alignItems: "center",
              width: "150px",
              height: "150px",
              mt: "30px",
              mb: "30px",
            }}
            alt="user profilePhoto"
            src={data.profilePhoto}    
          />

          <Box sx={{ display: "flex", flexDirection: 'row', justifyContent: "center", alignItems: 'center' }}>
                        <label htmlFor="contained-button-file">
                          <Input accept="image/*" id="contained-button-file" multiple type="file" onChange={onLoadImage}/>
                          <Button component="form" sx = {{ alignItems: 'center', color: 'white', bgcolor: '#ff5f70', ':hover': { bgcolor: '#ffc0cb'} }}>
                            사진 변경
                          </Button>
                        </label>
          </Box>
        </Box>  
        <div className="img__box"></div>    
      </Paper>

      {/* 첫 번째 칸 */}
      <Paper
        elevation={2}
        sx={{ width: 700, my: 1, mt: 4, p: 2 }}
      >
          <Stack direction="column" sx={{ my: 1, p: 1 }}>

            <Stack direction='row' sx={{ my: 1, p: 1}}>
              <Typography variant="h6" sx={{ fontWeight: "bold"}}>닉네임</Typography>
              {updateNickname}
            </Stack>  

            <Stack direction='row' sx={{ my: 1, p: 1}}>
              <Typography variant="h6" sx={{ fontWeight: "bold" }}>자기소개</Typography>
              {updateIntroduce}
            </Stack>

            <Stack direction='row' sx={{ my: 1, p: 1}}>
              <Typography variant="h6" sx={{ fontWeight: "bold" }}>블로그 주소</Typography>
              {updateDomain}
            </Stack>

            <Stack direction='row' sx={{ my: 1, p: 1}}>
              <Typography variant="h6" sx={{ fontWeight: "bold" }}>이메일 주소</Typography>
              <Typography sx={{ marginLeft: 5, p: 0.5 }}>{data.email}</Typography>
            </Stack>
            {updateButton}
            {cancleButton}
    
          </Stack>
      </Paper>

      {/* 두 번째 칸 */}
      <Paper
        elevation={2}
        sx={{ width: 700, my: 1, mt: 1, p: 2 }}
      >
          <Stack direction="column" sx={{ my: 1, p: 1 }}>

            <Stack direction='row' sx={{ my: 1, p: 1}}>
              <Typography variant="h6" sx={{ fontWeight: "bold" }}>이웃 관리</Typography>
              <Typography sx={{ marginLeft: 7.5, p: 0.5 }}>개발중입니다!</Typography>
              <Box sx={{ flexGrow: 1 }} />
            </Stack>

            <EtcButton size="small">이웃 관리</EtcButton>

          </Stack>
      </Paper>

      {/* 세 번째 칸 */}
      <Paper
        elevation={2}
        sx={{ width: 700, my: 1, mt: 1, p: 2 }}
      >
          <Stack direction="column" sx={{ my: 1, p: 1 }}>

            <Stack direction='row' sx={{ my: 1, p: 1}}>
              <Typography variant="h6" sx={{ fontWeight: "bold" }}>회원 탈퇴</Typography>
              <Box sx={{ flexGrow: 1 }} />
            </Stack>

            <EtcButton size="small" color="error" onClick={onClickDelete}>회원 탈퇴</EtcButton>
               
          </Stack>
      </Paper>
    </Box>
  );
}