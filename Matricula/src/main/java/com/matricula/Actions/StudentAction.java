package com.matricula.Actions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.matricula.base.domain.BeanServices;
import com.matricula.domain.Course;
import com.matricula.domain.Student;
import com.matricula.domain.UserEntity;
import com.matricula.domain.WorkingDay;
import com.matricula.row.AuxiliaryDataDetail;
import com.matricula.row.StudentRow;
import com.matricula.utilities.impl.ParameterUtilities;
import com.matricula.utilities.impl.constants.Status;

import javassist.NotFoundException;

/*
 * BEAN que manipulara las acciones que se hagan en estudiante
 */
@Named(value = "studentAction")
@ViewScoped
public class StudentAction extends BeanServices {

	private static final long serialVersionUID = 4207884919428813428L;
	private Student editStudent;
	private Course programaEntity;

	// Listas

	private List<StudentRow> studentList;
	private StudentRow selectedStudentRow;

	private List<WorkingDay> jornadaChoicelist;
	private List<AuxiliaryDataDetail> statusRb;
	private List<Course> programList;

	private Long statusId;

	private String programName;

	// FILTER
	private String studentNameFilter;
	private String docNumberFilter;
	private String programNameFilter;
	private String dayFilter;

	/*
	 * =====================================================
	 * 
	 * METODOS PUBLICOS
	 * 
	 * =====================================================
	 */
	public StudentAction() {
	}

	public void onStart() throws Exception {
		initializeFields();
		initialize();
		super.setNotAction();

	}

	/*
	 * =====================================================
	 * 
	 * EVENTS
	 * 
	 * =====================================================
	 */
	public void saveStudent(AjaxBehaviorEvent event) throws Exception {
		boolean isValid = validate();

		if (isValid) {
			complete();
			if (super.isCreating()) {
				super.serviceDao.save(editStudent);

			} else if (super.isEditing()) {
				super.serviceDao.update(editStudent);
			}
		}
		editStudent = new Student();
		loadStudents();
	}

	public void configureNewStudent(AjaxBehaviorEvent event) {
		super.setCreating();
		editStudent = new Student();
		clean();
	}

	public void editStudents(AjaxBehaviorEvent event) {
		super.setEditing();
		selectedStudentRow = (StudentRow) event.getComponent().getAttributes().get("studentRow");
		editStudent = selectedStudentRow.getStudent();
		if (editStudent.getStatus() != null) {
			statusId = editStudent.getStatus().getId();
		}
	}

	public void deleteResponse(AjaxBehaviorEvent event) throws Exception {
		if (selectedStudentRow != null) {
			super.serviceDao.delete(Student.class, selectedStudentRow.getStudent().getId());
			loadStudents();
		}
	}

	public void refreshList(AjaxBehaviorEvent event) throws Exception {
		loadStudents();
	}

	public void loadPrograms(AjaxBehaviorEvent event) throws FinderException {
		loadPrograms();
	}

	public void cleanFilter(AjaxBehaviorEvent event) {
		studentNameFilter = StringUtils.EMPTY;
		docNumberFilter = StringUtils.EMPTY;
		programNameFilter = StringUtils.EMPTY;
		dayFilter = StringUtils.EMPTY;
	}

	/*
	 * =====================================================
	 * 
	 * PRIVATE METHODS
	 * 
	 * =====================================================
	 */

	private void initialize() throws Exception {
		loadStudents();
		loadJornadaChoicelist();
		loadStatusRb();
	}

	private void initializeFields() throws Exception {
		editStudent = new Student();
		programName = StringUtils.EMPTY;

		// Establecer el estado en activo por defecto
		statusId = super.utilitiesDbService.findDetalleDatoAuxiliarByShortName(Status.ACTIVE).getId();
	}

	private void complete() throws FinderException, NotFoundException {
		editStudent.setStatus(super.serviceDao.findById(AuxiliaryDataDetail.class, statusId));
		editStudent.setUser(super.serviceDao.findById(UserEntity.class, 1l));

	}

	private boolean validate() throws FinderException, NotFoundException {

		if (statusId == null) {
			return false;
		}
		return true;
	}

	private void loadStatusRb() throws Exception {
		statusRb = super.utilitiesDbService.findDetalleDatoAuxiliarByGroup("STATUS");

	}

	private List<StudentRow> loadStudents() throws Exception {
		studentList = new ArrayList<StudentRow>();
		List<Student> estudianteList = new ArrayList<Student>();
		StringBuilder filter = new StringBuilder(createFilter());

		if (filter.length() > 0) {
			estudianteList = super.serviceDao.findAll(Student.class, filter.toString(), super.getParameters());
		} else {
			estudianteList = super.serviceDao.findAll(Student.class);
		}

		for (Student estudianteRow : estudianteList) {
			studentList.add(new StudentRow(estudianteRow, estudianteRow.getId()));
		}
		return studentList;
	}

