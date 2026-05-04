import './Navbar.css';

function Navbar({ onSignUpClick, onHomeClick }) {
    return (
        <nav className="nav-container">
            <div className="nav-links-left">
                <a href="/">Contact</a>
                <a href="/">About Us</a>
                <a href="/">FAQ</a>
                <a href="/">I guess bro</a>
            </div>

            <div className="nav-center">
                <button onClick={onHomeClick}><strong>Bank Name</strong></button>
            </div>

            <div className="nav-links-right">
                <button onClick={onSignUpClick} className="nav-button">Sign Up</button>
                <a href="/">Sign Up</a>
            </div>
        </nav>

    );
};

export default Navbar;