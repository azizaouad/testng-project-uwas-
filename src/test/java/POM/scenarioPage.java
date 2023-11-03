package POM;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;

public class scenarioPage extends Base {
    public static void uploadImage() {
        waitForVisibilityOfElement(By.id("testUpload"));
        WebElement upload_button = driver.findElement(By.id("testUpload"));
        // Thread.sleep(2000);
        upload_button.click();
        String relativePath = "src/test/resources/data/traditions-noel-europe-1024x683.jpg";
        File file = new File(relativePath);
        String absolutePath = file.getAbsolutePath();
        // Thread.sleep(5000);
        WebElement source = driver.findElement(By.id("upload-photos"));
        source.sendKeys(absolutePath);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public static void CheckIfTheImageUploaded() {
        waitForVisibilityOfElement(By.className("ant-divider-inner-text"));
        boolean upload = driver.findElement(By.className("ant-divider-inner-text"))
                .isDisplayed();
        if (upload) {
            System.out.println("the photos are successfully uploaded");
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/button"))
                    .click();

        } else {
            System.out.println("photos are not uploaded ");
        }
    }

}
