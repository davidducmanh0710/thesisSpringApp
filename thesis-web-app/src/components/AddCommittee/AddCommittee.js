import { useEffect, useState } from "react";
import { Button, FloatingLabel, Form, Stack } from "react-bootstrap";
import Select from "react-select";
import "../AddCommittee/AddCommittee.css";
import { useNavigate } from "react-router-dom";
import API, { endpoints } from "../../configs/API";

function AddCommittee() {
	const [name, setName] = useState("");
	const [chairman, setChairman] = useState({
		roleName: "Chủ tịch",
		userId: null,
	});
	const [secretary, setSecretary] = useState({
		roleName: "Thư kí",
		userId: null,
	});
	const [criticalLecturer, setCriticalLecturer] = useState({
		roleName: "Phản biện",
		userId: null,
	});

	const [lecturers, setLecturers] = useState([]);
	const [hidden, setHidden] = useState(true);
	// const [data, setData] = useState();
	const navigate = useNavigate();

	useEffect(() => {
		document.title = "Thêm hội đồng";

		const loadLecturers = async () => {
			const response = await API.get(endpoints["lecturers"]);

			setLecturers(
				response.data.map((l) => {
					return { value: l.id, label: `${l.lastName} ${l.firstName}` };
				})
			);
		};

		loadLecturers();
	}, []);

	const isOptionSelected = (_, selectValue) => {
		return selectValue.length > 1;
	};

	const addCommittee = (event) => {
		event.preventDefault();

		const add = async () => {
			const members = [];
			members.push(chairman);
			members.push(secretary);
			members.push(criticalLecturer);

			const committee = {
				name: name,
				committeeUserDtos: members,
			};

			const response = await API.post(endpoints["committees"], committee);

			if (response.status === 200) {
				alert("Thêm hội đồng thành công");
				navigate("/committees");
			} else {
				alert("Thêm hội đồng thất bại");
			}
		};

		add();
	};

	const changeHidden = () => {
		setHidden(!hidden);
	};

	// const addMembers = (role, event) => {
	// 	setMembers((current) => {
	// 		return [
	// 			...current,
	// 			{
	// 				roleName: role,
	// 				userId: event.value,
	// 			},
	// 		];
	// 	});
	// };

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
							onChange={(e) => setName(e.target.value)}
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
							onChange={(e) => setChairman({ ...chairman, userId: e.value })}
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
							onChange={(e) => setSecretary({ ...secretary, userId: e.value })}
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
							onChange={(e) =>
								setCriticalLecturer({ ...criticalLecturer, userId: e.value })
							}
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
