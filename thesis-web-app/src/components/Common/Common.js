import { Alert, Snackbar } from "@mui/material";

export const isAccountInit = (user) => {
	if (user !== null && user.user.active) return true;

	return false;
};

export const isAcademicManager = (user) => {
	if (user !== null && user.role.name === "ROLE_GIAOVU") return true;

	return false;
};

export const isLecturer = (user) => {
	if (user !== null && user.role.name === "ROLE_GIANGVIEN") return true;

	return false;
};

export const isStudent = (user) => {
	if (user !== null && user.role.name === "ROLE_SINHVIEN") return true;

	return false;
};

export const CustomerSnackbar = ({ open, message, severity }) => {
	return (
		<Snackbar
			anchorOrigin={{ vertical: "top", horizontal: "center" }}
			open={open}
			key={"top center"}>
			<Alert severity={severity} variant="filled" sx={{ width: "100%" }}>
				{message}
			</Alert>
		</Snackbar>
	);
};
