import React from 'react'

export default function Register() {
  return (
    <>
    <h1 align="center">Registration Form</h1>
    <form className='container'>
    <div className="mb-3">
    <label htmlFor="name" className="form-label">Position</label>
    <select className="form-control" placeholder="Choose your Position" required>
      <option></option>
      <option>Adminstative</option>
      <option>Staff</option>
    </select>
  </div>
    <div className="mb-3">
    <label htmlFor="name" className="form-label">Name</label>
    <input type="text" className="form-control" id="name" aria-describedby="name" placeholder='Enter Name' required/>
  </div>
  <div className="mb-3">
    <label htmlFor="name" className="form-label">Gender</label>
    <select className="form-control" placeholder="Choose your Position" required>
      <option></option>
      <option>Male</option>
      <option>Female</option>
      <option>Transgender</option>
    </select>
  </div>
  <div className="mb-3">
    <label htmlFor="Email" className="form-label">Email address</label>
    <input type="email" className="form-control" id="Email" aria-describedby="emailHelp" placeholder='Enter Email' required/>
  </div>
  <div className="mb-3">
    <label htmlFor="mobile" className="form-label">Mobile Number</label>
    <input type="text" className="form-control" id="mobile" pattern='[6-9]{1}[0-9]{9}' aria-describedby="mobile" placeholder='Enter Mobile Number' required/>
  </div>
  <div className="mb-3">
    <label htmlFor="address" className="form-label">Address</label>
    <input type="text" className="form-control" id="address"  aria-describedby="address" placeholder='Enter Address' required/>
  </div> <div className="mb-3">
    <label htmlFor="name" className="form-label">ID type</label>
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
    <label htmlFor="address" className="form-label">ID Number</label>
    <input type="text" className="form-control" id="aadhar"  aria-describedby="aadhaar" placeholder='Enter ID number' required/>
  </div>
  <div className="mb-3">
    <label htmlFor="Password" className="form-label">Password</label>
    <  input type="password" className="form-control" id="psw" name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></input>
  </div>
  <div className="mb-3">
    <label htmlFor="Password" className="form-label">Confirm Password</label>
    <  input type="password" className="form-control" id="cpsw" name="cpsw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></input>
  </div>
  
  <input className="form-control" style={{backgroundColor:'skyblue'}} type="submit"/>
</form>

    </>
  )
}
