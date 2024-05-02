import { useEffect } from "react";
import "./Thesis.css";
import { Button, Col, Row, Stack } from "react-bootstrap";
import { Link } from "react-router-dom";

function Thesis() {
	useEffect(() => {
		document.title = "Trang chủ";
	}, []);

	return (
		<>
			<Link to="add-thesis" className="btn btn-success mt-4">
				Thêm khóa luận
			</Link>

			<Row className="my-4">
				<Col md={6}>
					<Row className="thesis-item my-2 w-100">
						<Col>
							<h5>Khóa luận 1</h5>
							<h6>Điểm: 9.0</h6>
							<div>Cập nhật lần cuối: 28/04/2004</div>
						</Col>
						<Col md="auto" className="ms-auto">
							<Stack gap={2} direction="vertical">
								<Button variant="success">Xem chi tiết</Button>
								<Button variant="danger">Xóa</Button>
							</Stack>
						</Col>
					</Row>
				</Col>

				<Col md={6}>
					<Row className="thesis-item my-2 w-100">
						<Col>
							<h5>Khóa luận 1</h5>
							<h6>Điểm: 9.0</h6>
							<div>Cập nhật lần cuối: 28/04/2004</div>
						</Col>
						<Col md="auto" className="ms-auto">
							<Stack gap={2} direction="vertical">
								<Button variant="success">Xem chi tiết</Button>
								<Button variant="danger">Xóa</Button>
							</Stack>
						</Col>
					</Row>
				</Col>

				<Col md={6}>
					<Row className="thesis-item my-2 w-100">
						<Col>
							<h5>Khóa luận 1</h5>
							<h6>Điểm: 9.0</h6>
							<div>Cập nhật lần cuối: 28/04/2004</div>
						</Col>
						<Col md="auto" className="ms-auto">
							<Stack gap={2} direction="vertical">
								<Button variant="success">Xem chi tiết</Button>
								<Button variant="danger">Xóa</Button>
							</Stack>
						</Col>
					</Row>
				</Col>

				<Col md={6}>
					<Row className="thesis-item my-2 w-100">
						<Col>
							<h5>Khóa luận 1</h5>
							<h6>Điểm: 9.0</h6>
							<div>Cập nhật lần cuối: 28/04/2004</div>
						</Col>
						<Col md="auto" className="ms-auto">
							<Stack gap={2} direction="vertical">
								<Button variant="success">Xem chi tiết</Button>
								<Button variant="danger">Xóa</Button>
							</Stack>
						</Col>
					</Row>
				</Col>
			</Row>
		</>
	);
}

export default Thesis;
