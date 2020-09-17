package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import event_management.data.UserDAO;
import event_management.model.User;

public class UserDAOTest {
	

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUser("1234543432", "hxs7777", "Test123!", "Himan", "Dev", "test@gmail.com", "8787098789", "76013",
				"Summit Aveue", "513", "TX", "user", "Arlington");
		UserDAO.deleteUser(user);
		int res = UserDAO.saveUser(user);
		assertEquals(1, res);
		User dbuser=UserDAO.getUserByUserName(user).get(0);
		assertNotNull(dbuser.getUtaId());
		assertNotNull(dbuser.getCity());
		assertNotNull(dbuser.getEmail());
		assertNotNull(dbuser.getFirstName());
		assertNotNull(dbuser.getLastName());
		assertNotNull(dbuser.getPassword());
		assertNotNull(dbuser.getPhone());
		assertNotNull(dbuser.getRole());
		assertNotNull(dbuser.getState());
		assertNotNull(dbuser.getStreetName());
		assertNotNull(dbuser.getStreetNumber());
		assertNotNull(dbuser.getUsername());
		assertNotNull(dbuser.getZipCode());
		
		user.setEmail("testupdate@gmail.com");
		int updateTest = UserDAO.updateUser(user);
		assertEquals(1, updateTest);
		assertEquals("testupdate@gmail.com", UserDAO.getUser(user).getEmail());
		assertTrue(UserDAO.getUserByLastName(user).size()>0);
		assertEquals(1, UserDAO.getUserByUserName(user).size());
		assertTrue(UserDAO.getAllStaff().size()>0);
		
		boolean deleteTest = UserDAO.deleteUser(user);
		
		assertEquals(true, deleteTest);
		assertTrue(UserDAO.getAllStaff("-1").size()>0);
		assertEquals(0, UserDAO.getAssignedStaff("JUNIT").size());

	}

	@Before
	public void setUp() throws Exception {
	}

}
