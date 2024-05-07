import { useEffect, useState } from "react";
import API, { endpoints } from "../../configs/API";

function ThesisDetail(thesisId) {
	const [thesis, setThesis] = useState();

	useEffect(() => {
		const loadThesis = async () => {
			const response = await API.get(endpoints["thesisDetail"](thesisId));

			setThesis(response.data);
		};
		loadThesis();
	});

	return <>{thesis}</>;
}

export default ThesisDetail;
