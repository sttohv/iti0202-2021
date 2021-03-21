package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class SocialNetworkTest {
    @Test
    public void testUser() {
        User user1 = new User("user1");
        User user2 = new User("user2", 18);
        Assert.assertEquals(java.util.Optional.of(18).get(), user2.getAge());
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

    @Test
    public void testFeed(){
        User stina = new User("stina");
        Group group1 = new Group("suema", stina);
        group1.setName("suisa");
        Assert.assertEquals("suisa", group1.getName());
        Message message1 = new Message("Tiitel", "Sõnum", stina);
        group1.publishMessage(message1);
        Set<Message> setOfGroupMessages = new HashSet<>(group1.getMessages());
        Feed feed2 = new Feed(stina, setOfGroupMessages);
        Assert.assertEquals("stina", feed2.getUser().getName());
        Assert.assertEquals(true, feed2.getMessages().contains(message1));
    }

    @Test
    public void testNetwork(){
        User stina = new User("stina");
        Group group1 = new Group("suema", stina);
        Message message1 = new Message("Tiitel", "Sõnum", stina);
        Set<Message> setOfMessages = new HashSet<>();
        setOfMessages.add(message1);
        Set<Group> setOfGroups = new HashSet<>();
        setOfGroups.add(group1);
        Feed feed2 = new Feed(stina, setOfMessages);

        SocialNetwork fb = new SocialNetwork();
        fb.registerGroup(group1);
        Assert.assertEquals(fb.getGroups(), setOfGroups);
        Assert.assertEquals(fb.getFeedForUser(stina).getUser(), stina);
    }
}