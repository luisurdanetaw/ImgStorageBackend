import React, {useState, useEffect, useCallback} from "react";
import {useDropzone} from 'react-dropzone'
import './App.css';

const UserProfiles = () => {

    const [Employees, setEmployeeProfiles] = useState([]);

  const fetchEmployees = () => {
      fetch("http://localhost:8080/employees/v1/")
          .then(res => res.json())
          .then(data => setEmployeeProfiles(data));
  }

  useEffect(() => fetchEmployees(), [] );

  return Employees.map((employee) => {
      return(
          <div>
              {employee.userProfileId ? <img alt="pfp" src={`http://localhost:8080/employee/v1/${userProfile.userProfileId}/image/download`}/> : null}
              <br/>
              <br/>
              <h2>{employee.username}</h2>
              <p>{employee.userProfileId}</p>
              <Dropzone userProfileId = {employee.userProfileId}/>
              <br/>
          </div>
      )
  });
}
function Dropzone({userProfileId}) {
    const onDrop = useCallback(acceptedFiles => {

        const formData = new FormData();
        formData.append("file", acceptedFiles[0]);

        fetch(`http://localhost:8080/employees/v1/${userProfileId}/image/upload`, {
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
        <h1>Employees</h1>
        <hr/>
        <UserProfiles/>
    </div>
  );
}

export default App;
