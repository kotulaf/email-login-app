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
	- in the 