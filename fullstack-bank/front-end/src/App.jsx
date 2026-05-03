import './App.css'
import Navbar from './Navbar'
import IntroPage from './IntroPage'
import SecondPartPage from './SecondPage'

function App() {
  return (
    <div className="bank-app">
      <Navbar />
      <IntroPage />
      <SecondPartPage />
    </div>
  )
}

export default App