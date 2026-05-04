import { useState } from "react";
import "./SignUpPage.css";

function SignUpPage() {
    
    const [initialState, setState] = useState({});

    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setState(values => ({...values, [name] : value}))
    }

    const getFormValues = (e) => {
        e.preventDefault();

        console.log("Values inputted: ", initialState);
    }

    return (
        <form onSubmit={getFormValues}>
            <label>First Name:
                <input
                    type="text"
                    name="fname"
                    value={initialState.fname || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Last Name:
                <input
                    type="text"
                    name="lname"
                    value={initialState.lname || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Middle Name:
                <input
                    type="text"
                    name="mname"
                    value={initialState.mname || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Address Str:
                <input
                    type="text"
                    name="addressStr"
                    value={initialState.addressStr || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Address City:
                <input
                    type="text"
                    name="addressCity"
                    value={initialState.addressCity || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Address State:
                <input
                    type="text"
                    name="addressState"
                    value={initialState.addressState || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Zipcode:
                <input
                    type="text"
                    name="zipcode"
                    value={initialState.zipcode || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Phone:
                <input
                    type="text"
                    name="phone"
                    value={initialState.phone || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Email:
                <input
                    type="text"
                    name="email"
                    value={initialState.email || ""}
                    onChange={handleChange}
                />
            </label>
            <label>Password:
                <input
                    type="text"
                    name="password"
                    value={initialState.password || ""}
                    onChange={handleChange}
                />
            </label>
            <button type="submit">Submit</button>
        </form>
    );
}

export default SignUpPage