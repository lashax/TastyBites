import React, { useContext } from 'react';
import { IconButton, Menu, MenuItem } from '@mui/material';
import LanguageIcon from '@mui/icons-material/Language';
import './languageDropdown.css'

const LanguageDropdown = () => {

  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
    handleClose();
  };

  const handleLanguageSelect = (language) => {
    handleLanguageChange(language);
    handleClose();
  };

  const handleLanguageChange = () => {
    alert('langauge changed')
  }

  return (
    <div className='languagedropdownicon'>
      <IconButton
        aria-label="language"
        aria-controls="language-menu"
        aria-haspopup="true"
        color="inherit"
        onClick={handleClick}
      >
        <LanguageIcon />
      </IconButton>
      <Menu
        id="language-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        keepMounted
      >
        <MenuItem onClick={() => handleLanguageSelect('eng')}>ENG</MenuItem>
        <MenuItem onClick={() => handleLanguageSelect('geo')}>GEO</MenuItem>
      </Menu>
    </div>
  );
};

export default LanguageDropdown;