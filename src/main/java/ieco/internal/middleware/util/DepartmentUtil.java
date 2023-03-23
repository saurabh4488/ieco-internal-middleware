package ieco.internal.middleware.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DepartmentUtil {
	protected DepartmentUtil() {
	}

	public static String getdepartment(String category) {

		String department = null;

		switch (category) {
		case "Registartion/Login/KYC":
		case "Registration/Login/KYC":
		case "My Portfolio":
		case "Advisory/Recommendations":
		case "Mutual Funds":

			department = "KIAL";
			break;

		case "RD":
		case "SA":
		case "TD":
		case "NPS":
			department = "K-Bank";
			break;

		case "Direct Equity":
			department = "K-Sec";
			break;
		default:
			department = "KIAL";
		}
		return department;
	}

	public static String getDeptID(String department) {
		String deptID = null;

		switch (department) {

		case "KIAL":
			deptID = "17634000000072420";
			break;
		case "K-Bank":
			deptID = "17634000000075083";
			break;
		case "K-Sec":
			deptID = "17634000000065754";
			break;
		default:
			log.info("Invalid Department !!");
		}

		return deptID;
	}
}
