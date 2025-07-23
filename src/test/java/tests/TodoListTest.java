package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TodoPage;

public class TodoListTest extends BaseTest {

    @Test
    public void testCreateTodoListAndAddItemsWithCheckbox() throws InterruptedException {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.navigateTo("https://eviltester.github.io/simpletodolist/todolists.html");

        // Create new list
        String listName = "MyTestList";
        todoPage.createNewTodoList(listName);
        Assert.assertTrue(todoPage.isListCreated(listName), "List should be created");

        // Open list
        todoPage.openListByName(listName);


        // Items to add
        String[] todoItems = {"Buy Milk", "Buy car", "Buy chocolate"};

        // Add items
        for (String item : todoItems) {
            todoPage.addTodoItem(item);
            Assert.assertTrue(todoPage.isTodoItemPresent(item), "Item '" + item + "' should be present");


        }

        // Check the checkbox for the third item (third item)
        todoPage.checkTodoItem(todoItems[2]);
        Assert.assertTrue(todoPage.isTodoItemChecked(todoItems[2]), "Item '" + todoItems[2] + "' should be checked");

        // Delete first item
        todoPage.deleteTodoItem(todoItems[0]);
        Assert.assertFalse(todoPage.isTodoItemPresent(todoItems[0]), "Item '" + todoItems[0] + "' should be deleted");

       }


    @Test
    public void testCreateTodoListOnly() throws InterruptedException {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.navigateTo("https://eviltester.github.io/simpletodolist/todolists.html");

        String listName = "ListOnlyTest";
        todoPage.createNewTodoList(listName);
        Assert.assertTrue(todoPage.isListCreated(listName), "List should be created");

    }
    @Test
    public void testAddSingleItemToList() throws InterruptedException {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.navigateTo("https://eviltester.github.io/simpletodolist/todolists.html");

        String listName = "SingleItemList";
        todoPage.createNewTodoList(listName);

        todoPage.openListByName(listName);

        String todoItem = "Do Homework";
        todoPage.addTodoItem(todoItem);

        Assert.assertTrue(todoPage.isTodoItemPresent(todoItem), "Item should be added to the list");
    }
    @Test
    public void testDeleteTodoItem() throws InterruptedException {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.navigateTo("https://eviltester.github.io/simpletodolist/todolists.html");

        String listName = "DeleteTest";
        String itemToDelete = "Trash";

        todoPage.createNewTodoList(listName);
        todoPage.openListByName(listName);

        todoPage.addTodoItem(itemToDelete);
        Assert.assertTrue(todoPage.isTodoItemPresent(itemToDelete));

        todoPage.deleteTodoItem(itemToDelete);
        Assert.assertFalse(todoPage.isTodoItemPresent(itemToDelete));
    }
    @Test
    public void testAddMultipleItems() throws InterruptedException {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.navigateTo("https://eviltester.github.io/simpletodolist/todolists.html");
        String listName = "MultipleItemsList";
        todoPage.createNewTodoList(listName);

        todoPage.openListByName(listName);

        String[] items = {"Milk", "Bread", "Eggs"};

        for (String item : items) {
            todoPage.addTodoItem(item);
            Assert.assertTrue(todoPage.isTodoItemPresent(item), "Item should be added: " + item);
        }
    }
    @Test
    public void testCheckTodoItem() {
        TodoPage todoPage = new TodoPage(driver);
        todoPage.navigateTo("https://eviltester.github.io/simpletodolist/todolists.html");

        // Create list and add item
        String listName = "CheckListTest";
        String todoItem = "select item";

        todoPage.createNewTodoList(listName);
        Assert.assertTrue(todoPage.isListCreated(listName), "List should be created");

        todoPage.openListByName(listName);
        todoPage.addTodoItem(todoItem);
        Assert.assertTrue(todoPage.isTodoItemPresent(todoItem), "Todo item should be present");

        // Check the checkbox
        todoPage.checkTodoItem(todoItem);
        Assert.assertTrue(todoPage.isTodoItemChecked(todoItem), "Todo item should be checked");
    }

}
