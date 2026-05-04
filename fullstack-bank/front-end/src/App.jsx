import "./App.css";
import Navbar from "./components/NavBar/Navbar";
import IntroPage from "./pages/HomePage/IntroPage";
import SecondPartPage from "./pages/HomePage/SecondPage";
import SignUpPage from "./pages/Auth/SignUpPage";
import { useState } from "react";
function App() {
  const [view, setView] = useState("home");

  return (
    <div className="bank-app">
      <Navbar
        onHomeClick={() => setView("home")}
        onSignUpClick={() => setView("signup")}
      />

      {view === "home" && (
        <>
          <IntroPage />
          <SecondPartPage />
        </>
      )}
      {view === "signup" && <SignUpPage />}
    </div>
  );
}

export default App;
