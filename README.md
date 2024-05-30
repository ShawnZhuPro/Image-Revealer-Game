How to setup JavaFX
1. Open Eclipse -> Help -> Eclipse Marketplace
2. Search for "javafx"
3. You'll see e(fx)eclipse, install it.
4. After installation, restart eclipse
5.Download JavaFX in here JavaFX (Click here to download JavaFX: https://gluonhq.com/products/javafx/)
6.Extract the folder and place it downloads folder (or anyplace)
7.Then in eclipse right click the project and select properties
8.Choose Java Build Path
9.Then select Libraries tab (you'll see tabs on the top)
10.You'll see Classpath > JavaFX SDK
11.Click on Classpath, then click Add external JARs from the right.
12.Then from the download JavaFX (placed in downloads folder or somewhere you've placed).
13.Choose all the .jars file in Downloads/javafx-sdk-11.0.2/lib/(all .jar(s) file) and click open.
14.Then click Apply and Apply and close.
15.Then you won't get red underlines or (error).
16.Then right click on project > Run as > Run configuration
17.Choose the arguments tab and type this in VM arguments.
    --module-path /path/to/JavaFX/lib  --add-modules=javafx.controls,javafx.fxml
    **REPLACE PATH TO WITH THE ACTUAL PATH**
