# NewsListing


## Project Setup

- Clone the master branch to your local using following command

`git clone https://github.com/rajat-singh-jasrotia/NewsListing.git`

- Import the project to your Android Studio

  Follow the following steps to import the project, it will build the gradle automatically.
  
  `Go to File -> New Project -> Import Project and select the newly Cloned directory -> press OK`
  
- Run the test cases.

  We can either use the IDE to run our test cases or use the following command:
  
  `./gradlew test`

- Run the project
  We can either use the IDE to run our App or use the following command (App in debug mode):
  
  `./gradlew assembleDebug`

- Apk Path (For debug apk)

  `USNews/app/build/outputs/apk/debug/app-debug.apk`



## Project Structure

* `build.gradle` - root gradle config file
* `settings.gradle` - root gradle settings file
* `app` - our only project in this repo
* `app/build.gradle` - project gradle config file
* `app/src` - main project source directory
* `app/src/test` - main project test
* `app/src/main/AndroidManifest.xml` - manifest file
* `app/src/main/java` - java source directory
* `app/src/main/res` - resources directory
