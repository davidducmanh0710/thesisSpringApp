import { useCallback, useEffect, useState } from "react";
import API, { endpoints } from "../../configs/API";
import { Button, FloatingLabel, Form, Table } from "react-bootstrap";

function Criteria() {
	const [criteria, setCriteria] = useState([]);
	const [hidden, setHidden] = useState(true);
	const [name, setName] = useState("");

	const loadCriteria = useCallback(async () => {
		const response = await API.get(endpoints["criteria"]);

		setCriteria(response.data);
	}, []);

	useEffect(() => {
		document.title = "Tiêu chí";

		loadCriteria();
	}, [loadCriteria]);

	const changeHidden = () => {
		setHidden(!hidden);
	};

	const addCriteria = () => {
		const add = async () => {
			const response = await API.post(endpoints["criteria"], {
				name: name,
			});

			if (response.status === 201) {
				setName("");
				setHidden(true);
				alert("Thêm tiêu chí thành công");
				setCriteria(response.data);
			} else {
				alert("Thêm tiêu chí thất bại");
			}
		};

		add();
	};

	return (
		<>
			<div className="my-4">
				<div hidden={hidden} className="criteria-item my-4 w-100">
					<FloatingLabel
						controlId="floatingInput"
						label="Tên tiêu chí"
						className="mb-3">
						<Form.Control
							type="text"
							placeholder="Nhập tên khóa luận"
							className="mb-3"
							value={name}
							onChange={(e) => setName(e.target.value)}
						/>
					</FloatingLabel>

					<Button variant="success" onClick={addCriteria}>
						Thêm
					</Button>
				</div>

				<Button variant={hidden ? "primary" : "danger"} onClick={changeHidden}>
					{hidden ? "Thêm tiêu chí" : "Ẩn thêm tiêu chí"}
				</Button>
			</div>

			<Table striped hover>
				<thead>
					<tr>
						<th>ID</th>
						<th>Tên tiêu chí</th>
					</tr>
				</thead>
				<tbody>
					{criteria.map((c) => (
						<tr>
							<td>{c.id}</td>
							<td>{c.name}</td>
						</tr>
					))}
				</tbody>
			</Table>
		</>
	);
}

export default Criteria;
