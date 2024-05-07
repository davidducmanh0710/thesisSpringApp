import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import Header from "./layout/Header/Header";
import Footer from "./layout/Footer/Footer";
import UserDetail from "./components/UserDetail/UserDetail";
import AddThesis from "./components/AddThesis/AddThesis";
import AddCommittee from "./components/AddCommittee/AddCommittee";
import InitAccount from "./components/InitAccount/InitAccount";
import Login from "./components/Login/Login";
import Thesis from "./components/Thesis/Thesis";
import Committee from "./components/Committee/Committee";
import Lecturer from "./components/Lecturer/Lecturer";
import Student from "./components/Student/Student";
import ThesisDetail from "./components/ThesisDetail/ThesisDetail";

function App() {
	return (
		<BrowserRouter>
			<Header />
			<Container>
				<Routes>
					<Route path="/" element={<Thesis />} />
					<Route path="/add-thesis" element={<AddThesis />} />
					<Route path="/committees" element={<Committee />} />
					<Route path="/add-committee" element={<AddCommittee />} />
					<Route path="/login" element={<Login />} />
					<Route path="/init-account" element={<InitAccount />} />
					<Route path="/user-detail" element={<UserDetail />} />
					<Route path="/lecturers" element={<Lecturer />} />
					<Route path="/students" element={<Student />} />
				</Routes>
			</Container>
			<Footer />
		</BrowserRouter>
	);
}

export default App;
