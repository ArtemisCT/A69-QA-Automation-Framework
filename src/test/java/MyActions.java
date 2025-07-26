//Mouse Actions in Selenium
//
//doubleClick(): Performs double click on the element.
//clickAndHold(): Performs long click on the mouse without releasing it.
//dragAndDrop(): Drags the element from one point and drops to another.
//moveToElement(): Shifts the mouse pointer to the center of the element.
//contextClick(): Performs right-click on the mouse.
//


//Keyboard Actions in Selenium
//sendKeys(): Sends a series of keys to the element.
//keyUp(): Performs key release.
//keyDown(): Performs keypress without release.

//Examples:
//How to move mouse to specific element and click to it?
//Actions action = new Actions(driver);
//
////Move over the menu options
//WebElement menuOption = driver.findElement(By.xpath("locator"));
//action.moveToElement(menuOption).perform();
//
////Displays the menu list with options, now we can click an option from the menu list
//WebElement selectMenuOption = driver.findElement(By.xpath("locator2"));
//action.click(selectMenuOption).perform();
//--------------

//How to double click on the Web Element?
//Actions action = new Actions(driver);
//
////Double click on element
//WebElement button = driver.findElement(By.xpath("locator"));
//
//action.doubleClick(button).perform();
//--------------

//How to right click on the Web Element?
//Actions action = new Actions(driver);
//
////Right click on element
//WebElement button = driver.findElement(By.xpath("locator"));
//
//action.contextClick(button).perform();


//Multiple Web Elements
//Sometimes, working with only one element is not enough, and you need to interact with multiple web elements.
//
//List<WebElement> listOfElements = driver.findElements(By.xpath("//div"));
//
//This will store all elements matching the provided locator as a List of WebElement objects, allowing you to work with multiple elements.
