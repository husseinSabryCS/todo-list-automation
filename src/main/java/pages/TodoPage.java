package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TodoPage extends BasePage {

    public TodoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/section/header/input")
    private WebElement newListInput;

    @FindBy(id = "create-todo-list")
    private WebElement createListButton;

    @FindBy(xpath = "//label[text()=\"MyTestList\"]/..//a")
    private List<WebElement> todoLists;

    @FindBy(className = "new-todo")
    private WebElement newTodoInput;

    @FindBy(id = "add-todo")
    private WebElement addTodoButton;

    @FindBy(css = ".todos li")
    private List<WebElement> todoItems;

    public void createNewTodoList(String listName) {
        waitAndType(newListInput, listName);
        newListInput.sendKeys(Keys.ENTER);  // بدل الزرار
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isListCreated(String listName) {
        try {
            WebElement label = driver.findElement(By.xpath("//label[text()='" + listName + "']"));
            return label.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openListByName(String listName) {
        WebElement link = driver.findElement(By.xpath("//label[text()='" + listName + "']/..//a"));
        waitAndClick(link);
    }


    public void addTodoItem(String itemText) {
        newTodoInput.clear();
        newTodoInput.sendKeys(itemText);
        newTodoInput.sendKeys(Keys.ENTER);
    }

    public boolean isTodoItemPresent(String itemText) {
        try {
            WebElement item = driver.findElement(By.xpath("//label[text()='" + itemText + "']"));
            return item.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void deleteTodoItem(String itemText) {
        String xpath = "//label[text()='" + itemText + "']/..//button";
        WebElement deleteButton = driver.findElement(By.xpath(xpath));
        waitAndClick(deleteButton);
    }
    public void checkTodoItem(String itemText) {
        By checkboxLocator = By.xpath("//label[text()='" + itemText + "']/..//input");
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isTodoItemChecked(String itemText) {
        By checkboxLocator = By.xpath("//label[text()='" + itemText + "']/..//input");
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));
        return checkbox.isSelected();
    }


}
