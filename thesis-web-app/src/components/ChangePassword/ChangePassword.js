import { useContext, useState } from "react";
import { LoadingContext } from "../../configs/Context";
import { Button, FloatingLabel, Form } from "react-bootstrap";
import { CustomerSnackbar } from "../Common/Common";
import { authAPI, endpoints } from "../../configs/API";
import { useNavigate } from "react-router-dom";

function ChangePassword() {
	const [oldPassword, setOldPassword] = useState();
	const [newPassword, setNewPassword] = useState();
	const [requiredPassword, setRequiredPassword] = useState();
	const [, loadingDispatch] = useContext(LoadingContext);
	const [open, setOpen] = useState(false);
	const [data, setData] = useState({
		message: "Thay đổi password thành công",
		severity: "success",
	});
	const navigate = useNavigate();

	const handleSubmit = async (event) => {
		event.preventDefault();
		loadingDispatch({ type: "loading" });

		try {
			if (newPassword !== requiredPassword) {
				setData({
					message: "Nhập lại password mới không chính xác",
					severity: "error",
				});

				setOpen(true);

				setTimeout(() => {
					setOpen(false);
				}, 2000);
			} else {
				const response = await authAPI().patch(endpoints["changePassword"], {
					oldPassword: oldPassword,
					newPassword: newPassword,
				});

				if (response.status === 200) {
					setData({
						message: "Thay đổi password thành công",
						severity: "success",
					});

					setOpen(true);

					setTimeout(() => {
						setOpen(false);
					}, 2000);

					setOldPassword("");
					setNewPassword("");
					setRequiredPassword("");

					setTimeout(() => {
						navigate("/user-detail");
					}, 1000);
				}
			}
		} catch {
			setData({
				message: "Password cũ không chính xác",
				severity: "error",
			});

			setOpen(true);

			setTimeout(() => {
				setOpen(false);
			}, 2000);
		}

		loadingDispatch({ type: "unloading" });
	};

	const handleChange = (e, func) => {
		if (e.target.value.trim().length === 0) {
			func("");
			setData({
				message: "Không được nhập khoảng trắng vào password",
				severity: "error",
			});

			setOpen(true);

			setTimeout(() => {
				setOpen(false);
			}, 2000);
		} else {
			func(e.target.value.trim());
		}
	};

	return (
		<>
			<CustomerSnackbar
				open={open}
				message={data.message}
				severity={data.severity}
			/>

			<h1 className="text-center text-primary my-4">THAY ĐỔI MẬT KHẨU</h1>

			<div className="mx-auto w-50 my-4">
				<Form onSubmit={handleSubmit}>
					<FloatingLabel
						controlId="floatingInput"
						label="Nhập mật khẩu cũ"
						className="mb-3">
						<Form.Control
							type="password"
							placeholder="Nhập mật khẩu cũ"
							value={oldPassword}
							required
							onChange={(e) => handleChange(e, setOldPassword)}
						/>
					</FloatingLabel>

					<FloatingLabel
						controlId="floatingPassword"
						label="Nhập mật khẩu mới"
						className="mb-3">
						<Form.Control
							type="password"
							placeholder="Nhập mật khẩu mới"
							value={newPassword}
							required
							onChange={(e) => handleChange(e, setNewPassword)}
						/>
					</FloatingLabel>

					<FloatingLabel
						controlId="floatingPassword"
						label="Nhập lại mật khẩu mới"
						className="mb-3">
						<Form.Control
							type="password"
							placeholder="Nhập lại mật khẩu mới"
							value={requiredPassword}
							required
							onChange={(e) => handleChange(e, setRequiredPassword)}
						/>
					</FloatingLabel>

					<FloatingLabel className="flex-box">
						<Button type="submit" variant="outline-success" className="mb-3">
							Thay đổi
						</Button>
					</FloatingLabel>
				</Form>
			</div>
		</>
	);
}

export default ChangePassword;
