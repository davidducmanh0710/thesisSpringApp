import { useContext } from "react";
import { UserContext } from "../../configs/Context";
import { Image } from "react-bootstrap";

function ChangeAvatarAndPassword() {
	const [user] = useContext(UserContext);

	console.log(user);

	return (
		<>
			<Image
				src={user.avatar}
				width="200"
				height="200"
				alt="Ảnh đại diện"
				roundedCircle
				className="my-4 mx-auto"
			/>
		</>
	);
}

export default ChangeAvatarAndPassword;
