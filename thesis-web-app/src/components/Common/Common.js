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
