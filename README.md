README file for all TEKSYSTEMS Interview Code Test Projects.

##TEKsystems:OrderCalculation

###Description
OrderCalculation is a console program designed to compute sales taxes and totals of all items that are added to orders, where the orders are part of an overall bill. The final output is a display of all item information, order calculations, and the overall bill expenditure.

###Usage Notes
This project consists of two separate versions: 
  1. @*bugfixes*   -->  First version of OrderCalculation that is designed to fix all program bugs
                      in the supplied java code.
  2. @*refactored* -->  Refactored version of OrderCalculation that is designed using a much cleaner, 
                      modular approach. This is achieved by utilizing advanced coding techniques 
                      and tested via JUnit4 unit tests.                                     
To run each version of OrderCalculation via the command prompt:
  1. Verify appropriate environment and tools are downloaded. (type '*mvn -version*' in cmd prompt)
    - Apache Maven v3.3.9+
    - Java Runtime Environment (JRE) v1.8.0+
    - Java SE Development Kit (JDK) v1.8.0+    
  2. Copy and extract the GitHub repository from https://github.com/stevewhit/TEKsystems
  3. Open command prompt; cd to ..\TEKsystems\OrderCalculation
  4. To RUN each version:
    * @*bugfixes*   --> type '*mvn clean package exec:java@bugfixes*'
    * @*refactored* --> type '*mvn clean package exec:java@refactored*'
    
###Program Design/Approach for *@refactored*
  - When refactoring the given code, I chose to implement a more readable and maintainable approach utilizing OOP conventions.
  - AbstractOrderItem is the base class for an item and contains fields and methods that any type of item should contain. I chose this approach because it allows flexability in future development to design different classes that extend AbstractOrderItem but may need a similar base class (ie. if the developer wants to break LocalOrderItem down further by implementing it with state/federal tax rates or shipping charges).
  - LocalOrderItem and ImportedOrderItem have protected constructors because this restricts the developer by making sure these OrderItems are implemented through ShoppingOrder (ex. An item on Amazon has to be placed in the shopping cart before it can be purchased).
  - Using the same Amazon transaction example as above, a ShoppingOrder does not have to be part of a bill to be created (ex. An amazon buyer doesn't have to have their credit card linked to their Amazon account in order to fill a shopping cart, they can do so after they're ready to purchase the goods).
