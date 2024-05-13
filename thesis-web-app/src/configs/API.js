import axios from "axios";

const SERVER_CONTEXT = "thesisSpringApplication_war";
const SERVER = "http://localhost:8080";

export let endpoints = {
	initAccount: (userId) => `/${SERVER_CONTEXT}/api/users/${userId}/setInitAcc`,
	lecturers: `/${SERVER_CONTEXT}/api/users/lecturers`,
	students: `/${SERVER_CONTEXT}/api/users/students`,
	getTwoRoleList: `/${SERVER_CONTEXT}/api/users/role/get2RoleList`,
	theses: `/${SERVER_CONTEXT}/api/theses/`,
	committees: `/${SERVER_CONTEXT}/api/committees/`,
	thesisDetail: (thesisId) => `/${SERVER_CONTEXT}/api/theses/${thesisId}/`,
};

export default axios.create({
	baseURL: SERVER,
});
