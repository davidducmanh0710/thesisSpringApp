import { useEffect, useState } from "react";
import "./Thesis.css";
import { Button, Col, Row, Stack } from "react-bootstrap";
import { Link } from "react-router-dom";
import API, { endpoints } from "../../configs/API";

function Thesis() {
	const [theses, setTheses] = useState([]);

	useEffect(() => {
		document.title = "Trang chủ";

		const loadTheses = async () => {
			let response = await API.get(endpoints["theses"]);

			setTheses(response.data);
		};

		loadTheses();
	}, []);

	return (
		<>
			<Link to="add-thesis" className="btn btn-success mt-4">
				Thêm khóa luận
			</Link>

			<Row className="my-4">
				{theses.map((thesis) => (
					<Col md={6}>
						<Row className="thesis-item my-3 w-100">
							<Col>
								<h5>{thesis.name}</h5>
								<h6>
									Điểm:{" "}
									{thesis.score !== null ? thesis.score : "Chưa chấm điểm"}
								</h6>
								<div>
									Cập nhật lần cuối:{" "}
									{new Date(thesis.updateDate).toLocaleString()}
								</div>
							</Col>
							<Col md="auto" className="ms-auto">
								<Stack gap={2} direction="vertical">
									<Button variant="success">Xem chi tiết</Button>
									<Button variant="danger">Xóa</Button>
								</Stack>
							</Col>
						</Row>
					</Col>
				))}
			</Row>
		</>
	);
}

export default Thesis;
