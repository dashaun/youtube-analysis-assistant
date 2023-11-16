package io.dashaun.service.youtubeanalysisassistant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YaaClientTest {

    @Test
    void name(@Autowired YaaClient yaaClient) {
        var prompt = """
                Thumbnail Design: A vibrant background with code snippets and the Java coffee cup logo.
                 The text  "Mastering Java 21 & Spring" in bold, modern font. Icons representing different IDEs 
                 like IntelliJ IDEA and VS Code. A 'play' button symbol conveying that the blog post contains 
                 a video tutorial.
                """;
        var url = yaaClient.generateImage(prompt, ImageSize.SIZE_1024x1024);
        System.out.println(url);
    }
}