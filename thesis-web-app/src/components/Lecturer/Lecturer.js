import { useEffect, useState } from "react";
import "./Lecturer.css";
import { Image, Stack } from "react-bootstrap";
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
		<div className="d-flex flex-wrap my-4">
			{lecturers.map((lecturer) => (
				<div className="p-2">
					<div className="lecturer-item">
						<Stack gap={3} direction="horizontal" className="py-2 px-3">
							<Image src={lecturer.avatar} width={50} roundedCircle />
							<div>
								{lecturer.lastName} {lecturer.firstName}
							</div>
						</Stack>
					</div>
				</div>
			))}
		</div>
	);
}

export default Lecturer;
