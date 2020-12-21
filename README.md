# snake_ads_refactoring

How to play?  
W - up  
A - left  
D - right  
S - down  
R - respawn  

Solving the problems with JavaFx:
1. Download the appropriate JavaFX SDK for your operating system and unzip it to a desired location
https://gluonhq.com/products/javafx/

2. Add the JavaFx library to the project:
(IntelliJ) Go to File -> Project Structure -> Libraries and add the JavaFX 11 SDK as a library to the project. Point to the lib folder of the JavaFX SDK. 

3. Add a new Env Variable:
(IntelliJ) File->Settings->Appearance & Behavior->Path Variables: and add PATH_TO_FX, with the path to the lib folder of javafx

4. Run -> Edit Configurations... and add these VM options:
--module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.media,javafx.fxml

5. Set resources folder as a Resource.
(IntelliJ) Go to File -> Project Structure -> Modules and choice the project, in right side click on resources folder, mark directory as a resources root (there are some icon where shows with "Marks as:". 
