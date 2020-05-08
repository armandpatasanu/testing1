package org.loose.oose.fis.project.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.loose.oose.fis.project.exceptions.CouldNotWriteUsersException;
import org.loose.oose.fis.project.exceptions.EmailAlreadyUsedException;
import org.loose.oose.fis.project.exceptions.UsernameAlreadyExistsException;
import org.loose.oose.fis.project.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static User getUser(String name)
    {
        for(User u:users)
            if (Objects.equals(name, u.getUsername()))
                return u;
        return null;
    }
    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static boolean checkLoginUsername(String username)  {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                return true;
        }
        return false;
    }
    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    private static void checkEmailDoesNotAlreadyExist(String email) throws EmailAlreadyUsedException {
        for (User user : users) {
            if (Objects.equals(email, user.getEmail()))
                throw new EmailAlreadyUsedException(email);
        }
    }

    public static void addUser(String fname, String lname,String username, String email,String password) throws UsernameAlreadyExistsException,EmailAlreadyUsedException
    {
        checkEmailDoesNotAlreadyExist(email);
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(fname,lname,username,email,encodePassword(username, password)));
        persistUsers();
    }

    public static boolean checkLoginPassword(String password,String username)
    {
        String newpassword = encodePassword(username, password);

        for (User user : users) {
            if (newpassword.equalsIgnoreCase(user.getPassword()))
                return true;
        }
        return false;
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
