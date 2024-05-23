import { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import API, { endpoints } from "../../configs/API";
import { Button, Form, InputGroup } from "react-bootstrap";
import "../Score/Score.css";

function Score() {
	const { thesisId } = useParams();
	const [thesis, setThesis] = useState();
	const [criteria, setCriteria] = useState([]);
	const [scores, setScores] = useState([]);

	const loadThesis = useCallback(async () => {
		const response = await API.get(endpoints["thesisDetail"](thesisId));
		setThesis(response.data);
	}, [thesisId]);

	const loadCriteria = useCallback(async () => {
		const response = await API.get(endpoints["criteria"]);
		setCriteria(response.data);
	}, []);

	useEffect(() => {
		document.title = "Chấm điểm";

		loadThesis();
		loadCriteria();
	}, [loadThesis, loadCriteria]);

	return (
		<>
			{thesis ? (
				<>
					<div className="w-75 thesis-item my-4 mx-auto">
						<div>
							<h2>
								{thesis.thesis.name} -{" "}
								{thesis.students.map(
									(student) => student.lastName + " " + student.firstName
								)}
							</h2>
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

						<Form>
							{criteria.map((c) => (
								<Form.Group key={c.id} className="mb-3 thesis-item my-4 w-100">
									<Form.Label>Tiêu chí: {c.name}</Form.Label>

									<InputGroup>
										<InputGroup.Text id="basic-addon1">Điểm</InputGroup.Text>
										<Form.Control
											type="number"
											max={10}
											min={0}
											defaultValue={0}
											placeholder="Điểm"
										/>
									</InputGroup>
								</Form.Group>
							))}
						</Form>

						<div className="mt-4">
							<Button variant="success">Chấm điểm</Button>
						</div>
					</div>
				</>
			) : (
				<></>
			)}
		</>
	);
}

export default Score;
