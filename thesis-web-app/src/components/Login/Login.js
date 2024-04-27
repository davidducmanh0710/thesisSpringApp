import { Button, FloatingLabel, Form } from "react-bootstrap";
import "../InitAccount/Login.css";
import { Link } from "react-router-dom";

function Login() {
	return (
		<div className="flex-box mt-200">
			<div className="form-login">
				<h1 className="text-center text-primary mb-3">ĐĂNG NHẬP</h1>
				<Form>
					<FloatingLabel
						controlId="floatingInput"
						label="Tên đăng nhập"
						className="mb-3">
						<Form.Control type="text" placeholder="Tên đăng nhập" />
					</FloatingLabel>
					<FloatingLabel
						controlId="floatingPassword"
						label="Password"
						className="mb-3">
						<Form.Control type="password" placeholder="Mật khẩu" />
					</FloatingLabel>
					<FloatingLabel className="flex-box">
						<Button variant="primary" className="mb-3 fs-5">
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
