import { useEffect, useRef } from "react";
import { Button, FloatingLabel, Form, Stack } from "react-bootstrap";
import Select from "react-select";

function AddThesis() {
	const studentList = [
		{ value: 1, label: "Sinh Viên 1" },
		{ value: 2, label: "Sinh Viên 2" },
		{ value: 3, label: "Sinh Viên 3" },
	];

	const lecturerList = [
		{ value: 4, label: "Giảng Viên 1" },
		{ value: 5, label: "Giảng Viên 2" },
		{ value: 6, label: "Giảng Viên 3" },
	];

	const students = useRef();
	const lecturers = useRef();

	useEffect(() => {
		document.title = "Thêm khóa luận";
	}, []);

	const isOptionSelected = (_, selectValue) => {
		return selectValue.length > 1;
	};

	const addThesis = (event) => {
		event.preventDefault();
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
						/>
					</FloatingLabel>

					<Form.Group className="mb-3">
						<Form.Label>Danh sách sinh viên</Form.Label>
						<Select
							isMulti
							name="students"
							options={studentList}
							className="basic-multi-select fs-6 mb-3"
							classNamePrefix="select"
							isOptionSelected={isOptionSelected}
							isSearchable={true}
							placeholder="Chọn sinh viên"
							useRef={students}
						/>
					</Form.Group>

					<Form.Group className="mb-3">
						<Form.Label>Danh sách giảng viên</Form.Label>
						<Select
							isMulti
							name="lecturers"
							options={lecturerList}
							className="basic-multi-select fs-6 mb-3"
							classNamePrefix="select"
							isOptionSelected={isOptionSelected}
							isSearchable={true}
							placeholder="Chọn giảng viên"
							useRef={lecturers}
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
