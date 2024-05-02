import { useEffect } from "react";
import "./Committee.css";
import { Button, Col, Form, InputGroup, Row, Stack } from "react-bootstrap";
import { Link } from "react-router-dom";

function Committee() {
	useEffect(() => {
		document.title = "Hội đồng";
	}, []);

	return (
		<>
			<Link to="/add-committee" className="btn btn-success mt-4">
				Thêm hội đồng
			</Link>

			<Row className="my-4">
				<Col md={6} className="thesis-item my-2 w-100">
					<Stack>
						<h5>Hội đồng 1</h5>
						<Row>
							<Col md={4}>
								<InputGroup className="my-2">
									<InputGroup.Text className="w-25">Chủ tịch</InputGroup.Text>
									<Form.Control type="text" value="Ngô Văn Lâu" disabled />
								</InputGroup>
							</Col>

							<Col md={4}>
								<InputGroup className="my-2">
									<InputGroup.Text className="w-25">Thư kí</InputGroup.Text>
									<Form.Control type="text" value="Ngô Văn Lâu" disabled />
								</InputGroup>
							</Col>

							<Col md={4}>
								<InputGroup className="my-2">
									<InputGroup.Text className="w-25">Phản biện</InputGroup.Text>
									<Form.Control type="text" value="Ngô Văn Lâu" disabled />
								</InputGroup>
							</Col>

							<Col md={4}>
								<InputGroup className="my-2">
									<InputGroup.Text className="w-25">Thành viên</InputGroup.Text>
									<Form.Control type="text" value="Ngô Văn Lâu" disabled />
								</InputGroup>
							</Col>

							<Col md={4}>
								<InputGroup className="my-2">
									<InputGroup.Text className="w-25">Thành viên</InputGroup.Text>
									<Form.Control type="text" value="Ngô Văn Lâu" disabled />
								</InputGroup>
							</Col>
						</Row>

						<Button variant="danger" className="my-2 ms-auto">
							Đóng hội đồng
						</Button>
					</Stack>
				</Col>
			</Row>
		</>
	);
}

export default Committee;
