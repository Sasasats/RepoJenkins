package Utils;

import Models.Post;
import Models.UserModel.User;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIUtils {

    private final static ISettingsFile settingsData = new JsonSettingsFile("settingsData.json");

    private final static RequestSpecification REQUEST =
            new RequestSpecBuilder()
                    .setBaseUri(settingsData.getValue("/baseUri").toString())
                    .setContentType(ContentType.JSON)
                    .build();

    public List<Post> getPostList() {
        return given()
                .spec(REQUEST)
                .basePath(settingsData.getValue("/basePatchPosts").toString())
                .get()
                .then().statusCode(200)
                .extract().jsonPath().getList("", Post.class);
    }

    public Post getPost(int postId) {
        return given()
                .spec(REQUEST)
                .basePath(settingsData.getValue("/basePatchPosts").toString())
                .get("/" + postId)
                .then().statusCode(200)
                .extract().as(Post.class);
    }

    public String getNotValidPostAsString(int postId) {
        return given()
                .spec(REQUEST)
                .basePath(settingsData.getValue("/basePatchPosts").toString())
                .get("/" + postId)
                .then().statusCode(404)
                .extract().asString();
    }

    public Post getPostedPost(Post postRequest) {
        return given()
                .spec(REQUEST)
                .basePath("/posts")
                .body(postRequest)
                .when().post()
                .then().statusCode(201)
                .extract().as(Post.class);
    }

    public List<User> getUserList() {
        return given()
                .spec(REQUEST)
                .basePath(settingsData.getValue("/basePatchUsers").toString())
                .get()
                .then().statusCode(200)
                .extract().jsonPath().getList("", User.class);
    }

    public User getUserFromList(List<User> userList, int userId) {
        User user5 = new User();

        for (User user : userList) {
            if (user.getId() == userId){
                user5 = user;
            }
        }
        return user5;
    }

    public User getUser(int userId) {
        return given()
                .spec(REQUEST)
                .basePath(settingsData.getValue("/basePatchUsers").toString())
                .get("/" + userId)
                .then().assertThat()
                .statusCode(200)
                .extract().as(User.class);
    }
}
