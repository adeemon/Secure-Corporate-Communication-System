// import Button from "react-bootstrap/Button";
// import Stack from "react-bootstrap/Stack";

import { AccessAlarm } from "@mui/icons-material";
import { Button, Stack } from "@mui/material";

export default function Login() {
    return (
        <div>
            <h1>login page</h1>
            <Stack direction="row" spacing={2}>
                
                <Button variant="outlined" color="warning">
                    Button as link
                </Button>
                <Button variant="contained" color="success" endIcon={<AccessAlarm />}>
                    Button as link
                </Button>
            </Stack>
        </div>
    );
}