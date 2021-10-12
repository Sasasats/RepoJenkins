package Utils;

import Models.*;
import Models.UserModel.Address;
import Models.UserModel.Company;
import Models.UserModel.Geo;
import Models.UserModel.User;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonUtils {
    public static Post setPost(String postJson){
        final ISettingsFile expectedPost = new JsonSettingsFile(postJson);

        Post post = new Post();
        post.setUserId((int)expectedPost.getValue("/userId"));
        post.setId((int)expectedPost.getValue("/id"));
        post.setTitle(expectedPost.getValue("/title").toString());
        post.setBody(expectedPost.getValue("/body").toString());

        return post;
    }

    public static User setUser(String userJson){
        final ISettingsFile expectedUser = new JsonSettingsFile(userJson);

        Geo geo = new Geo();
        geo.setLat(expectedUser.getValue("/address/geo/lat").toString());
        geo.setLng(expectedUser.getValue("/address/geo/lng").toString());

        Address address = new Address();
        address.setCity(expectedUser.getValue("/address/city").toString());
        address.setStreet(expectedUser.getValue("/address/street").toString());
        address.setSuite(expectedUser.getValue("/address/suite").toString());
        address.setZipcode(expectedUser.getValue("/address/zipcode").toString());
        address.setGeo(geo);

        Company company = new Company();
        company.setName(expectedUser.getValue("/company/name").toString());
        company.setCatchPhrase(expectedUser.getValue("/company/catchPhrase").toString());
        company.setBs(expectedUser.getValue("/company/bs").toString());

        User user = new User();
        user.setId((int)expectedUser.getValue("/id"));
        user.setName(expectedUser.getValue("/name").toString());
        user.setUsername(expectedUser.getValue("/username").toString());
        user.setEmail(expectedUser.getValue("/email").toString());
        user.setAddress(address);
        user.setPhone(expectedUser.getValue("/phone").toString());
        user.setWebsite(expectedUser.getValue("/website").toString());
        user.setCompany(company);

        return user;
    }
}
