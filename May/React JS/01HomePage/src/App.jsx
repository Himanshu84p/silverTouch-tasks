import { useState } from "react";
import "./App.css";
import Navbar from "./components/Navbar.jsx";
import Intro from "./components/Intro.jsx";
import Explore from "./components/Explore.jsx";
import Footer from "./components/Footer.jsx";

function App() {
  const [backgorund, setBackgorund] = useState("bg-primaryBg");

  return (
    <div className={backgorund}>
      <div className="px-40 py-10">
        <Navbar bgColor="bg-primary" setBodyBackgorundColor={setBackgorund} />
        <Intro
          headLine="Welcome to the SkySpace"
          textColor="text-dark_slate_gray"
        />
        <Explore textColor="text-dark_slate_gray" />
        <Footer />
      </div>
    </div>
  );
}

export default App;
