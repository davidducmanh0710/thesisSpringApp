import axios from "axios";
import cookies from "react-cookies";

const SERVER_CONTEXT = "thesisSpringApplication_war";
const SERVER = "http://localhost:8080";

export let endpoints = {
	initAccount: (userId) => `/api/users/${userId}/setInitAcc/`,
	lecturers: `/api/users/lecturers/`,
	students: `/api/users/students/`,
	getTwoRoleList: `/api/users/role/get2RoleList/`,
	theses: `/api/theses/`,
	committees: `/api/committees/`,
	thesisDetail: (thesisId) => `/api/theses/${thesisId}/`,
	activeCommittees: `/api/committees/active/`,
	addOrUpdateCommitteeForThesis: `/api/theses/committee/`,
	noneThesisStudents: `/api/users/students/noneThesis/`,
	criteria: `/api/criteria/`,
	login: "/api/users/login/",
	currentUser: "/api/users/current-user/",
};

export const authAPI = () => {
	return axios.create({
		baseURL: `${SERVER}/${SERVER_CONTEXT}`,
		headers: {
			Authorization: cookies.load("token"),
		},
	});
};

export default axios.create({
	baseURL: `${SERVER}/${SERVER_CONTEXT}`,
});
