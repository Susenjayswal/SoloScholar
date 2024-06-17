import './App.css';
import './Appa.css'
import Bodypart from './components/Bodypart';
import Navbar from './components/Navbar';
import Register from './components/Register';
import RegisterStudent from './components/RegisterStudent';
function App() {
  return (
   <>
    <Navbar title="Solo Scholar"/>
    <Bodypart/>
    <Register/>
    <RegisterStudent/>  
   </>
  );
}
export default App;
