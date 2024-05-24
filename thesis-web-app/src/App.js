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
import Criteria from "./components/Criteria/Criteria";
import Score from "./components/Score/Score";
import { useReducer } from "react";
import { LoadingReducer, UserReducer } from "./configs/Reducer";
import { LoadingContext, UserContext } from "./configs/Context";
import ChangeAvatarAndPassword from "./components/ChangeAvatarAndPassword/ChangeAvatarAndPassword";
import {
	isAcademicManager,
	isAccountInit,
	isLecturer,
} from "./components/Common/Common";

function App() {
	const [user, userDispatch] = useReducer(UserReducer, null);
	const [loading, loadingDispatch] = useReducer(LoadingReducer, false);

	return (
		<BrowserRouter>
			<UserContext.Provider value={[user, userDispatch]}>
				<LoadingContext.Provider value={[loading, loadingDispatch]}>
					{user === null ? (
						<>
							<Container>
								<Routes>
									<Route path="/*" element={<Login />} />
								</Routes>
							</Container>
						</>
					) : (
						<>
							{isAccountInit(user) ? (
								<>
									<Header />
									<Container>
										<Routes>
											<Route path="/" element={<Thesis />} />
											<Route
												path="/theses/:thesisId"
												element={<ThesisDetail />}
											/>
											<Route path="/init-account" element={<InitAccount />} />
											<Route path="/user-detail" element={<UserDetail />} />
											<Route
												path="/user-detail/change"
												element={<ChangeAvatarAndPassword />}
											/>

											{isAcademicManager(user) && (
												<>
													<Route path="/add-thesis" element={<AddThesis />} />
													<Route path="/committees" element={<Committee />} />
													<Route
														path="/add-committee"
														element={<AddCommittee />}
													/>
													<Route path="/lecturers" element={<Lecturer />} />
													<Route path="/students" element={<Student />} />
													<Route path="/criteria" element={<Criteria />} />
												</>
											)}

											{isLecturer(user) && (
												<>
													<Route
														path="/theses/:thesisId/score"
														element={<Score />}
													/>
													<Route path="/committees" element={<Committee />} />
												</>
											)}
										</Routes>
									</Container>
									<Footer />
								</>
							) : (
								<>
									<Container>
										<Routes>
											<Route path="/*" element={<InitAccount />} />
										</Routes>
									</Container>
								</>
							)}
						</>
					)}
				</LoadingContext.Provider>
			</UserContext.Provider>
		</BrowserRouter>
	);
}

export default App;
