import { useEffect, useState } from "react";
import API, { endpoints } from "../../configs/API";
import { useParams } from "react-router-dom";

function ThesisDetail() {
	const [thesis, setThesis] = useState();
	const { thesisId } = useParams();

	useEffect(() => {
		const loadThesis = async () => {
			const response = await API.get(endpoints["thesisDetail"](1));
			setThesis(response.data);
			console.log(thesis);
		};
		loadThesis();
	}, [thesisId]);

	return (
		<>
			{thesis ? (
				<>
					<div className="w-75 thesis-item my-4 mx-auto">
						<h2>{thesis.name}</h2>
						<div>Sinh viên thực hiện: Ngô Văn Lâu</div>
						<div>Giảng viên hướng dẫn: Dương Hữu Thành</div>
						<div>Hội đồng bảo vệ: Hội đồng 1</div>
						<div>Điểm: {thesis.score}</div>
						<div>
							Cập nhật lần cuối: {new Date(thesis.updateDate).toLocaleString()}
						</div>
					</div>
				</>
			) : (
				<></>
			)}
		</>
	);
}

export default ThesisDetail;
