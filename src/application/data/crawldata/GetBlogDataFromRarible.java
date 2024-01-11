package application.data.crawldata;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class GetBlogDataFromRarible {
    public static void main(String[] args) {
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.50 Safari/537.36");

        // Set up the Chrome web driver
        WebDriver driver = new ChromeDriver(options);

        // URL of the web page you want to scrape
        rarible(driver);

        driver.close();
    }

    private static void rarible(WebDriver driver) {
        String url = "https://rarible.com/blog/?utm_source=rarible.com&utm_medium=landing-page&utm_campaign=editorial_blog";
        driver.get(url);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Scroll logic
        int initialHeight = 0;
        while (true) {
            ((ChromeDriver) driver).executeScript("window.scrollBy(0, 500);");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int newHeight = Integer.parseInt(((ChromeDriver) driver).executeScript("return window.scrollY").toString());
            if (newHeight == initialHeight) {
                break;
            }
            initialHeight = newHeight;
        }

        // Scrape data
        WebElement articles = driver.findElement(By.className("post-feed"));
        List<WebElement> articleElements = articles.findElements(By.className("post-card"));
        JSONArray articlesList = new JSONArray();

        for (WebElement articleElement : articleElements) {
            JSONObject article = new JSONObject();
            // Extracting article details
            // ...

            articlesList.put(article);
        }

        // Read and update JSON file
        JSONObject data = new JSONObject();
        try {
            String content = new String(Files.readAllBytes(Paths.get("data.json")));
            data = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        data.put("rarible", articlesList);

        try (FileWriter file = new FileWriter("data.json")) {
            file.write(data.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
