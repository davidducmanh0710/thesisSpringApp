import { useEffect } from "react";
import "./Lecturer.css";
import { Button, Card, Col, Image, Row, Stack } from "react-bootstrap";

function Lecturer() {
	useEffect(() => {
		document.title = "Giảng viên";
	}, []);

	return (
		<div className="d-flex flex-wrap my-4">
			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="py-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>

			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="p-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>

			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="p-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>

			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="p-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>

			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="p-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>

			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="p-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>
			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="p-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>

			<div className="p-2">
				<div className="lecturer-item">
					<Stack gap={3} direction="horizontal" className="p-2 px-3">
						<Image src="https://github.com/mdo.png" width={50} roundedCircle />
						<div>Ngô Văn Lâu</div>
					</Stack>
				</div>
			</div>
		</div>
	);
}

export default Lecturer;
