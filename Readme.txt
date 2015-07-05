This is a maven project .. So please follow the following instructions.

System requirements :
  1. Jdk 1.7
  2. tomcat 
  3. eclipse
  4. maven 
  5. no need to install any database as we are using sqlite 3 internally
  
Execution Procedure:

1. Extract the files from the git repository (source folders and pom.xml)
2. run the command mvn clean install
3. if you want to use eclipse , use mvn eclipse:eclipse
4.  by running mvn clean install , it will run the unit test cases also
    ( for unit test cases, used mockito frame work)
5. probable url : http://localhost:9090//RESTfulExample/rest/task/

     There are totally 4 cases :
      1. list tasks : http://localhost:9090//RESTfulExample/rest/task/list/{low/medium/high/all}
      2. add task : http://localhost:9090//RESTfulExample/rest/task/addTask
                    post body: {"taskname":XXXXX,"taskpriority":XXXX} 
                    content-type should be application/json else it will give status as 415
      3. update task : http://localhost:9090//RESTfulExample/rest/task/update
                    post body: {"taskid":XXX} content-type should be application/json else it will give status as 415
      4. delete task : http://localhost:9090//RESTfulExample/rest/task/delete
                     post body: {"taskid":XXX} content-type should be application/json else it will give status as 415
                     
 How to test it ?
 1. Install an addon "poster" in mozilla firefox
 2. Give the details as described in the form                   
                     
 