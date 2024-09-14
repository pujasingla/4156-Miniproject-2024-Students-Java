package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test class for the Course functionality in the application.
 *
 * <p>This class uses {@link SpringBootTest} to load the full application context
 * for integration testing, and {@link ContextConfiguration} to specify necessary
 * test-specific configurations.</p>
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
    testCourse1 = new Course("Donald Ferguson", "413 CSE", "1:10-3:40", 600);
  }


  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void testEnrollStudentSuccess() {
    boolean result = testCourse1.enrollStudent();
    assertTrue(result, "Enrollment should succeed when there is enough capacity.");
  }

  @Test
  public void testEnrollStudentFailure() {
    for (int i = 0; i < 250; i++) {
      testCourse.enrollStudent();
    }
    boolean result = testCourse.enrollStudent();
    assertFalse(result, "Enrollment should fail when the course is full.");
  }

  @Test
  public void testDropStudentSuccess() {
    testCourse.enrollStudent();
    boolean result = testCourse.dropStudent();
    assertTrue(result, "Dropping a student should succeed when there are students enrolled.");
  }

  @Test
  public void testDropStudentFailureWhenEmpty() {
    boolean result = testCourse1.dropStudent();
    assertFalse(result, "Dropping a student should fail when there are no students enrolled.");
  }

  @Test
  public void testGetCourseLocation() {
    String expectedLocation = "413 CSE";
    String actualLocation = testCourse1.getCourseLocation();

    assertEquals(expectedLocation, actualLocation);
  }

  @Test
  public void testGetInstructorName() {
    String expectedGetInstructorName = "Donald Ferguson";
    String actualGetInstructorName = testCourse1.getInstructorName();

    assertEquals(expectedGetInstructorName, actualGetInstructorName);
  }

  @Test
  public void testGetCourseTimeSlot() {
    String expectedGetCourseTimeSlot = "1:10-3:40";
    String actualCourseTimeSlot = testCourse1.getCourseTimeSlot();

    assertEquals(expectedGetCourseTimeSlot, actualCourseTimeSlot);
  }

  @Test
  public void testReassignInstructor() {
    String newInstructorName = "Luis Gravano";
    testCourse1.reassignInstructor(newInstructorName);
    assertEquals(newInstructorName, testCourse1.getInstructorName());
  }

  @Test
  public void testReassignLocation() {
    String newExpectedLocation = "HAV 309";
    testCourse1.reassignLocation(newExpectedLocation);
    assertEquals(newExpectedLocation, testCourse1.getCourseLocation());
  }

  @Test
  public void testReassignTime() {
    String newExpectedTime = "1:10-3:40";
    testCourse1.reassignTime(newExpectedTime);
    assertEquals(newExpectedTime, testCourse1.getCourseTimeSlot());
  }

  @Test
  public void testIsCourseFullWhenEnrolledCountEqualsCapacity() {
    for (int i = 0; i < 250; i++) {
      testCourse.enrollStudent();
    }
    assertTrue(testCourse.isCourseFull());
  }

  @Test
  public void testCourseFullWhenEnrolledCountExceedsCapacity() {
    for (int i = 0; i < 251; i++) {
      testCourse.enrollStudent();
    }
    assertTrue(testCourse.isCourseFull());
  }


  /** The test course instance used for testing. */
  public static Course testCourse;
  public static Course testCourse1;
}

