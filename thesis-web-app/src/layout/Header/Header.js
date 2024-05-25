import {
	Button,
	Container,
	Dropdown,
	Form,
	Image,
	Nav,
	NavDropdown,
	Navbar,
} from "react-bootstrap";
import { Link } from "react-router-dom";
import "../Header/Header.css";
import { useContext } from "react";
import { LoadingContext, UserContext } from "../../configs/Context";
import { isAcademicManager, isStudent } from "../../components/Common/Common";
import { LinearProgress } from "@mui/material";

function Header() {
	const [user, userDispatch] = useContext(UserContext);
	const [loading, loadingDispatch] = useContext(LoadingContext);

	const logout = () => {
		loadingDispatch({ type: "loading" });
		if (user !== null) {
			userDispatch({ type: "logout" });
		}
		loadingDispatch({ type: "unloading" });
	};

	return (
		<>
			<Navbar expand="lg" className="bg-body-tertiary">
				<Container>
					<Link to="/" className="navbar-brand">
						QUẢN LÝ KHÓA LUẬN
					</Link>
					<Navbar.Collapse id="navbarScroll">
						<Nav
							className="me-auto m-2 my-lg-0"
							style={{ maxHeight: "100px" }}
							navbarScroll>
							<Link to="/" className="nav-link">
								Trang chủ
							</Link>

							{!isStudent(user) && (
								<Link to="/committees" className="nav-link">
									Hội đồng
								</Link>
							)}

							{isAcademicManager(user) && (
								<>
									<Link to="/lecturers" className="nav-link">
										Giảng viên
									</Link>

									<Link to="/students" className="nav-link">
										Sinh viên
									</Link>

									<Link to="/criteria" className="nav-link">
										Tiêu chí
									</Link>
								</>
							)}

							{/* <NavDropdown title="Link" id="navbarScrollingDropdown">
								<NavDropdown.Item href="#action3">Action</NavDropdown.Item>

								<NavDropdown.Item href="#action4">
									Another action
								</NavDropdown.Item>

								<NavDropdown.Divider />

								<NavDropdown.Item href="#action5">
									Something else here
								</NavDropdown.Item>
							</NavDropdown> */}
						</Nav>

						<Form className="d-flex">
							<Form.Control
								type="search"
								placeholder="Tìm kiếm"
								className="me-2"
								aria-label="Search"
							/>
							<Button variant="outline-success">Search</Button>
						</Form>

						{user === null ? (
							<>
								<div className="ms-2">
									<Link to="/login" className="btn btn-primary me-2">
										Đăng nhập
									</Link>
								</div>
							</>
						) : (
							<>
								<Dropdown className="dropdown text-end ms-4">
									<Dropdown.Toggle className="header-btn-primary">
										<Image
											src={user === null ? "" : user.user.avatar}
											alt="mdo"
											width="50"
											height="50"
											className="rounded-circle"
										/>
									</Dropdown.Toggle>
									<Dropdown.Menu className="r-0 header-dropdown-menu">
										<Dropdown.Item>
											<Link className="dropdown-item" to="user-detail">
												Thông tin cá nhân
											</Link>
										</Dropdown.Item>

										<NavDropdown.Divider />

										<NavDropdown.Item>
											<Button className="dropdown-item" onClick={logout}>
												Đăng xuất
											</Button>
										</NavDropdown.Item>
									</Dropdown.Menu>
								</Dropdown>
							</>
						)}
					</Navbar.Collapse>
				</Container>
			</Navbar>
			<LinearProgress hidden={!loading} />
		</>
	);
}

export default Header;
