export const UserReducer = (current, action) => {
	switch (action.type) {
		case "login":
			return action.payload;
		case "logout":
			return null;
		default:
			return current;
	}
};

export const LoadingReducer = (current, action) => {
	switch (action.type) {
		case "loading":
			return true;
		case "unloading":
			return false;
		default:
			return current;
	}
};
