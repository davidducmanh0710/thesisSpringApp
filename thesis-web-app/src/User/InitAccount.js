import { useState } from "react";
import { Button, Container, FloatingLabel, Form } from "react-bootstrap";

const InitAccount = () => {
    const [initAccount, setInitAccount] = useState({
        "avatar": "",
        "password": ""
    })

    const change = (field, event) => {
        setInitAccount(current => {
            return {...current, [field]: event.target.value}
        })
    } 

    const show = () => {
        alert(initAccount.avatar);
    }

    return (
        <>
            <Container>
                <FloatingLabel
                    controlId="floatingAvatar"
                    label="Thêm ảnh đại diện"
                    className="mb-3"
                >
                    <Form.Control type="file" onChange={event => change('avatar', event)}/>
                </FloatingLabel>
                <FloatingLabel controlId="floatingPassword" label="Mật khẩu">
                    <Form.Control type="password" placeholder="Mật khẩu" onChange={event => change('password', event)}/>
                </FloatingLabel>
                <Button variant="info" onClick={show} className="mt-3">Kích hoạt tài khoản</Button>
            </Container>
        </>
    )
}

export default InitAccount;