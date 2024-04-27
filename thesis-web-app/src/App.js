import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import Header from "./layout/Header/Header";
import Footer from "./layout/Footer/Footer";
import AddCommittee from "./components/AddCommittee/AddCommittee";

function App() {
	return (
		<BrowserRouter>
			<Header />
			<Container>
				<Routes>
					<Route path="/" element={<AddCommittee />} />
				</Routes>
			</Container>
			<Footer />
		</BrowserRouter>
	);
}

export default App;
