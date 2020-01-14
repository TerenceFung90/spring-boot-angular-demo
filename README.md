# spring-boot-angular-demo
This is an example of spring boot as backend connecting to database and calling 3rd party api, serving restful api to angular as frontend web app.

# prerequisite
- mysql database

# start up spring boot backend
- modify application.properties for database connection
- run "mvn clean package"
- run "spring-boot:run"

NOTE: this spring boot app must serve on port 8080, since the angular app is consuming api on localhost:8080

# start up angular app
option 1: setup npm and angular
ref: https://angular.io/guide/setup-local
- at /demoApp folder, run "npm install" since this project src has not include any libraries.
- run "ng serve --open"

OR

option 2: use pre-build app
- unzip the demoApp.zip (this is production build)
- deploy to tomcat webapp folder
- open web browser at localhost:{port}/demoApp/


