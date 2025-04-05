
   // Implicit Wait
   // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));


   // Explicit Wait
   // // Assuming 'driver' is our WebDriver instance
   //WebDriver driver = new ChromeDriver(); // or another WebDriver instance
   //
   /// / Create a WebDriverWait instance with a timeout of 10 seconds
   //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   //
   //// Wait until the element is visible
   //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("locator")));


   /*
   Expected Conditions

Expected conditions help us specify what exactly we are waiting for. Here are the available conditions:

titleIs: Waits for the page title to be exactly equal to a specified value.

titleContains: Waits for the page title to contain a specified string.

presenceOfElementLocated: Waits for an element to be present in the DOM, regardless of its visibility.

visibilityOfElementLocated: Waits for an element to be present in the DOM and visible.

visibilityOf: Waits for a specific WebElement to be visible.

presenceOfAllElementsLocatedBy: Waits for all elements matching the locator to be present in the DOM.

textToBePresentInElement: Waits for the specified text to be present in a specified element.

textToBePresentInElementValue: Waits for the specified text to be present in a specified element's value attribute.

frameToBeAvailableAndSwitchToIt: Waits for a specified frame to be available and switches to it.

invisibilityOfElementLocated: Waits for an element to be invisible.

elementToBeClickable: Waits for an element to be visible and enabled so it can be clicked.

stalenessOf: Waits for an element to be removed from the DOM.

elementToBeSelected: Waits for an element to be selected.

elementSelectionStateToBe: Waits for an element's selection state to match a specified state.

alertIsPresent: Waits for an alert to be present.


    */