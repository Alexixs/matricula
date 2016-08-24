/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matricula.Actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.FinderException;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.matricula.base.domain.BeanServices;
import com.matricula.domain.Course;
import com.matricula.domain.Period;
import com.matricula.domain.Registration;
import com.matricula.domain.Student;
import com.matricula.domain.WorkingDay;
import com.matricula.row.ProgramRow;
import com.matricula.row.RegistrationRow;
import com.matricula.row.StudentRow;
import com.matricula.utilities.impl.Action;

import javassist.NotFoundException;

/**
 * @version 2
 * @author Alexis
 */
@Named(value = "registrationAction")
@ViewScoped
public class RegistrationAction extends BeanServices {

	private static final long serialVersionUID = -3033414009182792786L;
	private Registration editMatricula;
	private Student editStudent;
	private Course editProgram;

	private List<Period> periodList;
	private List<WorkingDay> schoolDayChoices;

	// Listas
	private List<ProgramRow> programList;
	private ProgramRow selectedProgramRow;

	private List<StudentRow> studentList;
	private StudentRow selectedStudentRow;

	private List<RegistrationRow> registrationList;
	private RegistrationRow selectedRegistrationRow;

	private Long periodId;
	private Long schoolDayId;

	/*
	 * =====================================================================
	 * 
	 * PUBLIC METHODS
	 * 
	 * =====================================================================
	 */
	public RegistrationAction() {

	}

	public void onStart() throws FinderException {
		configureList();
	}

	public void saveRegistration(AjaxBehaviorEvent event)
			throws FinderException, NotFoundException {
		boolean isValid = validate();

		if (isValid) {
			completeRecord();
			if (StringUtils.equals(super.getAction(), Action.CREATE.getValue())) {
				super.serviceDao.save(editMatricula);
			} else {
				super.serviceDao.update(editMatricula);
			}
			loadRegistrationList();
		}
	}

	public void configureNewRegistration(AjaxBehaviorEvent event) {
		super.setAction(Action.CREATE.getValue());
		editMatricula = new Registration();
		editProgram = new Course();
		editStudent = new Student();
		periodId = null;
		schoolDayId = null;

	}

	public void editRegistration(AjaxBehaviorEvent event)
			throws FinderException {
		super.setAction(Action.EDIT.getValue());
		editMatricula = selectedRegistrationRow.getMatriculaEntity();
		editProgram = editMatricula.getCourse();
		editStudent = editMatricula.getStudent();
		periodId = editMatricula.getPeriod().getId();
		schoolDayId = editMatricula.getWorkingDay().getId();
	}

	public void refreshList(AjaxBehaviorEvent event) throws FinderException {
		loadRegistrationList();

	}

	public void loadAvailableStudents(AjaxBehaviorEvent event)
			throws FinderException {
		studentList = new ArrayList<StudentRow>();

		List<Student> estudianteList = super.serviceDao.findAll(Student.class);

		for (Student estudianteRow : estudianteList) {
			studentList
					.add(new StudentRow(estudianteRow, estudianteRow.getId()));
		}
	}

	public void loadProgramList(AjaxBehaviorEvent event) throws FinderException {
		programList = new ArrayList<ProgramRow>();
		List<Course> programaList = super.serviceDao.findAll(Course.class);

		for (Course programaRow : programaList) {
			programList.add(new ProgramRow(programaRow));
		}
	}

	public void deleteResponse(AjaxBehaviorEvent event) throws Exception {
		if (selectedRegistrationRow != null) {
			super.serviceDao.delete(Registration.class, selectedRegistrationRow
					.getMatriculaEntity().getId());
			loadRegistrationList();
		}
	}

	/*
	 * =====================================================================
	 * 
	 * PRIVATE METHODS
	 * 
	 * =====================================================================
	 */

