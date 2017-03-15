/**
 * @ (#) SqlDml.java
 * Project     : SIMS
 * File        : SqlDml.java
 * Author      : Ninganna.c
 * Company     : 
 * Date Created: 15/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsservice.common;

/**
 * HQL queries for all the entities hibernate operations
 * 
 * @author Ninganna.c
 *
 */
public class SqlDml {
	// Span User entity queries
	public static final String LOGGED_IN_SPAN_USER = "FROM SpanUser u  where u.loginId = ? and u.password=?";

	// User entity queries
	public static final String LOGGED_IN_USER = "FROM User u  where u.loginId = ? and u.password=?";
	public static final String LOGGED_IN_USER_IDS = "SELECT u.loginId FROM User u";

	// Paper Entity queries
	public static final String PAPER_SELECT_QUERY = "FROM ManagePapers WHERE isActive='Y'";
	public static final String PAPER_DELETE_QUERY = "UPDATE ManagePapers SET isActive = ? WHERE managePaperId = ?";
	public static final String GET_PAPER_ID = "From ManagePapers m where m.name=?";
	public static final String PAPER_SELECT_BY_ID = "SELECT mp FROM ManagePapers mp WHERE isActive='Y' and managePaperId= ?";

	// Topic Entity queries
	public static final String TOPIC_SELECT_QUERY = "From ManageTopics WHERE isActive='Y'";
	public static final String GET_Topic_ID = "From ManageTopics t where t.name=?";
	public static final String TOPIC_DELETE_QUERY = "UPDATE ManageTopics set isActive = ? WHERE manageTopicId = ?";
	public static final String TOPIC_SELECT_LIST = "FROM ManageTopics WHERE section = ? and isActive = 'Y'";
	public static final String USER_TOPIC_SELECT_LIST = "FROM UserManagement um WHERE um.users.userId = ?";
	public static final String TOPIC_SELECT_BY_ID = "SELECT mt FROM ManageTopics mt WHERE isActive='Y' and manageTopicId= ?";

	// Association Entity queries
	public static final String ASSOCIATION_SELECT_QUERY = "From ManageAssociations WHERE isActive='Y'";
	public static final String Association_DELETE_QUERY = "UPDATE ManageAssociations SET isActive = ? WHERE manageAssociationId = ?";

	public static final String PAPER_BASED_ASSOCIATION_SELECT_QUERY = "";

	public static final String ASSOCIATION_DELETE_QUERY = "UPDATE ManageAssociations SET isActive = ? WHERE manageAssociationId = ?";
	
	public static final String SS_CHECK_QUERY = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y' and t.difficultyLevel = ? and t.verified='Y'";
	public static final String MS_CHECK_QUERY = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y' and t.difficultyLevel = ? and t.verified='Y'";
	public static final String DS_CHECK_QUERY = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y' and t.difficultyLevel = ? and t.verified='Y'";
	public static final String PSG_CHECK_QUERY = "SELECT t FROM TestPassages t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.isActive='Y' and t.verified='Y'";
	
	
	

	// Lookup data Entity queries
	public static final String SECTION_LIST = "SELECT section FROM LookUpData WHERE section!=null";
	public static final String QUESTIONTYPE_LIST = "SELECT questionType FROM LookUpData WHERE questionType!=null";
	public static final String DIFFICULTY_LIST = "SELECT difficultyLevel FROM LookUpData WHERE difficultyLevel!=null";
	public static final String LOOKUP_DELETE_QUERY = "DELETE LookUpData WHERE questionType = ? or section = ? or difficultyLevel = ?";

	// test questions Entity queries
	public static final String SSQ_SELECT_LIST = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y'";
	public static final String MSQ_SELECT_LIST = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y'";
	public static final String DSQ_SELECT_LIST = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y'";
	public static final String PAQ_SELECT_LIST = "SELECT t FROM TestPassages t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.isActive='Y'";
	public static final String QUSETION_UPDATE_QUERY = "UPDATE TestQuestions SET difficultyLevel = ? , question = ? , option1 = ? , option2 = ? , option3 = ? , option4 = ? , option5 = ? , option6 = ? , answer1 = ? , answer2 = ? , answer3 = ? , answer4 = ? , answer5 = ? , answer6 = ? , descriptiveAnswer = ? , modifiedBy = ? WHERE testQuestionId = ?";
	public static final String QUSETION_DELETE_QUERY = "UPDATE TestQuestions SET isActive = ? WHERE testQuestionId = ?";
	public static final String PASSAGE_DELETE_QUERY = "UPDATE TestPassages SET isActive = ? WHERE testPassageId = ?";
	public static final String PASSAGE_QUESTIONS_QUERY = "SELECT tq From TestQuestions tq left join tq.testPassages tp where tp.testPassageId = ? and tq.isActive = ?";

