import React from 'react'

export default function RegisterStudent() {
  return (
    <>
    <h1 align="center">Student Registration Form</h1>
    <form className='container'>
    <div className="mb-3">
    <label for="name" className="form-label">Name</label>
    <input type="text" className="form-control" id="name" aria-describedby="name" placeholder='Enter Name' required/>
  </div>
  <div className="mb-3">
    <label for="name" className="form-label">Gender</label>
    <select className="form-control" placeholder="Choose your Position" required>
      <option></option>
      <option>Male</option>
      <option>Female</option>
      <option>Transgender</option>
    </select>
  </div>
  <div className="mb-3">
    <label for="Email" className="form-label">Email address</label>
    <input type="email" className="form-control" id="Email" aria-describedby="emailHelp" placeholder='Enter Email' required/>
  </div>
  <div className="mb-3">
    <label for="mobile" className="form-label">Mobile Number</label>
    <input type="text" className="form-control" id="mobile" pattern='[6-9]{1}[0-9]{9}' aria-describedby="mobile" placeholder='Enter Mobile Number' required/>
  </div>
  <div className="mb-3">
    <label for="address" className="form-label">Address</label>
    <input type="text" className="form-control" id="address"  aria-describedby="address" placeholder='Enter Address' required/>
  </div> <div className="mb-3">
    <label for="name" className="form-label">ID type</label>
    <select className="form-control" placeholder="Choose your Position" required>
      <option></option>
      <option>Aadhar Card</option>
      <option>Pan Card</option>
      <option>Voter ID</option>
      <option>Driving License</option>
      <option>Passport Number</option>
    </select>
  </div>
  <div className="mb-3">
    <label for="address" className="form-label">ID Number</label>
    <input type="text" className="form-control" id="aadhar"  aria-describedby="aadhaar" placeholder='Enter ID number' required/>
  </div>
  <div className="mb-3">
    <label for="Password" className="form-label">Password</label>
    <  input type="password" className="form-control" id="psw" name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></input>
  </div>
  <div className="mb-3">
    <label for="Password" className="form-label">Confirm Password</label>
    <  input type="password" className="form-control" id="cpsw" name="cpsw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></input>
  </div>
  
  <input className="form-control" style={{backgroundColor:'skyblue'}} type="submit"/>
</form>

    </>

  )
}
