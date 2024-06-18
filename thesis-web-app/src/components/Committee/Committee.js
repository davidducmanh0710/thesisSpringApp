import { useCallback, useContext, useEffect, useState } from "react";
import "./Committee.css";
import { Button, Col, Form, InputGroup, Row, Stack } from "react-bootstrap";
import { Link } from "react-router-dom";
import API, { authAPI, endpoints } from "../../configs/API";
import {
	CustomerSnackbar,
	isAcademicManager,
	isLecturer,
} from "../Common/Common";
import { LoadingContext, UserContext } from "../../configs/Context";
import { Alert, Pagination } from "@mui/material";

function Committee() {
	const [committees, setCommittees] = useState([]);
	const [user] = useContext(UserContext);
	const [, loadingDispatch] = useContext(LoadingContext);
	const [open, setOpen] = useState(false);
	const [data, setData] = useState({
		message: "Thành công",
		severity: "success",
	});
	const [page, setPage] = useState(1);
	const [totalPages, setTotalPages] = useState(1);

	const loadCommittee = useCallback(async () => {
		try {
			let response;
			if (isAcademicManager(user)) {
				const url = `${endpoints["committees"]}?page=${page}`;
				response = await API.get(url);
				if (response.status === 200) {
					setCommittees(response.data.result);
					setTotalPages(response.data.totalPages);
				}
			}

			if (isLecturer(user)) {
				response = await authAPI().get(endpoints["committeesOfUser"]);
				if (response.status === 200) {
					setCommittees(response.data);
				}
			}
		} catch (ex) {
			console.log(ex);
		}
	}, [page]);

	useEffect(() => {
		loadingDispatch({ type: "loading" });
		document.title = "Hội đồng";
		loadCommittee();
		loadingDispatch({ type: "unloading" });
	}, [loadCommittee, loadingDispatch]);

	const handleCloseCommittee = async (committeeId) => {
		loadingDispatch({ type: "loading" });

		try {
			const response = await authAPI().patch(
				endpoints["closeCommittee"](committeeId)
			);

			if (response.status === 200) {
				setData({
					message: "Thành công",
					severity: "success",
				});

				setOpen(true);

				setTimeout(() => {
					setOpen(false);
				}, 2000);

				setCommittees(response.data);
			}
		} catch {
			setData({
				message: "Thất bại",
				severity: "error",
			});

			setOpen(true);

			setTimeout(() => {
				setOpen(false);
			}, 2000);
		}

		loadingDispatch({ type: "unloading" });
	};

	return (
		<>
			<CustomerSnackbar
				open={open}
				message={data.message}
				severity={data.severity}
			/>

			{isAcademicManager(user) && (
				<>
					<Link to="/add-committee" className="btn btn-success mt-4">
						Thêm hội đồng
					</Link>
					<Pagination
						count={totalPages}
						color="primary"
						className="mt-4"
						onChange={(event, value) => setPage(value)}
					/>
				</>
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
										<>
											{committee.active ? (
												<>
													<Button
														onClick={() => handleCloseCommittee(committee.id)}
														variant="danger"
														className="my-2 ms-auto">
														Đóng hội đồng
													</Button>
												</>
											) : (
												<>
													<Button
														onClick={() => handleCloseCommittee(committee.id)}
														variant="primary"
														className="my-2 ms-auto">
														Mở hội đồng
													</Button>
												</>
											)}
										</>
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
