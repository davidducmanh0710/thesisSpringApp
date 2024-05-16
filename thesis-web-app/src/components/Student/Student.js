import { useEffect, useState } from "react";
import API, { endpoints } from "../../configs/API";
import { Image, Table } from "react-bootstrap";

function Student() {
	const [students, setStudents] = useState([]);

	useEffect(() => {
		document.title = "Sinh viên";

		const loadStudent = async () => {
			const response = await API.get(endpoints["students"]);

			setStudents(response.data);
		};

		loadStudent();
	}, []);

	return (
		<Table striped hover>
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
				{students.map((student) => (
					<tr key={student.id}>
						<td>
							<Image src={student.avatar} width={50} roundedCircle />
						</td>
						<td>{student.useruniversityid}</td>
						<td>{student.lastName}</td>
						<td>{student.firstName}</td>
						<td>{student.email}</td>
						<td>{student.phone}</td>
						<td>{student.gender === "male" ? "Nam" : "Nữ"}</td>
						<td>{new Date(student.birthday).toLocaleDateString()}</td>
					</tr>
				))}
			</tbody>
		</Table>
	);
}

export default Student;
