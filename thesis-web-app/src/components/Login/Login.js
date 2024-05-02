import { Button, FloatingLabel, Form } from "react-bootstrap";
import "../Login/Login.css";
import { Link } from "react-router-dom";
import { useEffect } from "react";

function Login() {
	useEffect(() => {
		document.title = "Thông tin cá nhân";
	}, []);

	const login = (event) => {
		event.preventDefault();
	};

	return (
		<div className="flex-box my-4">
			<div className="form-login">
				<h1 className="text-center text-primary mb-3">ĐĂNG NHẬP</h1>
				<Form onSubmit={login}>
					<FloatingLabel
						controlId="floatingInput"
						label="Tên đăng nhập"
						className="mb-3">
						<Form.Control type="text" placeholder="Tên đăng nhập" required />
					</FloatingLabel>
					<FloatingLabel
						controlId="floatingPassword"
						label="Password"
						className="mb-3">
						<Form.Control type="password" placeholder="Mật khẩu" />
					</FloatingLabel>
					<FloatingLabel className="flex-box">
						<Button type="submit" variant="primary" className="mb-3 fs-5">
							Đăng nhập
						</Button>
						<Link to="#">Forget password?</Link>
					</FloatingLabel>
				</Form>
			</div>
		</div>
	);
}

export default Login;
