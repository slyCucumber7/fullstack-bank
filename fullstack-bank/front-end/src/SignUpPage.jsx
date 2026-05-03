import { useState } from "react";
function SignUpPage() {
    
    const [initialState, setState] = useState({});

    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setState(values => ({...values, [name] : value}))
    }

    return (
        <p> </p>
    );
}