import { useRef, useState } from "react";
import { Button, Form } from "react-bootstrap";
import API, { endpoints } from "../../configs/API";

function InitAccount() {
	const [password, setPassword] = useState();
	const avatar = useRef();

	const init = (event) => {
		event.preventDefault();

		const execute = async () => {
			let form = new FormData();
			form.append("password", password);
			form.append("avatar", avatar.current.files[0]);

			let res = await API.post(endpoints["initAccount"](2), form);

			console.log(res.data);
		};

		execute();
	};

	return (
		<>
			<Form onSubmit={init}>
				<Form.Group className="mb-3">
					<Form.Label label="Thêm ảnh đại diện" />
					<Form.Control type="file" ref={avatar} required />
				</Form.Group>

				<Form.Group className="mb-3">
					<Form.Label label="Mật khẩu" />
					<Form.Control
						type="password"
						placeholder="Mật khẩu"
						onChange={(e) => setPassword(e.target.value)}
					/>
				</Form.Group>

				<Form.Group className="mb-3">
					<Button variant="info" type="submit" className="mt-3">
						Kích hoạt tài khoản
					</Button>
				</Form.Group>
			</Form>
		</>
	);
}

export default InitAccount;