	// verification queries
	public static final String VERIFIED_QUESTION_STATUS_SET = "UPDATE TestQuestions SET verified='Y' WHERE testQuestionId=:id";
	public static final String VERIFIED_PASSAGE_STATUS_SET = "UPDATE TestPassages SET verified='Y' WHERE testPassageId=:id";
	public static final String SSQ_SELECT_VERIFIEDLIST = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y' and t.verified='N'";
	public static final String MSQ_SELECT_VERIFIEDLIST = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y' and t.verified='N'";
	public static final String DSQ_SELECT_VERIFIEDLIST = "SELECT t FROM TestQuestions t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.questionType = ? and t.testPassages=null and t.isActive='Y' and t.verified='N'";
	public static final String PAQ_SELECT_VERIFIEDLIST = "SELECT t FROM TestPassages t left join t.manageTopics tp WHERE tp.manageTopicId = ? and t.isActive='Y' and t.verified='N'";

	public static final String RANDOMIZE_QUESTION_ID_QUERY = "SELECT ma FROM ManageAssociations ma inner join  ma.managePapers mp "
			+ "WHERE mp.managePaperId=? and ma.isActive='Y'";
	// "SELECT
	// ma.manageTopics.manageTopicId,ma.noOfPassages,ma.noOfDescriptive,ma.noOfMultiSelect,"+
	// "ma.noOfSingleSelect,ma.difficultyLevel "+
	// "FROM ManageAssociations ma inner join ma.managePapers mp "+
	// "WHERE mp.managePaperId = 17 and ma.isActive = 'Y'";

	// excel upload queries
	public static final String QUESTION_SELECT_QUERY = "From ExcelQuestions t WHERE t.status != 'done' ";
	public static final String PASSAGE_SELECT_QUERY = "From ExcelPassages t WHERE t.status = null";
	public static final String CHANGE_XL_PASSAGE_STATUS_QUERY = "UPDATE ExcelPassages SET status= ? WHERE name = ?";
	public static final String TOPIC_SELECT_BY_NAME = "SELECT mt From ManageTopics mt WHERE mt.name = ?";
	public static final String XL_PASSAGE_QUESTION_SELECT_QUERY = "From ExcelQuestions eq WHERE eq.status = null and eq.xlPassageId = ?";
	public static final String XL_NON_PASSAGE_QUESTION_SELECT_QUERY = "From ExcelQuestions eq WHERE eq.status = null and eq.xlPassageId = null";
	public static final String CHANGE_XL_QUESTION__STATUS_QUERY = "UPDATE ExcelQuestions SET status= ? WHERE testQuestionId = ?";
	public static final String DELETE_XL_TEST_QUESTIONS = "truncate table xl_test_questions";
	public static final String DELETE_XL_TEST_PASSAGES = "truncate table xl_test_passages";
	
	// user management queries
	public static final String USERS_SELECT_QUERY_SUPER = "From User u WHERE u.userType != 'SuperAdmin'";
	public static final String USERS_SELECT_QUERY = "From User u WHERE u.addedBy = ?";
	public static final String USER_MANAGEMENT_DELETE_QUERY = "DELETE UserManagement um WHERE um.users.userId = ?";
	public static final String USER_EMAIL_EXIST_QUERY = "FROM User WHERE email = ?";
	public static final String USER_DELETE_QUERY = "DELETE User u WHERE u.userId = ?";

	// report queries
	public static final String CANDIDATE_RESULT_QUERY = "FROM CandidateResult WHERE managePapers.managePaperId = ? AND status='Evaluated'";
	public static final String CANDIDATE_FILTER_QUERY = "FROM CandidateResult cr WHERE cr.managePapers.managePaperId = ? AND cr.score >= ? AND cr.status='Evaluated'";
	public static final String TOPIC_NAMES_QUERY = " from ManageAssociations ma where ma.managePapers.managePaperId = ? and isActive = 'Y' and ma.manageTopics.isActive = 'Y'";
	public static final String CANDIDATE_TOPIC_WISE_RESULT = "SELECT SUM(cq.marks) FROM CandidateQuestion cq WHERE cq.testQuestions.manageTopics.manageTopicId= ? and cq.candidateInformation.candidateId= ? ";
	
