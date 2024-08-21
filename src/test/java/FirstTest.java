import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstTest {

    @Test
    public void run() {
        //Windows only
        //System.setProperty("web.driver.chrome.driver", "path");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        WebElement myCheck2 = driver.findElement(By.id("my-check-2"));
        Assert.assertFalse(myCheck2.isSelected());
        myCheck2.click();
        boolean checkBoxResult = myCheck2.isSelected();

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String submitMessage = message.getText();

        String title = driver.getTitle();
        driver.quit();
        Assert.assertEquals(title,"Web form - target page");
        Assert.assertEquals(submitMessage,"Received!");
        Assert.assertTrue(checkBoxResult);
    }

}
