import React, { useEffect } from "react";
import { Navigate } from 'react-router-dom';
import { useSelector } from "react-redux";

const Profile = () => {
  const { user: currentUser } = useSelector((state) => state.auth);
  const { isLoggedIn } = useSelector((state) => state.auth);

  console.log('Profile currentUser', currentUser);

  // useEffect(() => {
  //   if (currentUser === null) {
  //     navigate('/login', {replace: true});
  //   }
  // }, []);

  if (isLoggedIn) {
  return (
    <div className="container">
      <header className="jumbotron">
        <h3>
          <strong>{currentUser.user}</strong> Profile </h3>
      </header>
      <p>
        <strong>Token:</strong> {currentUser.accessToken.substring(0, 20)} ...{" "}
        {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
      </p>
      <p>
        <strong>Id:</strong> {currentUser.id}
      </p>
      <p>
        <strong>Email:</strong> {currentUser.promise}
      </p>
      <strong>Authorities:</strong>
      <ul>
        {currentUser.roles &&
          currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
      </ul>
    </div>
  );
} else {
  return <Navigate to='/login' replace={true} />
  }
}
export default Profile;