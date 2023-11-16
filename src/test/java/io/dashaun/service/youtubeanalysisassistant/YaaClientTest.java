package io.dashaun.service.youtubeanalysisassistant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YaaClientTest {

    @Test
    void name(@Autowired YaaClient yaaClient) {
        var url = yaaClient.generateImage("Thumbnail Design: A vibrant background with code snippets and the Java coffee cup logo. The text \"Mastering Java 21 & Spring\" in bold, modern font. Icons representing different IDEs like IntelliJ IDEA and VS Code. A 'play' button symbol conveying that the blog post contains a video tutorial.");
        System.out.println(url);
    }
}