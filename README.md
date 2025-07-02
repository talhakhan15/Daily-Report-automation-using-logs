# You can generate a JRE and then convert it to exe file using Launch4j
# Daily Report Mailer - Selenium Automation Script


This project automates the process of logging into Microsoft Outlook, composing a daily report email, and sending it with an attachment using Selenium WebDriver. The script is written in Java and uses Google Chrome for browser automation.

## Features

- **Login Automation**: Logs into Microsoft Outlook using your credentials.
- **Email Composition**: Automatically fills in the subject and recipient.
- **File Attachment**: Attaches a specified report file from your local machine.
- **Headless Mode**: Optionally run the script in headless mode (no GUI), which is useful for background automation tasks.
- **Logging**: Logs the actions for debugging and monitoring.
- **Customizable**: Easily modify the recipient, subject, and file attachment path.

## Prerequisites

- Java 8 or higher
- [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/) for browser automation
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/) for Google Chrome
- Maven (for managing dependencies)
