import axios from "axios";

const appContext = "thesisSpringApplication_war";

export let endpoints = {
	initAccount: (userId) => `${appContext}/api/users/${userId}/setInitAcc`,
};

export default axios.create({
	baseURL: "http://localhost:8080/",
});
