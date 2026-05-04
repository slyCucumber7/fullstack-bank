import "./App.css";
import Navbar from "./Navbar";
import IntroPage from "./IntroPage";
import SecondPartPage from "./SecondPage";
import SignUpPage from "./SignUpPage";
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
      {view === "signup" && <SignUpPage />}
    </div>
  );
}

export default App;
