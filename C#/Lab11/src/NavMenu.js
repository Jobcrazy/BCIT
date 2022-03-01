import React from "react";
import "./Style.css"
import { Link } from "react-router-dom";

const NavMenu = () => (
  <>
    <nav
      b-9xtfnu9a3u=""
      className="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3"
    >
      <div b-9xtfnu9a3u="" className="container">
        <Link className="navbar-brand" to="/">
          SomeApp
        </Link>
        <button
          b-9xtfnu9a3u=""
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target=".navbar-collapse"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span b-9xtfnu9a3u="" className="navbar-toggler-icon"></span>
        </button>
        <div
          b-9xtfnu9a3u=""
          className="navbar-collapse collapse d-sm-inline-flex justify-content-between"
        >
          <ul b-9xtfnu9a3u="" className="navbar-nav flex-grow-1">
            <li b-9xtfnu9a3u="" className="nav-item">
              <Link className="nav-link text-dark" to="/">
                Home
              </Link>
            </li>
            <li b-9xtfnu9a3u="" className="nav-item">
              <Link className="nav-link text-dark" to="/Privacy">
                Privacy
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </>
);
export default NavMenu;
