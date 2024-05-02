import { useEffect, useRef, useState } from "react";
import { Button, FloatingLabel, Form, Stack } from "react-bootstrap";
import Select from "react-select";
import API, { endpoints } from "../../configs/API";
import { useNavigate } from "react-router-dom";

function AddThesis() {
	const [name, setName] = useState("");
	const [lecturers, setLecturers] = useState([]);
	const [students, setStudents] = useState([]);

	const studentSelect = useRef();
	const lecturerSelect = useRef();

	const navigate = useNavigate();

	useEffect(() => {
		document.title = "Thêm khóa luận";

		const getTwoRoleList = async () => {
			const response = await API.get(endpoints["getTwoRoleList"]);

			setLecturers(
				response.data.usersGiangVien.map((l) => {
					return { value: l.id, label: `${l.lastName} ${l.firstName}` };
				})
			);

			setStudents(
				response.data.usersSinhVien.map((s) => {
					return { value: s.id, label: `${s.lastName} ${s.firstName}` };
				})
			);
		};

		getTwoRoleList();
	}, []);

	const isOptionSelected = (_, selectValue) => {
		return selectValue.length > 1;
	};

	const addThesis = (event) => {
		event.preventDefault();

		const add = async () => {
			let users = [];
			lecturerSelect.current.props.value.forEach((i) => users.push(i.value));
			studentSelect.current.props.value.forEach((i) => users.push(i.value));

			let response = await API.post(endpoints["theses"], {
				name: name,
				userIds: users,
			});

			if (response.status === 200) {
				alert("Thêm khóa luận thành công");
				navigate("/");
			} else {
				alert("Thêm khóa luận thất bại");
			}
		};

		add();
	};

	return (
		<>
			<h1 className="text-success text-center my-3">THÊM KHÓA LUẬN</h1>

			<Stack>
				<Form className="w-50 mx-auto" onSubmit={addThesis}>
					<FloatingLabel
						controlId="floatingInput"
						label="Tên khóa luận"
						className="mb-3">
						<Form.Control
							type="text"
							placeholder="Nhập tên khóa luận"
							className="mb-3"
							onChange={(e) => setName(e.target.value)}
						/>
					</FloatingLabel>

					<Form.Group className="mb-3">
						<Form.Label>Danh sách sinh viên</Form.Label>
						<Select
							isMulti
							name="students"
							options={students}
							className="basic-multi-select fs-6 mb-3"
							classNamePrefix="select"
							isOptionSelected={isOptionSelected}
							isSearchable={true}
							placeholder="Chọn sinh viên"
							ref={studentSelect}
						/>
					</Form.Group>

					<Form.Group className="mb-3">
						<Form.Label>Danh sách giảng viên</Form.Label>
						<Select
							isMulti
							name="lecturers"
							options={lecturers}
							className="basic-multi-select fs-6 mb-3"
							classNamePrefix="select"
							isOptionSelected={isOptionSelected}
							isSearchable={true}
							placeholder="Chọn giảng viên"
							ref={lecturerSelect}
						/>
					</Form.Group>

					<FloatingLabel>
						<Button type="submit" variant="primary" className="mb-3 fs-6">
							Đăng nhập
						</Button>
					</FloatingLabel>
				</Form>
			</Stack>
		</>
	);
}

export default AddThesis;
