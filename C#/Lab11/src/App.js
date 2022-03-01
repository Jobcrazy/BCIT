import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.min.js";

import NavMenu from "./NavMenu";
import Footer from "./Footer";
import Home from "./Home";
import Privacy from "./Privacy";

import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <NavMenu />
        <Routes>
          <Route path="/" element={<Home />} exact />
          <Route path="/Privacy" element={<Privacy />} exact />
        </Routes>
      </BrowserRouter>
      <Footer />
    </div>
  );
}

export default App;