	// Evaluation dao quieries
	public static final String SELECT_CANDIDATE_ON_PAPER_ID = "from CandidateResult where managePapers.managePaperId = ? AND status IN('Attempted','Evaluated')";
	public static final String SELECT_CANDIDATE_QUESTIONS = "from CandidateQuestion where candidateInformation.candidateId = ?";
	public static final String UPDATE_DESCRIPTIVE_MARKS = "UPDATE CandidateQuestion cq SET cq.marks= ? WHERE cq.testQuestions.testQuestionId = ? and cq.candidateInformation.candidateId = ?";
	public static final String SELECT_CANDIDATE_MARKS = "SELECT SUM(marks) FROM CandidateQuestion cq where cq.candidateInformation.candidateId = ?";
	public static final String UPDATE_CANDIDATE_SCORE = "UPDATE CandidateResult cr SET cr.score= ?, cr.status='Attempted' WHERE cr.candidateInformation.candidateId = ?";
	public static final String UPDATE_CANDIDATE_SCORE_POST_EVALUATION = "UPDATE CandidateResult ca SET ca.score= ?, ca.status='Evaluated' WHERE ca.candidateInformation.candidateId = ?";

	// web services quries
	public static final String GET_CANDIDATE_ID = "SELECT c.candidateId FROM CandidateIds c";
	public static final String DELETE_CANDIDATE_ID = "DELETE FROM CandidateIds c WHERE c.candidateId = ?";
	public static final String GET_CANDIDATE_TIME = "From CandidateTestTime ctt WHERE ctt.candidateId = ?";
	public static final String GET_CANDIDATE_NAME = "From CandidateInformation ci WHERE ci.candidateId = ?";
	public static final String SELECT_CANDIDATE_TESTQUESTIONS = "select c.testQuestions.testQuestionId as testQuestionId ,c.testQuestions.questionType as questionType,c.testQuestions.question as question,"
			+ "c.testQuestions.option1 as option1,c.testQuestions.option2 as option2,c.testQuestions.option3 as option3,"
			+ "c.testQuestions.option4 as option4,c.testQuestions.option5 as option5,c.testQuestions.option6 as option6"
			+ " from CandidateQuestion c where c.candidateInformation.candidateId = ?";
	public static final String SELECT_CANDIDATE_QUESTION = "FROM CandidateQuestion cq WHERE cq.candidateInformation.candidateId = ?";
	public static final String GET_MAXIMUM_TIME = "From CandidateResult cr where cr.candidateInformation.candidateId= ?";

	public static final String UPDATE_CANDIDATE_QUESTION = "UPDATE CandidateQuestion cq SET cq.answer1= ?, cq.answer2=?, cq.answer3=?,cq.answer4=?,cq.answer5=?,cq.answer6=?,cq.descriptiveAnswer=?,cq.status=?,cq.marks=?  WHERE cq.candidateInformation.candidateId = ? and cq.testQuestions.testQuestionId=?";
	public static final String PAPERWS_SELECT_QUERY = "SELECT p.managePaperId,p.name, p.maxTime, p.description From ManagePapers p WHERE p.isActive= 'Y' ";

	// Archive
	public static final String SAFE_MODE_QUERY = "SET SQL_SAFE_UPDATES = 0";
	public static final String ARCHIVE_QUERY = "insert candidate_information_archive select * from candidate_information";
	public static final String ARCHIVE_RESULT_QUERY = "insert candidate_result_archive select * from candidate_result";
	public static final String DELETE_RESULT_QUERY = "truncate table candidate_result";
	public static final String DELETE_CANDIDATE_QUESTION_QUERY = "truncate table candidate_question";
	public static final String DELETE_QUERY = "delete from candidate_information";

	// Cancel Candidate id's and Question
	public static final String DELETE_CANDIDATE_ID_S = "truncate table candidate_ids";
	public static final String DELETE_CANDIDATE_RESULT = "truncate table candidate_result";
	public static final String DELETE_CANDIDATE_QUESTION_S = "truncate table candidate_question";
	public static final String DELETE_CANDIDATE_INFORMATION_QUERY = "delete from candidate_information";

	private SqlDml() {
	}
}
