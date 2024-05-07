import { useEffect, useState } from "react";
import "./Lecturer.css";
import { Image, Table } from "react-bootstrap";
import API, { endpoints } from "../../configs/API";

function Lecturer() {
	const [lecturers, setLecturers] = useState([]);

	useEffect(() => {
		document.title = "Giảng viên";

		const loadLecturers = async () => {
			let response = await API.get(endpoints["lecturers"]);

			setLecturers(response.data);
		};

		loadLecturers();
	}, []);

	return (
		<Table hover>
			<thead>
				<tr>
					<th></th>
					<th>ID</th>
					<th>Họ</th>
					<th>Tên</th>
					<th>Email</th>
					<th>Số điện thoại</th>
					<th>Giới tính</th>
					<th>Ngày sinh</th>
				</tr>
			</thead>
			<tbody>
				{lecturers.map((lecturer) => (
					<tr>
						<td>
							<Image src={lecturer.avatar} width={50} roundedCircle />
						</td>
						<td>{lecturer.useruniversityid}</td>
						<td>{lecturer.lastName}</td>
						<td>{lecturer.firstName}</td>
						<td>{lecturer.email}</td>
						<td>{lecturer.phone}</td>
						<td>{lecturer.gender === "male" ? "Nam" : "Nữ"}</td>
						<td>{new Date(lecturer.birthday).toLocaleDateString()}</td>
					</tr>
				))}
			</tbody>
		</Table>
	);
}

export default Lecturer;
