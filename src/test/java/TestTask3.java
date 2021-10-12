import Models.Post;
import Models.UserModel.User;

import Utils.*;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestTask3 {

    private final APIUtils api = new APIUtils();
    private final static ISettingsFile settingsData = new JsonSettingsFile("settingsData.json");

    @Test
    public void testAPI() {
        //1
        AqualityServices.getLogger().info("Получение списка всех постов");
        Assert.assertTrue(SortUtils.isPostsSortedById(api.getPostList()));

        //2
        AqualityServices.getLogger().info("Получение поста с id 99");
        Post expectedPost = JsonUtils.setPost("post99.json");
        Post actualPost = api.getPost(expectedPost.getId());

        Assert.assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "userId not equals");
        Assert.assertEquals(actualPost.getId(), expectedPost.getId(), "id not equals");
        Assert.assertFalse(actualPost.getTitle().isEmpty(), "title is empty");
        Assert.assertFalse(actualPost.getBody().isEmpty(), "body is empty");

        //3
        AqualityServices.getLogger().info("Получение несуществующего поста с id 150");
        Assert.assertEquals(api.getNotValidPostAsString((int)settingsData.getValue("/notExistPostId")), settingsData.getValue("/emptyRequestBody"), "post not empty");

        AqualityServices.getLogger().info("Отправка поста");
        expectedPost.setBody(RandomUtils.getRandomString(5));
        expectedPost.setTitle(RandomUtils.getRandomString(5));
        expectedPost.setUserId(1);
        expectedPost.setId(0);

        actualPost = api.getPostedPost(expectedPost);
        Assert.assertEquals(actualPost.getBody(), expectedPost.getBody(), "body not equals");
        Assert.assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "title not equals");
        Assert.assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "userId not equals");
        Assert.assertFalse(("" + actualPost.getId()).isEmpty(), "id is empty");

        //5
        AqualityServices.getLogger().info("Получение списка пользователей");
        List<User> userList = api.getUserList();
        User expectedUser = JsonUtils.setUser("user5.json");
        User actualUser = api.getUserFromList(userList, expectedUser.getId());
        Assert.assertEquals(actualUser, expectedUser, "Users not equals");

        //6
        AqualityServices.getLogger().info("Получение пользователя c id 5");
        actualUser = api.getUser(expectedUser.getId());
        Assert.assertEquals(expectedUser, actualUser, "Users not equals");
    }
}