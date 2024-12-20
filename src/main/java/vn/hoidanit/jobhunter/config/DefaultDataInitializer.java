package vn.hoidanit.jobhunter.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.util.CsvHelper;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DefaultDataInitializer {
    private final UserRepository userRepository;
    private final UserService userService;

    public DefaultDataInitializer(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            // Đoạn code sẽ được chạy khi ứng dụng khởi động
            System.out.println("Application has started!");

            // Ví dụ: Thực hiện khởi tạo dữ liệu
            initialDefaultUsers();

            System.out.println("Application Still running at: ");
            System.out.println("localhost:8080/admin");
            System.out.println("localhost:8080");
        };
    }

    private void initialDefaultUsers() {
        if(userRepository.count() == 0) {
            try {
                InputStream is = new ClassPathResource("static/csv/users.csv").getInputStream();
                List<User> userList = CsvHelper.csvToUser(is);

                for (User user : userList) {
                    userService.handleCreateUser(user);
                }

                System.out.println("Imported user data from CSV file successfully.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("User data already exists in the database.");
        }
    }
}
