import React, { useState } from 'react'

export default function TextForm() {
    const handle=(event)=>{
        settext(event.target.value)
    }
    const handleup=()=>{
        let newtext=text.toUpperCase();
        settext(newtext);
    }
    const [text, settext]=useState('Enter Text Here')
    return (
    <div>
        <textarea onChange={handle} className='form-control' id='myBox' rows='4' value={text}></textarea>
        <button className="btn btn-primary" onClick={handleup}>Convert</button>
    </div>
  )
}
