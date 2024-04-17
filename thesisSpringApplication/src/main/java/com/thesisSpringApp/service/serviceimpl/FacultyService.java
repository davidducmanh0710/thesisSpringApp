package com.thesisSpringApp.service.serviceimpl;

import java.util.List;

import com.thesisSpringApp.pojo.Faculty;

public interface FacultyService {
	List<Faculty> findAllFaculties();

	Faculty findFacultyById(int facultyId);
}
