# AGU
AGU is a user-friendly mobile application designed to connect parents or guardians with trustworthy and qualified babysitters. Whether you need a sitter for a night out, a last-minute appointment, or regular childcare, AGU simplifies the process of finding the perfect caregiver for your children.
Currently implemented features:
1. Easy Registration: Parents and babysitters can quickly create an account by providing their essential details. For Babysitters there is also a possibility to choose their location, so that client could see him on the map.
2. Secure Messaging: AGU provides a secure in-app messaging feature, allowing parents and babysitters to communicate efficiently. It's implemented using firebase and we've written our own messenger.
3. Profile editing, password resetting, google authentification are also included in our app.
4. Data caching. Since we are using firebase, it is enough for user to register once, so that app will remember user's credentials. Also when user is connected to Internet, all new updates will be catched up, so that user will have current data.


Deploy & Run:
To run the source code of a project you need to install latest version of AndroidStudio Flamingo.
Afterwards clone the repository, and you should be good with the default settings of the IDE. Build & Run. 
If everything is good the simulator of a mobile phone(virtual machine) will show up, and the app will be executed. 

Notes on OOP:
The used architecture type is MVVM, so in the packages you can see files with suffix View, ViewModel, and Model.
Almost all logic(i.e. code of Model) is implemented in Java, while all View is implemented in Kotlin. ViewModel varies and utilizes both Kotlin and Java.

Contributions:
Made 50/50 by Daniil Zabauski and Bogdan Tolstik. Daniil focused more on messenger and map, while Bogdan on registration, Executors cards, as well as map. 
Backend was written together. 




