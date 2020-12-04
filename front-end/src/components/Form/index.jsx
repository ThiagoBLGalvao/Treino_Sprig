import React from 'react';
import { Button } from '@material-ui/core';

const Form = ({ handleSubmit, children, action }) => {
    const onSubmit = (e) => {
        e.preventDefault();
        handleSubmit();
    }

    return (
        <form style={{ width: "100%" }} onSubmit={onSubmit}>
            { children }
            <Button
                fullWidth
                type="submit"
                variant="contained"
                color="primary"
            >
                { action }
            </Button>
        </form>
    )
}

export default Form;