import React, {useState, useEffect, useCallback} from "react";
import {useDropzone} from 'react-dropzone'
import './App.css';

const UserProfiles = () => {

    const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
      fetch("http://localhost:8080/api/v1/user-profile")
          .then(res => res.json())
          .then(data => setUserProfiles(data));
  }

  useEffect(() => fetchUserProfiles(), [] );

  return userProfiles.map((userProfile, index) => {
      return(
          <div key={index}>
              {userProfile.userProfileId ? <img alt="pfp" src={`http://localhost:8080/api/v1/user-profile/${userProfile.userProfileId}/image/download`}/> : null}
              <br/>
              <br/>
              <h2>{userProfile.username}</h2>
              <p>{userProfile.userProfileId}</p>
              <Dropzone userProfileId = {userProfile.userProfileId}/>
              <br/>
          </div>
      )
  });
}
function Dropzone({userProfileId}) {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log(file);
        const formData = new FormData();
        formData.append("file", file);

        fetch(`http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`, {
            method: 'post',
            body: formData
        })
            .then(() => console.log("File uploaded successfully"))
            .catch((e) => console.log(e));
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p>Drop the images here ...</p> :
                    <p>Drag 'n' drop profile image here, or click to select image</p>
            }
        </div>
    )
}
function App() {
  return (
    <div className="App">
        <h1>Registry</h1>
        <br/>
        <UserProfiles/>
    </div>
  );
}

export default App;
