package pl.barksville.barksville;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DataJpaTest
class BarksvilleApplicationTests {
/*
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private Friend

	@BeforeEach
	public void prepareFriendlyUser(){
		user = new User();
	}



	@Test
	public void shouldSaveValidFriend(){
		//Given some valid friend
		Friend
	}




	@Test
	void contextLoads() {
	}


 */
}
/*
przykład testu JPA

package honestit.akwilina.projects.promises.domain.repositories;
		import honestit.akwilina.projects.promises.domain.entities.Friend;
		import honestit.akwilina.projects.promises.domain.entities.User;
		import org.junit.jupiter.api.BeforeEach;
		import org.junit.jupiter.api.Test;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
		import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
		import org.springframework.dao.DataIntegrityViolationException;
		import static org.junit.jupiter.api.Assertions.assertEquals;
		import static org.junit.jupiter.api.Assertions.assertNotNull;
		import static org.junit.jupiter.api.Assertions.assertThrows;
@DataJpaTest
class FriendRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private FriendRepository friendRepository;
	private User user;
	@BeforeEach
	public void prepareFriendlyUser() {
		user = new User();
		user.setUsername("friendlyUser");
		user.setEmail("email");
		user.setActive(true);
		user.setPassword("pass");
		entityManager.persistAndFlush(user);
	}
	@Test
	public void shouldSaveValidFriend() {
		// Given some valid friend
		Friend friend = new Friend();
		friend.setName("friend");
		friend.setOwner(user);
		// When save friend
		friendRepository.save(friend);
		// Then friend has id
		assertNotNull(friend.getId());
	}
	@Test
	public void shouldThrowExceptionWhenSavingInvalidFriend() {
		// Given some invalid friend
		Friend friend = new Friend();
		friend.setOwner(user);
		// When save invalid friend
		// Then throw DataIntegrationConstraintException
		assertThrows(DataIntegrityViolationException.class, () -> friendRepository.save(friend));
	}
	@Test
	public void shouldReturnFriendForAUser() {
		// Given user and a friend
		Friend friend = new Friend();
		friend.setName("friend");
		friend.setOwner(user);
		entityManager.persistAndFlush(friend);
		// When search for a friend by user and friend name
		Friend foundFriend = friendRepository.getOneByOwnerAndName(user, "friend");
		// Saved friend and found friends are equals
		assertNotNull(foundFriend);
		assertEquals(foundFriend, friend);
	}
}

 */