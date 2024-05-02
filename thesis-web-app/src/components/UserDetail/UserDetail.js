import { useEffect } from "react";
import {
	Col,
	Container,
	Form,
	Image,
	InputGroup,
	Row,
	Stack,
} from "react-bootstrap";

function UserDetail() {
	useEffect(() => {
		document.title = "Thông tin cá nhân";
	}, []);

	return (
		<Stack>
			<h1 className="text-center text-success my-4">Thông tin cá nhân</h1>
			<Image
				src="https://github.com/mdo.png"
				width="200"
				height="200"
				alt="Ảnh đại diện"
				roundedCircle
				className="my-4 mx-auto"
			/>

			<Form class="needs-validation">
				<Container fluid className="w-50 mx-auto my-4">
					<Row className="mb-3">
						<Col>
							<Form.Group controlId="lastName">
								<Form.Label>Họ</Form.Label>
								<Form.Control type="text" value="Ngô Văn" disabled required />
							</Form.Group>
						</Col>

						<Col>
							<Form.Group controlId="firstName">
								<Form.Label>Tên</Form.Label>
								<Form.Control type="text" value="Lâu" required disabled />
							</Form.Group>
						</Col>
					</Row>

					<Row className="mb-3">
						<Form.Group controlId="universityId" class="col-sm-6">
							<Form.Label>Mã ID</Form.Label>
							<Form.Control
								type="number"
								value="2151053034"
								disabled
								required
							/>
						</Form.Group>

						<Form.Group controlId="birthday" class="col-sm-6">
							<Form.Label>Ngày sinh</Form.Label>
							<Form.Control type="text" value="29/06/2003" disabled required />
						</Form.Group>
					</Row>

					<Row className="mb-3">
						<Form.Group controlId="faculty" class="col-sm-6">
							<Form.Label>Khoa</Form.Label>
							<Form.Control
								type="text"
								value="Công nghệ thông tin"
								required
								disabled
							/>
						</Form.Group>

						<Form.Group controlId="major" class="col-sm-6">
							<Form.Label>Ngành</Form.Label>
							<Form.Control
								type="text"
								value="Công nghệ thông tin"
								disabled
								required
							/>
						</Form.Group>
					</Row>

					<Row className="mb-3">
						<Form.Group controlId="email" class="col-sm-6">
							<Form.Label>Email</Form.Label>
							<InputGroup>
								<InputGroup.Text>@</InputGroup.Text>
								<Form.Control
									type="text"
									value="ngovanlau2003@gmail.com"
									required
									disabled
								/>
							</InputGroup>
						</Form.Group>

						<Form.Group controlId="phone" class="col-sm-6">
							<Form.Label>Phone</Form.Label>
							<InputGroup>
								<InputGroup.Text>84</InputGroup.Text>
								<Form.Control
									type="number"
									value="393131096"
									required
									disabled
								/>
							</InputGroup>
						</Form.Group>
					</Row>
				</Container>
			</Form>
		</Stack>
	);
}

export default UserDetail;
