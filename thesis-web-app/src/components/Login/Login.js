import { Button, FloatingLabel, Form } from "react-bootstrap";
import "../Login/Login.css";
import { Link, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import API, { authAPI, endpoints } from "../../configs/API";
import { UserContext } from "../../configs/Context";
import cookies from "react-cookies";

function Login() {
	const [username, setUserName] = useState();
	const [password, setPassword] = useState();
	const [, userDispatch] = useContext(UserContext);
	const navigate = useNavigate();

	useEffect(() => {
		document.title = "Thông tin cá nhân";
	}, []);

	const login = async (event) => {
		event.preventDefault();

		const response = await API.post(endpoints["login"], {
			username: username,
			password: password,
		});

		if (response.status === 200) {
			// if (cookies.load("token")) {
			// 	cookies.remove("token");
			// }
			cookies.save("token", response.data);
			console.log(response.data);
		} else {
			alert("Sai tài khoản hoặc mật khẩu");
		}

		setTimeout(async () => {
			const response = await authAPI().get(endpoints["currentUser"]);

			if (response.status === 200) {
				userDispatch({ type: "login", payload: response.data });
				alert("Đăng nhập thành công");
				setUserName(null);
				setPassword(null);
				navigate("/");
			} else {
				alert("Lỗi khi lấy thông tin tài khoản");
			}
		}, 200);
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
						<Form.Control
							type="text"
							onChange={(e) => setUserName(e.target.value)}
							placeholder="Tên đăng nhập"
							required
						/>
					</FloatingLabel>
					<FloatingLabel
						controlId="floatingPassword"
						label="Password"
						className="mb-3">
						<Form.Control
							type="password"
							onChange={(e) => setPassword(e.target.value)}
							placeholder="Mật khẩu"
							required
						/>
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
