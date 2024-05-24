import { useContext, useRef, useState } from "react";
import { UserContext } from "../../configs/Context";
import { Button, FloatingLabel, Form, Image } from "react-bootstrap";
import { Link } from "react-router-dom";

function ChangeAvatarAndPassword() {
	const [user] = useContext(UserContext);
	const [src, setSrc] = useState(null);
	const imageRef = useRef(null);
	const [oldPassword, setOldPassword] = useState();
	const [newPassword, setNewPassword] = useState();
	const [requiredPassword, setRequiredPassword] = useState();

	const change = (event) => {
		const file = event.target.files[0];
		if (file) {
			const reader = new FileReader();

			reader.readAsDataURL(file);

			reader.onload = (e) => {
				setSrc(e.target.result);
			};
		}
		setSrc(null);
	};

	const handleChange = (event) => {
		event.preventDefault();
	};

	return (
		<>
			<div className="d-flex justify-content-center">
				<div>
					<Image
						src={src !== null ? src : user.user.avatar}
						width="200"
						height="200"
						alt="Ảnh đại diện"
						roundedCircle
						className="my-4 mx-auto"
					/>
				</div>

				<div className="d-flex align-items-center ms-4">
					<Form.Group controlId="formFile" className="mb-3">
						<Form.Label>Chọn ảnh</Form.Label>
						<Form.Control
							type="file"
							accept=".jsp, .png"
							ref={imageRef}
							onChange={(e) => change(e)}
							required
						/>
					</Form.Group>
				</div>
			</div>

			<div className="mx-auto w-50">
				<Form onSubmit={handleChange}>
					<FloatingLabel
						controlId="floatingInput"
						label="Nhập mật khẩu cũ"
						className="mb-3">
						<Form.Control
							type="password"
							placeholder="Nhập mật khẩu cũ"
							required
						/>
					</FloatingLabel>

					<FloatingLabel
						controlId="floatingPassword"
						label="Nhập mật khẩu mới"
						className="mb-3">
						<Form.Control
							type="password"
							placeholder="Nhập mật khẩu mới"
							required
						/>
					</FloatingLabel>

					<FloatingLabel
						controlId="floatingPassword"
						label="Nhập lại mật khẩu mới"
						className="mb-3">
						<Form.Control
							type="password"
							placeholder="Nhập lại mật khẩu mới"
							required
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

export default ChangeAvatarAndPassword;
