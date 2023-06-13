import { NavLink } from "react-router-dom";
import Dropdown from "../UI/Dropdown";
import Button from "../UI/Button";
import LanguageDropdown from "../UI/LanguageDropdown";

const MobileMenu = () => {
    return (
      <div className={'mobile-menu login'}>
        <NavLink to="/" >Home</NavLink>
        <Dropdown name="Recipes" />
        <NavLink to="/blog" >Blogs</NavLink>
        <NavLink to="/about" >About Us</NavLink>
        <NavLink to="/contact" >Contact Us</NavLink>
        <NavLink to="/auth/register">
          <Button name="Register" />
        </NavLink>
        <NavLink to="/auth/login">
          <Button name="Login" />
        </NavLink>
        <LanguageDropdown />
      </div>
    );
  };

  export default MobileMenu