	private void completeRecord() throws FinderException, NotFoundException {
		editMatricula.setRegistrationDate(Calendar.getInstance().getTime());

		editMatricula.setStudent(super.serviceDao.findById(Student.class,
				editStudent.getId()));

		editMatricula.setPeriod(super.serviceDao.findById(Period.class,
				periodId));

		editMatricula.setCourse(super.serviceDao.findById(Course.class,
				editProgram.getId()));

		editMatricula.setWorkingDay(super.serviceDao.findById(WorkingDay.class,
				schoolDayId));
	}

	private void configureList() throws FinderException {
		loadRegistrationList();
		loadPeriodList();
		loadSchoolDayChoices();
	}

	private void loadRegistrationList() throws FinderException {
		registrationList = new ArrayList<RegistrationRow>();

		List<Registration> matriculaList = super.serviceDao
				.findAll(Registration.class);

		for (Registration matriculaRow : matriculaList) {
			registrationList.add(new RegistrationRow(matriculaRow, matriculaRow
					.getId()));
		}

	}

	private void loadSchoolDayChoices() throws FinderException {
		schoolDayChoices = super.serviceDao.findAll(WorkingDay.class);

	}

	private void loadPeriodList() throws FinderException {
		periodList = super.serviceDao.findAll(Period.class);
	}

	private boolean validate() {

		if (editStudent == null) {
			return false;
		}
		if (periodId == null) {
			return false;
		}
		if (editProgram == null) {
			return false;
		}
		if (schoolDayId == null) {
			return false;
		}
		if (StringUtils.trimToEmpty(editMatricula.getComment()).length() == 0) {
			return false;
		}
		return true;
	}

	/*
	 * =====================================================================
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * =====================================================================
	 */

	public Registration getEditMatricula() {
		return editMatricula;
	}

	public void setEditMatricula(Registration editMatricula) {
		this.editMatricula = editMatricula;
	}

	public List<Period> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<Period> periodList) {
		this.periodList = periodList;
	}

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public Student getEditStudent() {
		return editStudent;
	}

	public void setEditStudent(Student editStudent) {
		this.editStudent = editStudent;
	}

	public Course getEditProgram() {
		return editProgram;
	}

	public void setEditProgram(Course editProgram) {
		this.editProgram = editProgram;
	}

	public List<ProgramRow> getProgramList() {
		return programList;
	}

	public void setProgramList(List<ProgramRow> programList) {
		this.programList = programList;
	}

	public Long getSchoolDayId() {
		return schoolDayId;
	}

	public void setSchoolDayId(Long schoolDayId) {
		this.schoolDayId = schoolDayId;
	}

	public List<WorkingDay> getSchoolDayChoices() {
		return schoolDayChoices;
	}

	public void setSchoolDayChoices(List<WorkingDay> schoolDayChoices) {
		this.schoolDayChoices = schoolDayChoices;
	}

	public List<RegistrationRow> getRegistrationList() {
		return registrationList;
	}

	public void setRegistrationList(List<RegistrationRow> registrationList) {
		this.registrationList = registrationList;
	}

	public List<StudentRow> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentRow> studentList) {
		this.studentList = studentList;
	}

	public ProgramRow getSelectedProgramRow() {
		return selectedProgramRow;
	}

	public void setSelectedProgramRow(ProgramRow selectedProgramRow) {
		this.selectedProgramRow = selectedProgramRow;
		this.editProgram = selectedProgramRow.getProgramaEntity();
	}

	public StudentRow getSelectedStudentRow() {
		return selectedStudentRow;
	}

	public void setSelectedStudentRow(StudentRow selectedStudentRow) {
		this.selectedStudentRow = selectedStudentRow;
		this.editStudent = selectedStudentRow.getStudent();
	}

	public RegistrationRow getSelectedRegistrationRow() {
		return selectedRegistrationRow;
	}

	public void setSelectedRegistrationRow(
			RegistrationRow selectedRegistrationRow) {
		this.selectedRegistrationRow = selectedRegistrationRow;
	}

}
