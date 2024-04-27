import { useState } from "react";
import { Button, FloatingLabel, Form, Stack } from "react-bootstrap";
import Select from "react-select";
import "../AddCommittee/AddCommittee.css";

function AddCommittee() {
	const [lecturers, setLecturers] = useState([
		{ value: 4, label: "Giảng Viên 1" },
		{ value: 5, label: "Giảng Viên 2" },
		{ value: 6, label: "Giảng Viên 3" },
	]);
	const [hidden, setHidden] = useState(true);
	// const [data, setData] = useState();

	const isOptionSelected = (_, selectValue) => {
		return selectValue.length > 1;
	};

	const addCommittee = (event) => {
		event.preventDefault();
	};

	const changeHidden = () => {
		setHidden(!hidden);
	};

	const addData = (e) => {
		setLecturers(lecturers.filter((l) => l.value !== e.value));
	};

	return (
		<div>
			<h1 className="text-success text-center my-3">
				Thêm hội đồng bảo vệ khóa luận
			</h1>
			<Stack>
				<Form className="w-50 mx-auto" onSubmit={addCommittee}>
					<FloatingLabel
						controlId="floatingInput"
						label="Tên hội đồng "
						className="mb-3">
						<Form.Control
							type="text"
							placeholder="Nhập tên hội đồng"
							className="mb-3"
						/>
					</FloatingLabel>
					<Form.Group className="mb-3">
						<Form.Label>Chủ tịch</Form.Label>
						<Select
							name="lecturers"
							options={lecturers}
							className="basic-single fs-6 mb-3"
							classNamePrefix="select"
							isSearchable={true}
							placeholder="Chọn giảng viên"
							hideSelectedOptions={true}
							onChange={addData}
						/>
					</Form.Group>
					<Form.Group className="mb-3">
						<Form.Label>Thư kí</Form.Label>
						<Select
							name="lecturers"
							options={lecturers}
							className="basic-single fs-6 mb-3"
							classNamePrefix="select"
							isSearchable={true}
							placeholder="Chọn giảng viên"
							hideSelectedOptions={true}
							onChange={addData}
						/>
					</Form.Group>
					<Form.Group className="mb-3">
						<Form.Label>Giảng viên phản biện</Form.Label>
						<Select
							name="lecturers"
							options={lecturers}
							className="basic-single fs-6 mb-3"
							classNamePrefix="select"
							isSearchable={true}
							placeholder="Chọn giảng viên"
							hideSelectedOptions={true}
							onChange={addData}
						/>
					</Form.Group>
					<Form.Group hidden={hidden} className="mb-3">
						<Form.Label>Thành viên</Form.Label>
						<Select
							isMulti
							name="lecturers"
							options={lecturers}
							className="basic-multi-select fs-6 mb-3"
							classNamePrefix="select"
							isOptionSelected={isOptionSelected}
							isSearchable={true}
							placeholder="Chọn giảng viên"
						/>
					</Form.Group>
					<FloatingLabel>
						<Button
							onClick={changeHidden}
							variant="success"
							className="mb-3 fs-6">
							{hidden ? "Thêm thành viên" : "Ẩn thành viên"}
						</Button>
					</FloatingLabel>
					<FloatingLabel>
						<Button type="submit" variant="primary" className="mb-3 fs-6">
							Thêm hội đồng
						</Button>
					</FloatingLabel>
				</Form>
			</Stack>
		</div>
	);
}

export default AddCommittee;
