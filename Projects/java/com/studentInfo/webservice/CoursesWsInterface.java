/**
 * 
 */
package com.studentInfo.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studentInfo.common.SimsException;
import com.studentInfo.model.StudentInfoModel;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author sanjay.kumar
 *
 */
@Path("/courses")
@Api(value = "/courses", description = "data of education courses")
public interface CoursesWsInterface {

	/**
	 * To add course
	 * 
	 * @param course
	 * @return
	 */
	@POST
	@Path(value = "/course")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "To add the course", response = Boolean.class)
	public @ResponseBody Response addCourse(@RequestBody StudentInfoModel course) throws SimsException;

	/**
	 * To update course
	 * 
	 * @param course
	 * @return
	 */
	@PUT
	@Path(value = "/course")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "To add the course", response = Boolean.class)
	public @ResponseBody Response updateCourse(@RequestBody StudentInfoModel course) throws SimsException;

	/**
	 * To delete course
	 * 
	 * @param course
	 * @return
	 */
	@PUT
	@Path(value = "/course/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "To delete the course", response = Boolean.class)
	public @ResponseBody Response deleteCourse(@RequestBody StudentInfoModel course) throws SimsException;

	/**
	 * To get course
	 * 
	 * @param course
	 * @return
	 */
	@GET
	@Path(value = "/studentIndi")
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "To get the studentInfo", response = Boolean.class)
	public @ResponseBody Response getStudentInfoIndi(@QueryParam("studentId") String studentId) throws SimsException;

	/**
	 * To get All courses
	 * 
	 * @param course
	 * @return
	 */
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@ApiOperation(value = "To get the course", response = Boolean.class)
	public @ResponseBody Response getAllCourse() throws SimsException;
}
