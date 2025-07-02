package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DailyReportMailer {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(DailyReportMailer.class.getName());
    String Rec = "";
    private String Subject = "Daily Report";

    void setupDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            //  options.addArguments("--headless=new"); // Headless mode enabled
            options.addArguments("--disable-gpu");  // Optional: for older systems
            options.addArguments("--window-size=1920,1080"); // Prevent UI issues
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            logger.info("ChromeDriver initialized in headless mode successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Driver setup failed: ", e);
            throw e;
        }


    }

    void signin() {
        try {
            driver.get("https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=9199bf20-a13f-4107-85dc-02114787ef48&scope=https%3A%2F%2Foutlook.office.com%2F.default%20openid%20profile%20offline_access&redirect_uri=https%3A%2F%2Foutlook.live.com%2Fmail%2F&client-request-id=4041ce95-db08-ce11-c1e0-b55daf16d644&response_mode=fragment&response_type=code&x-client-SKU=msal.js.browser&x-client-VER=4.4.0&client_info=1&code_challenge=Uk8aLZ0RTSbfUx0CxQY6KBHHg2LVplbQ40lk96K_9sU&code_challenge_method=S256&prompt=select_account&nonce=0196804d-102c-7f20-a12b-f79dc0c42001&state=eyJpZCI6IjAxOTY4MDRkLTEwMmItNzI0Yi1hMzkwLWQ2NDQxMTA4ZTE4MCIsIm1ldGEiOnsiaW50ZXJhY3Rpb25UeXBlIjoicmVkaXJlY3QifX0%3D&claims=%7B%22access_token%22%3A%7B%22xms_cc%22%3A%7B%22values%22%3A%5B%22CP1%22%5D%7D%7D%7D&cobrandid=ab0455a0-8d03-46b9-b18b-df2f57b9e44c&fl=dob,flname,wld");
            logger.info("Opened Sign-in page");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='i0116']")))
                    .sendKeys("your@mail.co" + Keys.ENTER);
            logger.info("Entered email");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='i0118']")))
                    .sendKeys("12@Abc" + Keys.ENTER);
            logger.info("Entered password");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='No']"))).click();
            logger.info("Clicked 'No' on Stay signed in");

        } catch (TimeoutException | NoSuchElementException e) {
            logger.log(Level.SEVERE, "Signin failed: ", e);
        }
    }

    void outlook() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='New mail']"))).click();
            logger.info("Clicked on 'New Mail'");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='0']"))).sendKeys("m.fawaz@MyTm.pk");
            logger.info("Entered recipient");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add a subject']")))
                    .sendKeys(Subject);
            logger.info("Entered subject");

            driver.findElement(By.xpath("//div[@class='XnGcL' and @id='editorParent_1']")).click();
            logger.info("Focused on message body");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //button[@id=5]"))).click();
            logger.info("Clicked attach button");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Attach file']"))).click();
            logger.info("Clicked 'Attach file'");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Browse this computer']"))).click();
            logger.info("Clicked 'Browse this computer'");

            Thread.sleep(3000);
            logger.info("Waiting for file dialog to appear...");

            uploadFileWithRobot("C:\\Users\\Lenovo\\Desktop\\DailyReport.docx");

            Thread.sleep(2000);
            logger.info("Waiting after file upload...");

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'fui-Button') and contains(text(),'Send')]")
            )).click();
            logger.info("Clicked send button, email should be sent!");

        } catch (TimeoutException | NoSuchElementException e) {
            logger.log(Level.SEVERE, "Error during email composition or sending: ", e);
        }
    }

    void uploadFileWithRobot(String filePath) throws InterruptedException {
        try {
            logger.info("Preparing to upload file using Robot: " + filePath);
            Robot robot = new Robot();
            robot.setAutoDelay(500);

            StringSelection selection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            logger.info("File path copied to clipboard");

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            logger.info("Pasted path in dialog");

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            logger.info("Confirmed file upload with Enter");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "File upload via Robot failed: ", e);
        }
    }


}
