import { useContext, useEffect } from "react";
import {
	Col,
	Container,
	Form,
	Image,
	InputGroup,
	Row,
	Stack,
} from "react-bootstrap";
import { UserContext } from "../../configs/Context";

function UserDetail() {
	const [user] = useContext(UserContext);

	useEffect(() => {
		document.title = "Thông tin cá nhân";
	}, []);

	return (
		<>
			{user === null ? (
				<></>
			) : (
				<>
					<Stack>
						<h1 className="text-center text-success my-4">Thông tin cá nhân</h1>

						<Image
							src={user.avatar}
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
											<Form.Control
												type="text"
												value={user.lastName}
												disabled
												required
											/>
										</Form.Group>
									</Col>

									<Col>
										<Form.Group controlId="firstName">
											<Form.Label>Tên</Form.Label>
											<Form.Control
												type="text"
												value={user.firstName}
												required
												disabled
											/>
										</Form.Group>
									</Col>
								</Row>

								<Row className="mb-3">
									<Form.Group controlId="universityId" class="col-sm-6">
										<Form.Label>Mã ID</Form.Label>
										<Form.Control
											type="number"
											value={user.useruniversityid}
											disabled
											required
										/>
									</Form.Group>

									<Form.Group controlId="birthday" class="col-sm-6">
										<Form.Label>Ngày sinh</Form.Label>
										<Form.Control
											type="text"
											value={new Date(user.birthday).toLocaleDateString()}
											disabled
											required
										/>
									</Form.Group>
								</Row>

								<Row className="mb-3">
									<Form.Group controlId="faculty" class="col-sm-6">
										<Form.Label>Khoa</Form.Label>
										<Form.Control
											type="text"
											value={user.faculty}
											required
											disabled
										/>
									</Form.Group>

									<Form.Group controlId="phone" class="col-sm-6">
										<Form.Label>Phone</Form.Label>
										<InputGroup>
											<InputGroup.Text>84</InputGroup.Text>
											<Form.Control
												type="number"
												value={user.phone.substring(1)}
												required
												disabled
											/>
										</InputGroup>
									</Form.Group>
								</Row>

								<Row className="mb-3">
									<Form.Group controlId="email" class="col-sm-6">
										<Form.Label>Email</Form.Label>
										<InputGroup>
											<InputGroup.Text>@</InputGroup.Text>
											<Form.Control
												type="text"
												value={user.email}
												required
												disabled
											/>
										</InputGroup>
									</Form.Group>
								</Row>
							</Container>
						</Form>
					</Stack>
				</>
			)}
		</>
	);
}

export default UserDetail;
