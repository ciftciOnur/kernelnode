package tr.edu.yeditepe.kernelnode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class KernelNodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(KernelNodeApplication.class, args);
    }

    @Bean
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Istanbul")));
    }

}