import { useEffect, useState } from "react";
import "./Committee.css";
import { Button, Col, Form, InputGroup, Row, Stack } from "react-bootstrap";
import { Link } from "react-router-dom";
import API, { endpoints } from "../../configs/API";

function Committee() {
	const [committees, setCommittees] = useState([]);

	useEffect(() => {
		document.title = "Hội đồng";

		const loadCommittee = async () => {
			try {
				const response = await API.get(endpoints["committees"]);

				setCommittees(response.data);
			} catch (ex) {
				console.log(ex);
			}
		};

		loadCommittee();
	}, []);

	return (
		<>
			<Link to="/add-committee" className="btn btn-success mt-4">
				Thêm hội đồng
			</Link>

			<Row className="my-4">
				{committees.map((committee) => (
					<Col key={committee.id} md={6} className="thesis-item my-3 w-100">
						<Stack>
							<h5>{committee.name}</h5>
							<Row>
								{committee.members.map((member) => (
									<Col md={4} key={member.user.id}>
										<InputGroup className="my-2">
											<InputGroup.Text className="w-25">
												{member.role}
											</InputGroup.Text>
											<Form.Control
												type="text"
												value={
													member.user.lastName + " " + member.user.firstName
												}
												disabled
											/>
										</InputGroup>
									</Col>
								))}
							</Row>

							<Button variant="danger" className="my-2 ms-auto">
								Đóng hội đồng
							</Button>
						</Stack>
					</Col>
				))}
			</Row>
		</>
	);
}

export default Committee;
