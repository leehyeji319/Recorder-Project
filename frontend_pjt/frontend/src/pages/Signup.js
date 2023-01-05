import React, { useState, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

import { Box, Button, TextField } from '@mui/material';
import { register } from '../redux/actions/auth';

import styled from 'styled-components'
import { withStyles } from '@material-ui/core/styles';
import { Typography } from '@material-ui/core';
import { ClassNames } from '@emotion/react';

  const RecorderTextField = withStyles({
    root: {
      '& label.Mui-focused': {
        color: '#ffc0cb',
      },
      '& .MuiInput-underline:after': {
        borderBottomColor: '#ffc0cb',
      },
      '& .MuiOutlinedInput-root': {
        '& fieldset': {
          borderColor: '#ffc0cb',
        },
        '&:hover fieldset': {
          borderColor: '#ffc0cb',
        },
        '&.Mui-focused fieldset': {
          borderColor: '#ffc0cb',
        },
      },
    },
  })(TextField);

export default function SignUp() {

  const RegisterButton = styled(Button)`
  background: #ff5f70;
  color: white;
  `;

  const CancleButton = styled(Button)`
  background: white;
  border: solid 1px;
  color: #ff5f70
  `;

// & label.Mui-focused {
//   color: #ff5f70;
// }
// & .MuiOutlinedInput-root {
//   &.Mui-focused fieldset {
//     border-color: #ff5f70;
//   }
// }
// & .MuiFromHelperText-root.Mui-error {
//   color: green;
// }

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rePassword, setRePassword] = useState('');
  const [nickname, setNickname] = useState('');
  const [domain, setDomain] = useState('');
  const [introduce, setIntroduce] = useState('');
  const [successful, setSuccessful] = useState(false);

  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [passwordCheckError, setPasswordCheckError] = useState(false);
  const [nicknameError, setNicknameError] = useState(false);
  const [domainError, setDomainError] = useState(false);
  const [introduceError, setIntroduceError] = useState(false);

  const [emailErrorMessage, setEmailErrorMessage] = useState('');
  const [passwordErrorMessage, setPasswordErrorMessage] = useState('');
  const [nicknameErrorMessage, setNicknameErrorMessage] = useState('');
  const [domainErrorMessage, setDomainErrorMessage] = useState('');

  // 유효성검사
  const onChangeEmail = (e) => {
    const emailRegex = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/;
    if (e.target.value === '') {
      setEmailErrorMessage('이메일을 입력해주세요');
      setEmailError(true);
    } else if (!emailRegex.test(e.target.value)) {
      setEmailErrorMessage('잘못된 형식의 이메일 입니다.');
      setEmailError(true);
    } else {
      setEmailError(false);
      setEmail(e.target.value);
    }
  };

  const onChangePassword = (e) => {
    const passwordRegex =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$/;
    if (e.target.value === '') {
      setPasswordErrorMessage('비밀번호를 입력해주세요');
      setPasswordError(true);
    } else if (!passwordRegex.test(e.target.value)) {
      setPasswordErrorMessage(
        '소문자/숫자/특수문자 포함 6글자 이상 입력해주세요',
      );
      setPasswordError(true);
    } else setPasswordError(false);
    setPassword(e.target.value);
  };

  const onChangePasswordCheck = useCallback((e) => {
      setRePassword(e.target.value);
      setPasswordCheckError(e.target.value !== password);
    },[password],);

  const onChangeNickname = (e) => {
    const nicknameRegex = /^[가-힣a-zA-Z 0-9]{2,}$/;
    if (e.target.value === '') {
      setNicknameErrorMessage('닉네임을 입력해주세요');
      setNicknameError(true);
    } else if(e.target.value.length < 2) {
      setNicknameErrorMessage('두 글자 이상 입력해주세요.')
      setNicknameError(true);
    } else if (!nicknameRegex.test(e.target.value)) {
      setNicknameErrorMessage('한글, 영어, 숫자만 입력 가능합니다.');
      setNicknameError(true);
    } else setNicknameError(false);
    setNickname(e.target.value);
  };

  const onChangeDomain = (e) => {
    const domainRegex = /^[A-Za-z0-9+]{5,}$/;
    if (e.target.value === '') {
      setDomainErrorMessage('블로그명(도메인)을 입력해주세요');
      setDomainError(true);
    } else if(e.target.value.length < 5) {
      setDomainErrorMessage('다섯 글자 이상 입력해주세요');
      setDomainError(true);
    } else if (!domainRegex.test(e.target.value)) {
      setDomainErrorMessage('영어, 숫자만 입력 가능합니다.');
      setDomainError(true);
    } else setDomainError(false);
    setDomain(e.target.value);
  };

  const onChangeIntroduce = (e) => {
    setIntroduce(e.target.value);
  };

  const validation = () => {
    if (!email) setEmailError(true);
    if (!password) setPasswordError(true);
    if (!nickname) setNicknameError(true);
    if (!domain) setDomainError(true);
    if (!introduce) setIntroduceError(true);

    if (email && password && nickname && domain && introduce) return true;
    else return false;
  };

  //submit
  const onSubmitHandler = (e) => {
    e.preventDefault();
    setSuccessful(false);

    dispatch(register(email, password, nickname, domain, introduce))
      .then(() => {
        setSuccessful(true);
        alert('회원가입을 성공적으로 완료했습니다!')
      })
      .catch(() => {
        setSuccessful(false);
      });

    navigate('/login');
  };

  // 취소 버튼
  const onClickCancleButton = () => {
    navigate('/login');
  }

  return (
    <Box
      gap={1}
      sx={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        width: '100vw',
        height: '100vh',
        backgroundColor: 'white',
      }}
    >
      
      <Typography>회원 가입</Typography>
      <Box
        component="form"
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          '& > :not(style)': { m: 1, width: '35ch' },
        }}
        noValidate
        autoComplete="off"
        onSubmit={onSubmitHandler}
      >
        <RecorderTextField
          id="Email"
          label="이메일"
          autoComplete="email"
          autoFocus
          onChange={onChangeEmail}
          error={emailError}
          helperText={emailError ? emailErrorMessage : ''}
        />
        <RecorderTextField
          id="password"
          label="비밀번호"
          type="password"
          autoComplete="current-password"
          value={password}
          onChange={onChangePassword}
          error={passwordError}
          helperText={passwordError ? passwordErrorMessage : ''}
        />
        <RecorderTextField
          id="rePassword"
          name="rePassword"
          label="비밀번호 확인"
          type="password"
          autoComplete="current-password"
          value={rePassword}
          onChange={onChangePasswordCheck}
          error={password !== rePassword}
          helperText={
            password !== rePassword ? '비밀번호가 일치하지 않습니다' : ''
          }
        />
        <RecorderTextField
          id="Nickname"
          label="닉네임"
          variant="outlined"
          onChange={onChangeNickname}
          error={nicknameError}
          helperText={nicknameError ? nicknameErrorMessage : ''}
        />
        <RecorderTextField
          id="Domain"
          label="블로그명(도메인)"
          variant="outlined"
          onChange={onChangeDomain}
          error={domainError}
          helperText={domainError ? domainErrorMessage : ''}
        />
        <RecorderTextField
          id="Introduce"
          label="소개 글을 작성해주세요"
          variant="outlined"
          onChange={onChangeIntroduce}
        />

        <Box
          sx={{
            display: 'flex',
            flexDirection: 'row',
            alignItems: 'center',
            '& > :not(style)': { m: 1, width: '20ch' },
          }}
        >
          <RegisterButton type="submit">
            회원가입
          </RegisterButton>
          <CancleButton onClick={onClickCancleButton}>취소</CancleButton>
        </Box>
      </Box>
    </Box>
  );
}
