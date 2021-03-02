package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import org.junit.Assert;
import org.junit.Test;


public class SocialNetworkTest {
    @Test
    public void testUser() {
        User user1 = new User("user1");
        Assert.assertEquals(null, user1.getAge());
        Assert.assertEquals("user1", user1.getName());

    }
    @Test
    public void testMessage() {
        User user1 = new User("user1");
        Message message1 = new Message("title1", "content1", user1);
        Assert.assertEquals("title1", message1.getTitle());
        Assert.assertEquals("content1", message1.getMessage());
        Assert.assertEquals(user1, message1.getAuthor());

    }
    @Test
    public void testGroup() {
        User user1 = new User("user1");
        User user2 = new User("user2");
        Group group1 = new Group("suema", user1);
        Assert.assertEquals("suema", group1.getName());
        Assert.assertEquals(user1, group1.getOwner());
        group1.addUser(user2);
        //Assert.assertEquals();

    }
}