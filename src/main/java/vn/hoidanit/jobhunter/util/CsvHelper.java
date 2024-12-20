package vn.hoidanit.jobhunter.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.util.constant.GenderEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    public static List<User> csvToUser(InputStream is) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    User user = getUser(fields);
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private static User getUser(String[] fields) {
        User user = new User();
        user.setName(fields[0]);
        user.setEmail(fields[1]);
        user.setPassword(fields[2]);
        user.setAge(Integer.parseInt(fields[3]));
        user.setGender(GenderEnum.valueOf(fields[4]));
        user.setAddress(fields[5]);

        return user;
    }
}
