import { useCallback, useEffect, useState } from "react";
import API, { endpoints } from "../../configs/API";
import { Button, Form } from "react-bootstrap";
import Select from "react-select";
import { Link, useParams } from "react-router-dom";

function ThesisDetail() {
	const [thesis, setThesis] = useState();
	const { thesisId } = useParams();
	const [committees, setCommittees] = useState([]);
	const [hidden, setHidden] = useState(true);
	const [committee, setCommittee] = useState();

	const loadThesis = useCallback(async () => {
		const response = await API.get(endpoints["thesisDetail"](thesisId));
		setThesis(response.data);
	}, [thesisId]);

	const loadCommittees = useCallback(async () => {
		const response = await API.get(endpoints["activeCommittees"]);

		setCommittees(
			response.data.map((committee) => {
				return { value: committee.id, label: committee.name };
			})
		);
	}, []);

	useEffect(() => {
		document.title = "Chi tiết khóa luận";

		loadThesis();
		loadCommittees();
	}, [loadThesis, loadCommittees]);

	const changeHidden = () => {
		setHidden(!hidden);
	};

	const addCommittee = () => {
		const add = async () => {
			const response = await API.patch(
				endpoints["addOrUpdateCommitteeForThesis"],
				{
					thesisId: parseInt(thesisId),
					committeeId: committee,
				}
			);

			setThesis(response.data);
			loadCommittees();
		};
		add();
	};

	return (
		<>
			{thesis ? (
				<>
					<div className="w-75 thesis-item my-4 mx-auto">
						<h2>{thesis.thesis.name}</h2>
						<div>
							Sinh viên thực hiện:{" "}
							{thesis.students.map((student) => (
								<span key={student.id}>
									{student.lastName} {student.firstName}
								</span>
							))}
						</div>
						<div>
							Giảng viên hướng dẫn:{" "}
							{thesis.lecturers.map((lecturer) => (
								<span key={lecturer.id}>
									{lecturer.lastName} {lecturer.firstName}
								</span>
							))}
						</div>
						<div>
							Hội đồng bảo vệ:{" "}
							{thesis.committee === null
								? "Chưa có hội đồng"
								: thesis.committee.name}
						</div>

						<div>Điểm: {thesis.thesis.score}</div>
						<div>
							Cập nhật lần cuối:{" "}
							{new Date(thesis.thesis.updateDate).toLocaleString()}
						</div>

						<div hidden={hidden} className="thesis-item my-4 w-100">
							<Form.Group className="mb-3">
								<Form.Label>Chọn hội đồng</Form.Label>
								<Select
									name="committees"
									options={committees}
									className="basic-single fs-6 mb-3"
									classNamePrefix="select"
									isSearchable={true}
									placeholder="Chọn hội đồng"
									hideSelectedOptions={true}
									onChange={(e) => setCommittee(e.value)}
								/>
							</Form.Group>

							<Button variant="success" onClick={addCommittee}>
								{thesis.committee === null ? "Thêm" : "Chỉnh sửa"}
							</Button>
						</div>

						<div className="mt-4">
							{hidden ? (
								<>
									<Button variant="info" onClick={changeHidden}>
										{thesis.committee === null
											? "Thêm hội đồng"
											: "Chỉnh sửa hội đồng"}
									</Button>
								</>
							) : (
								<>
									<Button variant="danger" onClick={changeHidden}>
										{thesis.committee === null
											? "Ẩn thêm hội đồng"
											: "Ẩn chỉnh sửa hội đồng"}
									</Button>
								</>
							)}
						</div>

						<div className="mt-4">
							<Link
								to={`/theses/${thesisId}/score`}
								className="btn btn-success">
								Chấm điểm
							</Link>
						</div>
					</div>
				</>
			) : (
				<></>
			)}
		</>
	);
}

export default ThesisDetail;
