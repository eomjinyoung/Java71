package com.model2.mvc.service.user.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;



@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class UserServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Test
	public void testAddUser() throws Exception {
		
		User user = new User();
		user.setUserId("testUserId");
		user.setUserName("testUserName");
		user.setPassword("testPasswd");
		user.setSsn("1111112222222");
		user.setPhone("111-2222-3333");
		user.setAddr("sdf");
		user.setEmail("test@test.com");
		
		userService.addUser(user);
		
		user = userService.getUser("testUserId");

		//System.out.println(user);
		
		Assert.assertEquals("testUserId", user.getUserId());
		Assert.assertEquals("testUserName", user.getUserName());
		Assert.assertEquals("testPasswd", user.getPassword());
		Assert.assertEquals("111-2222-3333", user.getPhone());
		Assert.assertEquals("sdf", user.getAddr());
		Assert.assertEquals("test@test.com", user.getEmail());
	}
	
	//@Test
	public void testGetUser() throws Exception {
		
		User user = new User();
//			user.setUserId("testUserId");
//			user.setUserName("testUserName");
//			user.setPassword("testPasswd");
//			user.setSsn("1111112222222");
//			user.setPhone("111-2222-3333");
//			user.setEmail("test@test.com");
		
		user = userService.getUser("testUserId");

		//System.out.println(user);
		
		Assert.assertEquals("testUserId", user.getUserId());
		Assert.assertEquals("testUserName", user.getUserName());
		Assert.assertEquals("testPasswd", user.getPassword());
		Assert.assertEquals("111-2222-3333", user.getPhone());
		Assert.assertEquals("haha", user.getAddr());
		Assert.assertEquals("test@test.com", user.getEmail());

		Assert.assertNotNull(userService.getUser("user02"));
		Assert.assertNotNull(userService.getUser("user05"));
	}
	
	//@Test
	 public void testUpdateUser() throws Exception{
		 
		User user = userService.getUser("testUserId");
		Assert.assertNotNull(user);
		
		Assert.assertEquals("testUserName", user.getUserName());
		Assert.assertEquals("111-2222-3333", user.getPhone());
		Assert.assertEquals("haha", user.getAddr());
		Assert.assertEquals("test@test.com", user.getEmail());

		user.setUserName("change");
		user.setPhone("777-7777-7777");
		user.setAddr("change");
		user.setEmail("change@change.com");
		
		userService.updateUser(user);
		
		user = userService.getUser("testUserId");
		Assert.assertNotNull(user);
		
		//System.out.println(user);
			
		Assert.assertEquals("change", user.getUserName());
		Assert.assertEquals("777-7777-7777", user.getPhone());
		Assert.assertEquals("change", user.getAddr());
		Assert.assertEquals("change@change.com", user.getEmail());
	 }
	 
	//@Test
	public void testCheckDuplication() throws Exception{

//			User user = new User();
//			user.setUserId("testUserId");
//			user.setUserName("testUserName");
//			user.setPassword("testPasswd");
//			user.setSsn("1111112222222");
//			user.setPhone("111-2222-3333");
//			user.setEmail("test@test.com");
//			
//			userService.addUser(user);
		
		//System.out.println(userService.checkDuplication("testUserId"));
		//System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
	 	
		Assert.assertFalse( userService.checkDuplication("testUserId") );
	 	Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
		 	
	}
	
	 //@Test
	 public void testGetUserListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = userService.getUserList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetUserListByUserId() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("admin");
	 	Map<String,Object> map = userService.getUserList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetUserListByUserName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("SCOTT");
	 	Map<String,Object> map = userService.getUserList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = userService.getUserList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}