# Simple-Ktor-Api
Simple project to study ktor

Study observations to me

### 1) Procfile to deploy
The template procfile necessary to push to heroku is

web: ./build/install/[projectname]/bin/[projectname]

Where [projectname] is name find in settings.gradle, on this project for example
this name is "com.pliniodev.ktor-sample"

### 2) Code rotine to heroku

`heroku login` 

To push application on server
`git push heroku master`

To start Procfile
`heroku ps:scale web=1`

To open the application
`heroku open`

### 3) Some used links to find solutions
https://ktor.io/docs/status-pages.html#install_plugin

https://ktor.io/docs/interactive-website-add-persistence.html#model

https://ktor.io/docs/html-dsl.html#templates

https://github.com/Kotlin/kotlinx.html

https://bettercoding.dev/kotlin/ktor-rest-api-exposed/

https://dev.to/kotlin/publishing-server-side-kotlin-applications-ktor-on-heroku-2ce4

https://dev.to/kotlinautas/criando-uma-api-com-ktor-8le

https://ishroid.medium.com/ktor-database-connection-with-h2-ktor-and-exposed-100e4d3dc2e2

https://medium.com/@dariopellegrini/deploy-a-kotlin-server-in-minutes-using-ktor-and-heroku-ed6ed33337f

how add postgresql db in heroku
https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java

how connect to pgadmin
https://stackoverflow.com/questions/11769860/connect-to-a-heroku-database-with-pgadmin