this file is just a placeholder for some ideas and logics that still fresh in my mind, 
and explain some decisions for future escalation and reduce confusion, and code pasta

in the app/ (app/ = src/main/java/kani.springsecurity/) are thre folder holding thre difreten logics 

 - Application : here we have all the parts that are important for the aplication to work, and is also
the point between the busines logic and the I/O of the aplication as a system
   - in the folder are Resquest and responses packges for I/O encapsulation so dont pase sansible data.


- Domain : here the logic of the aplication and entitys are, here we have class to database entity base of implementation
like model,repository and service, the service is here to be a point of interation for the aplication package logic
    - by now the database is in mysql( i will start the migration to postegres) and the database entitys are basicly separated in thre,
  users , profile and tags, users hold the most important and non response important data like username and password, profile has secondari informations
  end tags where a table just for future semantic search.

- infra : configuration and other features for outside aplication features like webflux webclient
and spring security config

