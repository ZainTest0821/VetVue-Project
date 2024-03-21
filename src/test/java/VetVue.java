import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class VetVue {

    WebDriver driver;


    @BeforeTest
    public void Launch() {


        WebDriverManager.chromedriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://vetvue.celeritasdigital.com/");

    }

    @AfterTest
    void teardown() {
        //driver.quit();

    }
    // Login

    @Test(priority = 1)
    void login() throws InterruptedException {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // First scroll to end
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Check Login button functionality

        WebElement loginbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@type= submit'])[53]")));
        loginbutton.click();

        // Check Forgot button functionality

        WebElement forgotbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@class=col-md-12 text-end mb-3'])[51]")));
        forgotbutton.click();

        // Enter  wrong login cred to check it is working as expected

        WebElement email1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        email1.sendKeys("admin@gmail.com");

        // Click on login to check functionality without password
        loginbutton.click();

        //Now enter password
        WebElement password1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password1.sendKeys("admin12345!");

        //Click on Login button again
        Thread.sleep(2000);
        loginbutton.click();

        //Now enter valid cred

        WebElement email2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        email2.sendKeys("admin@gmail.com");

        WebElement password2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password2.sendKeys("admin123!");

        //Click on Login button

        WebElement loginbutton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@type= submit'])[53]")));
        loginbutton2.click();

    }

    @Test(priority = 2)
    void AllCheck() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Scroll to end using Action class
        Thread.sleep(2500);
        WebElement end = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(end).sendKeys(Keys.END).perform();

        Thread.sleep(2000);

        //Scroll to the top using Action class
        WebElement top = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(top).sendKeys(Keys.HOME).perform();

        Thread.sleep(1500);

        // Click on toggle button of random dog entity
        WebElement toogle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@class=card-header d-flex align-items-center justify-content-between collapsed'])[145]")));
        toogle.click();

        // scroll to littile bit down
        js.executeScript("window.scrollTo(" + 100 + "," + 500 + ");");

        Thread.sleep(1500);

        //Again scroll to top
        //Scroll to the top using Action class
        WebElement top1 = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(top1).sendKeys(Keys.HOME).perform();


    }


    @Test(priority = 3)
    void date() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);


        // Days toggle button functionality check

        Thread.sleep(2500);

        // Click on day toggle button
        WebElement daytoogle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn dropdown-toggle btn-light']")));
        daytoogle.click();

        // Click on 7 days
        WebElement day7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='text']")));
        day7.click();

        // Click again on toggle button
        Thread.sleep(3500);
        daytoogle.click();


        // Click on 14 days

        WebElement day14 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text'])[2]")));
        day14.click();

        Thread.sleep(3500);
        //Click again on toggle button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn dropdown-toggle btn-light']")));
        daytoogle.click();

        // Now click on 30 days

        WebElement day30 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text'])[3]")));
        day30.click();


        // Again click on toggle button for selecting 90 days option
        Thread.sleep(3500);
        daytoogle.click();

        // Now select 90 days
        WebElement day90 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text'])[4]")));
        day90.click();

    }

    @Test(priority = 4)
    void create() throws InterruptedException, IOException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        // Check create button is working fine

        Thread.sleep(2500);

        WebElement create = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='bi bi-plus-square-fill font-size24px pe-2 pt-1 text-green']")));
        create.click();

        // Check that proper error show when user did not enter any details and click on Upload

        WebElement upload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']")));
        upload.click();


        // Send values in PetName to create
        WebElement petname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='add_pet_name']")));
        petname.sendKeys(" Romeo");



        // Send value in Pet Family text box
        WebElement petfamily = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='add_pet_family']")));
        petfamily.sendKeys("German Family");


        //enter Value in Breed name
            WebElement breed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='add_pet_breed']")));
            breed.sendKeys(" German");

            // Enter values in Age
        WebElement age=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='add_pet_age']")));
        age.sendKeys("2 years");


        // Enter weight
        WebElement weight=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='add_pet_weight']")));
        weight.sendKeys("40lbs");


        // Enter Clinic Name
        WebElement clinicname=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='add_pet_clinic']")));
        clinicname.sendKeys("The best Dogs clinic by exp");


        // Now select different value from dropdown
        WebElement devicedropdownbutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Select Device']")));
        devicedropdownbutton.click();

        // Select a value from dropdown
        WebElement devicedropdown=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-2-1']")));
        devicedropdown.click();


        // Select pet type
        WebElement pettypdropdown=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Select Pet Type']")));
        pettypdropdown.click();

        // Select Dog from Dropdown
        WebElement petdropdown=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-3-2']")));
        petdropdown.click();


        // Select Gender from dropdown
        WebElement gender=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Select Gender']")));
        gender.click();

        // Select Male from dropdown s
        WebElement genderselect=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-4-1']")));
        genderselect.click();

        // Select the option which is different in selecting male and female
        WebElement optionseclect=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='not-neutered']")));
        optionseclect.click();


        // Select File for upload
        // Click on Upload button

        WebElement uploadbutton=wait.until(ExpectedConditions.elementToBeClickable(By.className("input-file-container")));
        uploadbutton.click();

        // Path of file
        String filepath= "C:\\Users\\Admin\\Downloads\\VetVue\\Upload Pet Image.exe";
        Runtime.getRuntime().exec(filepath);

        Thread.sleep(8000);


        upload.click();

        Thread.sleep(12000);

        }

        @Test(priority = 5)
        void Highrisk () throws InterruptedException {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
            Thread.sleep(1500);

            //Click on High Risk tab button
            WebElement highrisk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ex1-tab-2']")));
            highrisk.click();

            Thread.sleep(1500);

            //Again click on All
            WebElement all = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ex1-tab-1']")));
            all.click();

        }
        @Test(priority = 6)
        void clickonbell () throws InterruptedException {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
            Actions actions = new Actions(driver);

            // Here check the functionality of  bell icon on given entries
            WebElement bellicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='me-2 text-decoration-none position-relative']")));
            bellicon.click();

            // Scroll to end using Action class
            Thread.sleep(2500);
            WebElement end = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
            actions.moveToElement(end).sendKeys(Keys.END).perform();

            Thread.sleep(2000);

            //Scroll to the top using Action class
            WebElement top = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
            actions.moveToElement(top).sendKeys(Keys.HOME).perform();

            Thread.sleep(2000);
        }

        @Test(priority = 7)
        void NotificationSetting () throws InterruptedException {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            // Notification check list
            // Click on Notification setting from Menu

            WebElement notificationsetting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@style='padding-bottom: 0.6rem']")));
            notificationsetting.click();

            // Click on Device toggle button
            WebElement device = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='filter-option-inner-inner']")));
            device.click();
            // Click in Animo
            WebElement animo=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-1-0']")));
            animo.click();

            Thread.sleep(1000);
            device.click();

            //Click on Waldo
            WebElement waldo=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-1-1']")));
            waldo.click();





            // Click on Condition dropdown button
            WebElement condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[2]")));
            condition.click();

            // Select Distance Travels
            WebElement distance=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-2-0']")));
            distance.click();

            Thread.sleep(2000);

            // Select Active
            /*WebElement active=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-2-1']")));
            //active.click();


            // Select Calories Burned
            WebElement calories=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-2-2']")));
            calories.click();

            Thread.sleep(2000);
             */



            // Click on Days dropdown button
            WebElement days = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[3]")));
            days.click();

            // Select 7days
            WebElement day7=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-3-0']")));
            day7.click();

            Thread.sleep(2000);
            days.click();

            // Select 14 Days
            WebElement day14=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-3-1']")));
            day14.click();

            Thread.sleep(2000);
            days.click();
            Thread.sleep(1500);

            // Select 30 days
            WebElement day30=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-3-2']")));
            day30.click();

            Thread.sleep(2000);
            days.click();
            Thread.sleep(1000);

            // Select  90 days
            WebElement day90=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-3-3']")));
            day90.click();

            Thread.sleep(2000);





            // Click on Range Scenario toggle button
            WebElement Rangescenario = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[4]")));
            Rangescenario.click();

            // Select Up/Down
            WebElement updown=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-4-0']")));
            updown.click();


            Thread.sleep(2000);
            Rangescenario.click();
            Thread.sleep(1000);

            // Select Up
            WebElement up=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-4-1']")));
            up.click();

            Thread.sleep(2000);
            Rangescenario.click();
            Thread.sleep(1000);

            // Select Down
            WebElement down=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-4-2']")));
            down.click();

            Thread.sleep(2000);


            //Click on Priority toggle button
            WebElement priority = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[5]")));
            priority.click();

            // Select High
            WebElement high=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-5-0']")));
            high.click();

            Thread.sleep(2000);
            priority.click();
            Thread.sleep(1000);

            // Select Medium
            WebElement medium=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-5-1']")));
            medium.click();

            Thread.sleep(2000);
            priority.click();
            Thread.sleep(1000);


            // Select Low
            WebElement low=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bs-select-5-2']")));
            low.click();




            // Send Value in Percentage textbox
            WebElement percentage=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='per_notify']")));
            percentage.sendKeys("50");



            //Click on Add  button
            WebElement add=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn btn-primary pe-5 ps-5 mb-1']")));
            add.click();

            Thread.sleep(4000);
        }


        @Test(priority = 8)
        void crud () throws InterruptedException {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            // Check that the edit and delete button on value is working as expected

            // Click on edit

            WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='cursor-point']")));
            edit.click();

            // Click on Device dropdown button in edit's dialogbox
            WebElement device = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[9]")));
            device.click();

            // Click on condition in dialogbox
            WebElement condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[10]")));
            condition.click();

            // Click on days in dialogbox
            WebElement days = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[11]")));
            days.click();

            // Click on Range scenario in dialogbox
            WebElement rangescenario = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[12]")));
            rangescenario.click();

            // Click on update button in dialgobox
            WebElement update = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='btn btn-primary pe-5 ps-5'])[5]")));
            update.click();

            Thread.sleep(1000);

            // Now close the dialog box
            // WebElement close=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='bi bi-x-circle'])[6]")));
            //close.click();


            // Now check delete button functionality

            WebElement delete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='cursor-point'])[2]")));
            delete.click();

            // Click on cross button to close delete dialog box
            WebElement cross = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text-lightGray font-size24px'])[8]")));
            cross.click();

        }

        @Test(priority = 9)
        void Notification () throws InterruptedException {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
            Actions actions = new Actions(driver);

            // Here we check notification screen
            // Click on All notification button from menu bar
            WebElement notificationscreen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='nav-link border-radius-10px  d-flex align-items-center '])[2]")));
            notificationscreen.click();

            // Enter some value in Search bar to check its functionality
            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='search']")));
            search.sendKeys("Testing search bar by Mr.Arif");

            Thread.sleep(2000);


            // Now click on priority dropdown button
            WebElement priority = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='button']")));
            priority.click();


        }
        @Test(priority = 10)
        void logout () {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            // Now test that our logout functionality is working fine

            //Click on Logout button dropdown

            WebElement logoutdropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='dropdown']")));
            logoutdropdown.click();

            //Now click on Logout button
            WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='dropdown-item']")));
            logout.click();

        }
    }
