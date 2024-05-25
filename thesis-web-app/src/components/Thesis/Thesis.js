import { useCallback, useContext, useEffect, useState } from "react";
import "./Thesis.css";
import { Button, Col, Row, Stack } from "react-bootstrap";
import { Link } from "react-router-dom";
import API, { authAPI, endpoints } from "../../configs/API";
import { LoadingContext, UserContext } from "../../configs/Context";
import { isAcademicManager } from "../Common/Common";
import { Alert } from "@mui/material";

function Thesis() {
	const [theses, setTheses] = useState([]);
	const [user] = useContext(UserContext);
	const [loading, loadingDispatch] = useContext(LoadingContext);

	const loadTheses = useCallback(async () => {
		let response;

		if (isAcademicManager(user)) response = await API.get(endpoints["theses"]);
		else response = await authAPI().get(endpoints["thesisOfUser"]);

		if (response) setTheses(response.data);
	}, []);

	useEffect(() => {
		loadingDispatch({ type: "loading" });
		document.title = "Trang chủ";
		loadTheses();
		loadingDispatch({ type: "unloading" });
	}, [loadTheses, loadingDispatch]);

	return (
		<>
			{isAcademicManager(user) && (
				<Link to="add-thesis" className="btn btn-success mt-4">
					Thêm khóa luận
				</Link>
			)}

			<Row className="my-4">
				{theses.length < 1 ? (
					<>
						<Alert variant="filled" severity="info" className="w-50 mx-auto">
							Hiện không có khóa luận
						</Alert>
					</>
				) : (
					<>
						{theses.map((thesis) => {
							const url = `theses/${thesis.id}`;
							return (
								<Col md={6} key={thesis.id}>
									<Row className="thesis-item my-3 w-100">
										<Col>
											<h5>{thesis.name}</h5>
											<h6>
												Điểm:{" "}
												{thesis.score !== null
													? thesis.score
													: "Chưa chấm điểm"}
											</h6>
											<div>
												Cập nhật lần cuối:{" "}
												{new Date(thesis.updateDate).toLocaleString()}
											</div>
										</Col>
										<Col md="auto" className="ms-auto">
											<Stack gap={2} direction="vertical">
												<Link to={url} className="btn btn-success">
													Xem chi tiết
												</Link>
												<Button variant="danger">Xóa</Button>
											</Stack>
										</Col>
									</Row>
								</Col>
							);
						})}
					</>
				)}
			</Row>
		</>
	);
}

export default Thesis;
