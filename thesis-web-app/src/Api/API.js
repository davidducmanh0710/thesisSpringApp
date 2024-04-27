import axios from "axios"

export let endpoints = {
    'initAccount': (userId) => `api/users/${userId}/setInitAcc`
}

export default axios.create({
    baseURL: 'http://localhost:8080/thesisSpringApplication_war/'
})
