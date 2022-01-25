package com.paloit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the Swagger-UI.
 */
@Configuration
public class SpringDocConfig {

    private static final String DATE_TIME_PATTERN = "MM-dd-yyyy HH:mm:ss";
    private final GitProperties gitProperties;
    private final String environment;

    public SpringDocConfig(
        @Value(value = "${spring.profiles.active:Not specified}") String environment,
        @Autowired(required = false) GitProperties gitProperties) {
        this.gitProperties = gitProperties;
        this.environment = environment;
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Openapi 3 Codegen API")
                .description(
                    String.format(
                        "``` Environment: %s```<br>%s",
                        environment, gitInfo()
                    )
                )
                .version("v0.0.1"))
            ;
    }

    private String gitInfo() {
        if(gitProperties != null) {
            String branch = gitProperties.getBranch();
            String commitId = gitProperties.getShortCommitId();
            ZoneId zoneId = ZoneId.of("Asia/Bangkok");
            var dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
            String commitTime = gitProperties.getCommitTime().atZone(zoneId).format(dateTimeFormatter);
            String gitInfo = constructGitInfo(commitId, branch, commitTime);
            return gitInfo;
        } else {
            return "<br><br><br>```No commit information available```";
        }
    }

    private String constructGitInfo(String commitId, String branch, String commitTime) {
        var builder = new StringBuilder();
        builder.append(" <br><br><br> ");
        builder.append("```");
        builder.append("Commit: ");
        builder.append(commitId);
        builder.append(" | ");
        builder.append("Branch: ");
        builder.append(branch);
        builder.append(" | ");
        builder.append("Time: ");
        builder.append(commitTime);
        builder.append("```");
        builder.append("<br><br>");
        builder.append("```");
        builder.append(gitProperties.get("commit.message.short"));
        builder.append("```");
        return builder.toString();
    }
}
