import './Navbar.css';

function Navbar() {
    return (
        <nav className="nav-container">
            <div className="nav-links-left">
                <a href="/">Contact</a>
                <a href="/">About Us</a>
                <a href="/">FAQ</a>
                <a href="/">I guess bro</a>
            </div>

            <div className="nav-center">
                <strong>Bank Name</strong>
            </div>

            <div className="nav-links-right">
                <a href="/">Log In</a>
                <a href="/">Sign Up</a>
            </div>
        </nav>

    );
};

export default Navbar;