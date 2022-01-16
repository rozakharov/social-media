package ru.otus.socialnetwork.generator;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.otus.socialnetwork.dto.User;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty("${application.generate-users.enabled}")
public class UsersGenerator implements CommandLineRunner {

  @Value("${application.generate-users.count}")
  private Integer count;

  @Override
  public void run(String... args) throws Exception {
    FileWriter fileWriter = new FileWriter("generated-users.csv");
    PrintWriter printWriter = new PrintWriter(fileWriter);
    Faker faker = new Faker(new Locale("ru_Ru"));
    for (int i = 0; i < count; i++) {
      User user = new User();
      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      user.setEmail(firstName + "." + lastName + "@+" + i + "example.com");
      user.setPassword("fake-password");
      user.setFirstName(firstName);
      user.setLastName(lastName);
      user.setAge(faker.number().numberBetween(18, 80));
      user.setHobby(faker.programmingLanguage().name());
      user.setCity(faker.address().city());
      printWriter.println(
          "0," +
              "'" + user.getEmail() + "'," +
              "'" + user.getPassword() + "'," +
              "'" + user.getFirstName() + "'," +
              "'" + user.getLastName() + "'," +
              "'" + user.getAge() + "'," +
              "," + //sex
              "'" + user.getHobby() + "'," +
              "'" + user.getCity() + "'");
    }
    printWriter.close();
  }
}
