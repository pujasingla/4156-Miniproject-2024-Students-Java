package dev.coms4156.project.individualproject;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
public class RouteControllerTests {

  @Autowired
  private MockMvc mockMvc;

  private ConfigurableApplicationContext context;

  @BeforeEach
  public void setUp() {
     context = SpringApplication.run(IndividualProjectApplication.class, new String[]{"setup"});
  }

  @Test
  public void testRetrieveDepartmentIsFound() throws Exception {
    mockMvc.perform(get("/retrieveDept").param("deptCode", "PHYS"))
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("PHYS")));
  }

  @Test
  public void testRetrieveDepartmentNotFound() throws Exception {
    mockMvc.perform(get("/retrieveDept").param("deptCode", "Math"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void testRetrieveCourseIsFound() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "COMS")
            .param("courseCode","3251"))
            .andExpect(status().isOk());
  }
  @Test
  public void testRetrieveCourseIsNotFound() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "COMS")
            .param("courseCode","1000"))
            .andExpect(status().isNotFound());
  }
  @Test
  public void testRetrieveCourseWhenDeptIsNotFound() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "MATH")
            .param("courseCode","1000"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testIsCourseFullWhenCourseIsFull() throws Exception {
    mockMvc.perform(get("/isCourseFull")
            .param("deptCode", "IEOR")
            .param("courseCode","2500"))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
  }

  @Test
  public void testIsCourseFullWhenCourseIsNotFull() throws Exception {
    mockMvc.perform(get("/isCourseFull")
            .param("deptCode", "ECON")
            .param("courseCode","1105"))
            .andExpect(status().isOk())
            .andExpect(content().string("false"));
  }

  @Test
  public void testIsCourseFullWhenDeptIsNotFound() throws Exception {
    mockMvc.perform(get("/isCourseFull")
            .param("deptCode", "MATH")
            .param("courseCode","1000"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testGetMajorCountFromDept() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
            .param("deptCode", "IEOR"))
            .andExpect(status().isOk())
            .andExpect(content().string("There are: 67 majors in the department"));
  }

  @Test
  public void testGetMajorCountFromDeptWhenDeptIsNotFound() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
            .param("deptCode", "MATH"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testIdentifyDeptChairWhenChairIsFound() throws Exception {
    mockMvc.perform(get("/idDeptChair")
            .param("deptCode", "IEOR"))
            .andExpect(status().isOk())
            .andExpect(content().string("Jay Sethuraman is the department chair."));
  }

  @Test
  public void testIdentifyDeptChairWhenDeptIsNotFound() throws Exception {
    mockMvc.perform(get("/idDeptChair")
            .param("deptCode", "MATH"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testFindCourseLocation() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
            .param("deptCode", "CHEM")
            .param("courseCode","3080"))
            .andExpect(status().isOk())
            .andExpect(content().string("209 HAV is where the course is located."));
  }

  @Test
  public void testFindCourseLocationWhenDeptIsNotFound() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
            .param("deptCode", "MATH")
            .param("courseCode","3080"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testFindCourseLocationWhenCourseIsNotFound() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
            .param("deptCode", "COMS")
            .param("courseCode","2010"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testFindCourseInstructor() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
            .param("deptCode", "CHEM")
            .param("courseCode","3080"))
            .andExpect(status().isOk())
            .andExpect(content().string("Milan Delor is the instructor for the course."));
  }

  @Test
  public void testFindCourseInstructorWhenDeptIsNotFound() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
            .param("deptCode", "MATH")
            .param("courseCode","3080"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testFindCourseInstructorWhenCourseIsNotFound() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
            .param("deptCode", "COMS")
            .param("courseCode","2010"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testFindCourseTime() throws Exception {
    mockMvc.perform(get("/findCourseTime")
            .param("deptCode", "PHYS")
            .param("courseCode","2802"))
            .andExpect(status().isOk())
            .andExpect(content().string("The course meets at: 10:10-12:00 some time"));
  }

  @Test
  public void testFindCourseTimeWhenDeptIsNotFound() throws Exception {
    mockMvc.perform(get("/findCourseTime")
            .param("deptCode", "MATH")
            .param("courseCode","3080"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testFindCourseTimeWhenCourseIsNotFound() throws Exception {
    mockMvc.perform(get("/findCourseTime")
            .param("deptCode", "COMS")
            .param("courseCode","2010"))
            .andExpect(status().isNotFound());
  }

  



  @AfterEach
  public void close() {
    context.close();
  }
}
