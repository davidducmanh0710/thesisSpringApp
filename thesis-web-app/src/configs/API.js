import axios from "axios";

const SERVER_CONTEXT = "/thesisSpringApplication_war";
const SERVER = "http://localhost:8080";

export let endpoints = {
	initAccount: (userId) => `${SERVER_CONTEXT}/api/users/${userId}/setInitAcc`,
	lecturers: `${SERVER_CONTEXT}/api/users/role/lecturer`,
	getTwoRoleList: `${SERVER_CONTEXT}/api/users/role/get2RoleList`,
	theses: `${SERVER_CONTEXT}/api/theses/`,
};

export default axios.create({
	baseURL: SERVER,
});
