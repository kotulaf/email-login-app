SPRING APP LOG IN AND SENDING EMAILS FOR VALIDATION
- with the help of amigoscode we are going to create an email log in app, that's going to send a validation email to your inbox

first things first, we rename the application.properties file to application.yml, different configuration

in AppUser, which is our first class, we have it implement the UserDetails class, and we have to say implement undefined methods, otherwise we get an error.
