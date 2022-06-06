SPRING APP LOG IN AND SENDING EMAILS FOR VALIDATION
- with the help of amigoscode we are going to create an email log in app, that's going to send a validation email to your inbox

first things first, we rename the application.properties file to application.yml, different configuration

1) In AppUser, which is our first class, we have it implement the UserDetails class, and we have to say implement undefined methods, otherwise we get an error.
	- we create our variables (id, name, email, ....)
	- we have to create a separate enum type, which holds the values that do NOT change throughout the course of the program, there we put two values, user and admin
	- in the first generated method getAuthorities() we put a new SimpleGrantedAuthority variable (which is going to be the username), 
	  we'll return a Collections singleton list with that authority inside
	- then we assign return values to the rest of the methods that have been generated, more in the code itself :)
	- we give our class annotations we already know with a new one, that is EqualsAndHashCode, which generates the hashCode and equals methods for us automatically
	  we annotate our ID but there's something new, we had to use @Enumerated(EnumType.String) on our AppUserRole, what we say by it is that the value is of type
	  string but we essentially need it to be converted into type AppUserRole

2) Next we create a service AppClassService, which implements UserDetailsService, which again going to prompt us to generate a method, loadUserByUsername
	- we create our repo and give it the annotation we already know + @Transactional(readOnly = true), our query will be there
	- in service, we return the repo variable we created, but the findByEmail(email) method, but we have to make sure error get served, therefore we say
	  .orElseThrow and create a new user not found exception
	- we can also create our own error in a few ways, such as USER_NOT_FOUND and assign a string with our error message to the variable
	- then, in the UsernameNotFoundException(), we put the String.format() method which takes two parameters, the string and the variable used inside it

3) We create a new package called Registration and create a Controller, Request and Service
	- we start in the Controller, where we give it annotations and create a method register, which is connected to the request, that's where we will go next
	- in the Request, we put all the desired variables (which are fname, lname, email and password) and annotate the class, as seen in the program
	- since the register function does not exist in the Service, we have it be automatically created and have it return something to test whether it works or not.

4) The Security package is created next, where there is really only one class is where things are configured, and that is WebSecurityConfig we will also have PasswordEncoder, but in a different package
	-  in our WebSecurityConfig we give it the new annotations to me with this project, @Configuration (used for dependency injection) and @EnableWebSecurity and the class will extend the
	   WebSecurityConfigureAdapter class, which deprecated, meaning that its not recommended to use it and its being removed
	- next we override the configure method and we basically configure that any request that goes through the URL is permitted, is allowed
	- then we create an another override and a bean where we use the DaoAuthenticationProvider, and since our AppUserService implements UserServiceDetails, we are able to
	  create a variable of our class and set it as the UserDetalsService
	- at the moment security is completely done

Some things had to be configured in order to work, but it mostly was missing annotations (for example the @Service annotation) and we must not forget that our Repo
extends the JpaRepository

Then we test with Thunder Client whether sending requests works, using the post method
Spring security in action: if we go at the url /hello we will be prompted to be sign in page

5) We will check in Service whether the email is valid, therefore we create an email validator, which implements Predicate<String>

6) Back in RegistrationService, we change up the register function a little bit so we use our email validator and have some basic functionality like throwing errors
   and basically sign up the user

7) In AppUserService we create the signUpUser method, we chceck whether the user exists (whether the email is already being used), if it is the program prints an error
   then we create an encoded password for the users and assign it instead of the one they currently have and then save the user

We continue testing with thunder client and if we try to log in we will see the "user is disabled" error, which needs fixing
Also, if we try to continue with an email that has already been used we see that thunder client triggers an error

8) Now it's time to make email validation functional:
	- first is expiration link and then an email
	- we create a new package token and a ConfirmationToken class inside it and give it these variables:
		. id
		. token
		. createsAt
		. expiresAt
		. confirmsAt
	- the last three are of type LocalDateTime, to check whether the link is still valid
	- we also need to define an AppUser variable and give it the @ManyToOne and @JoinColumn annotations
	  @ManyToOne is basically an inter-table relationship, in this context meaning that the user can have multiple tokens
	  @JoinColumn which is basically an extension annotation for the first one 

9) Next we create a Repo and a Service for our ConfirmationToken
	- we do the standard stuff we already know and go back to AppUserService to create and save our token :)