	private List<WorkingDay> loadJornadaChoicelist() throws FinderException {
		return super.serviceDao.findAll(WorkingDay.class);
	}

	private void clean() {
		programName = StringUtils.EMPTY;

	}

	private void loadPrograms() throws FinderException {
		programList = super.serviceDao.findAll(Course.class);
	}

	private StringBuilder createFilter() {
		super.setParameters(new ArrayList<ParameterUtilities<?>>());
		StringBuilder filter = new StringBuilder(StringUtils.EMPTY);

		trimFields();

		if (StringUtils.isNotEmpty(studentNameFilter)) {
			filter.append(" upper(strNombreEst) like upper(:studentNameFilter)");
			super.getParameters()
					.add(new ParameterUtilities<String>("studentNameFilter", "%" + studentNameFilter + "%"));
		}
		if (StringUtils.isNotEmpty(docNumberFilter)) {
			if (filter.length() > 0) {
				filter.append(" and ");
			}
			filter.append(" upper(strNroDocEst) like upper(:docNumberFilter)");

			super.getParameters().add(new ParameterUtilities<String>("docNumberFilter", "%" + docNumberFilter + "%"));
		}

		if (StringUtils.isNotEmpty(programNameFilter)) {
			if (filter.length() > 0) {
				filter.append(" and ");
			}
			filter.append(" upper(programa.strNombrePro) like upper(:programNameFilter)");
			super.getParameters()
					.add(new ParameterUtilities<String>("programNameFilter", "%" + programNameFilter + "%"));

		}
		if (StringUtils.isNotEmpty(dayFilter)) {
			if (filter.length() > 0) {
				filter.append(" and ");
			}
			filter.append(" upper(jornada.strDescripcionJOR) like upper(:dayFilter)");
			super.getParameters().add(new ParameterUtilities<String>("dayFilter", "%" + dayFilter + "%"));
		}
		return filter;
	}

	/*
	 * =====================================================
	 * 
	 * GETTERS AND SETTERS
	 * 
	 * =====================================================
	 */

	public Student getEditStudent() {
		return editStudent;
	}

	public void setEditStudent(Student editStudent) {
		clean();
		if (editStudent != null) {

			if (editStudent.getStatus() != null) {
				statusId = editStudent.getStatus().getId();
			}
		}
		this.editStudent = editStudent;
	}

	public void setJornadaChoicelist(List<WorkingDay> jornadaChoicelist) {
		this.jornadaChoicelist = jornadaChoicelist;
	}

	public List<WorkingDay> getJornadaChoicelist() {
		return jornadaChoicelist;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public List<AuxiliaryDataDetail> getStatusRb() {
		return statusRb;
	}

	public void setStatusRb(List<AuxiliaryDataDetail> statusRb) {
		this.statusRb = statusRb;
	}

	public List<Course> getProgramList() {
		return programList;
	}

	public void setProgramList(List<Course> programList) {
		this.programList = programList;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Course getProgramaEntity() {
		return programaEntity;
	}

	public void setProgramaEntity(Course programaEntity) {
		if (programaEntity != null) {
			programName = programaEntity.getName();
		}
		this.programaEntity = programaEntity;
	}

	public String getStudentNameFilter() {
		return studentNameFilter;
	}

	public void setStudentNameFilter(String studentNameFilter) {
		this.studentNameFilter = studentNameFilter;
	}

	public String getDocNumberFilter() {
		return docNumberFilter;
	}

	public void setDocNumberFilter(String docNumberFilter) {
		this.docNumberFilter = docNumberFilter;
	}

	public String getProgramNameFilter() {
		return programNameFilter;
	}

	public void setProgramNameFilter(String programNameFilter) {
		this.programNameFilter = programNameFilter;
	}

	public String getDayFilter() {
		return dayFilter;
	}

	public void setDayFilter(String dayFilter) {
		this.dayFilter = dayFilter;
	}

	private void trimFields() {
		studentNameFilter = StringUtils.trimToEmpty(studentNameFilter);
		dayFilter = StringUtils.trimToEmpty(dayFilter);
		programName = StringUtils.trimToEmpty(programName);
		docNumberFilter = StringUtils.trimToEmpty(docNumberFilter);
	}

	public StudentRow getSelectedStudentRow() {
		return selectedStudentRow;
	}

	public void setSelectedStudentRow(StudentRow selectedStudentRow) {
		this.selectedStudentRow = selectedStudentRow;
	}

	public List<StudentRow> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentRow> studentList) {
		this.studentList = studentList;
	}

}
