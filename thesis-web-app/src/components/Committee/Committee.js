import { useCallback, useContext, useEffect, useState } from "react";
import "./Committee.css";
import { Button, Col, Form, InputGroup, Row, Stack } from "react-bootstrap";
import { Link } from "react-router-dom";
import API, { authAPI, endpoints } from "../../configs/API";
import { isAcademicManager, isLecturer } from "../Common/Common";
import { LoadingContext, UserContext } from "../../configs/Context";
import { Alert } from "@mui/material";

function Committee() {
	const [committees, setCommittees] = useState([]);
	const [user] = useContext(UserContext);
	const [loading, loadingDispatch] = useContext(LoadingContext);

	const loadCommittee = useCallback(async () => {
		try {
			let response;
			if (isAcademicManager(user))
				response = await API.get(endpoints["committees"]);
			if (isLecturer(user))
				response = await authAPI().get(endpoints["committeesOfUser"]);

			setCommittees(response.data);
		} catch (ex) {
			console.log(ex);
		}

		console.log(Math.random());
	}, [user]);

	useEffect(() => {
		loadingDispatch({ type: "loading" });

		document.title = "Hội đồng";

		loadCommittee();
		loadingDispatch({ type: "unloading" });
	}, [loadCommittee, loadingDispatch]);

	return (
		<>
			{isAcademicManager(user) && (
				<Link to="/add-committee" className="btn btn-success mt-4">
					Thêm hội đồng
				</Link>
			)}

			<Row className="my-4">
				{committees.length < 1 ? (
					<>
						<Alert variant="filled" severity="info" className="w-50 mx-auto">
							Hiện không có hội đồng
						</Alert>
					</>
				) : (
					<>
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

									{isAcademicManager(user) && (
										<Button variant="danger" className="my-2 ms-auto">
											Đóng hội đồng
										</Button>
									)}
								</Stack>
							</Col>
						))}
					</>
				)}
			</Row>
		</>
	);
}

export default Committee;
